package model.persistency;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import model.Coordinate;
import model.elements.Edge;
import model.elements.Graph;
import model.elements.GraphAttributeType;
import model.elements.GraphType;
import model.elements.Vertex;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import exceptions.AlgorithmException;
import exceptions.GraphComponentException;
import exceptions.GraphException;
import exceptions.MalformedGraphException;

public class GraphsParser extends DefaultHandler {

	final static String XML_FILE = "../persistency/Graphs.xml";
	
	private ArrayList<Graph> graphs;
	    
    private Stack<String> elementStack = new Stack<>();
    
    /**
	 * 
	 */
	public GraphsParser() {
		this.graphs = new ArrayList<Graph>();
	}

	public ArrayList<Graph> parse() {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
                InputStream xmlInput = this.getClass().getResourceAsStream(XML_FILE);

                SAXParser saxParser = factory.newSAXParser();

                saxParser.parse(xmlInput, this);

        } catch (FileNotFoundException e) {
                System.out.println("ERROR: Cannot find file: " + XML_FILE);
                System.exit(1);
        } catch (ParserConfigurationException e) {
                System.out.println("ERROR: ParserConfigurationException thrown");
                System.exit(1);
        } catch (SAXException e) {
                System.out.println("ERROR: SAXException thrown");
                System.exit(1);
        } catch (IOException e) {
                System.out.println("ERROR: IOException thrown");
                System.exit(1);
        }
        
        return this.graphs;
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts)
    throws SAXException {
        this.elementStack.push(qName);
        
        int idx = this.graphs.size()-1;
        Graph current = null;
        if (idx >= 0) current = this.graphs.get(idx);
        
        if (qName.equals("graph")) {
        	String name = atts.getValue("name");
        	if (name == null || name.equals("")) {
				try {
					throw new MalformedGraphException("Graph with no name");
				} catch (MalformedGraphException e) {
					e.printStackTrace();
				}
        	}
        	
        	GraphType type = null;
        	if (atts.getValue("type") == null) {
        		type = GraphType.Directed;
        	} else {
        		try {
					throw new MalformedGraphException(String.format("Unkown GraphTrype %s",
							atts.getValue("type")));
				} catch (MalformedGraphException e) {
					e.printStackTrace();
				}
        	}
        	
        	GraphAttributeType attributeType = null;
        	if (atts.getValue("attributeType") == null) {
        		attributeType = GraphAttributeType.None;
        	} else if (atts.getValue("attributeType").equals("Weighted")) {
        		attributeType = GraphAttributeType.Weighted;
        	} else {
        		try {
					throw new MalformedGraphException(String.format("Unkown GraphAttributeTrype %s",
							atts.getValue("attributeType")));
				} catch (MalformedGraphException e) {
					e.printStackTrace();
				}
        	}
        	
			this.graphs.add(new Graph(atts.getValue("name"), type, attributeType));
			
        } else if (qName.equals("vertex")) {
			Vertex vertex = new Vertex(atts.getValue("name"), new Coordinate(
					Double.parseDouble(atts.getValue("x")), 
					Double.parseDouble(atts.getValue("y"))));
			String attribute = atts.getValue("attribute");
			if (attribute != null && !attribute.equals("")) {
				vertex.setAttribute(attribute);
			}
			current.addVertex(vertex);
			
        } else if (qName.equals("edge")) {
			Vertex from = current.getVertex(atts.getValue("from"));
			Vertex to = current.getVertex(atts.getValue("to"));
			Edge edge = new Edge(from, to);
			String attribute = atts.getValue("attribute");
			if (attribute != null && !attribute.equals("")) {
				edge.setAttribute(attribute);
			}
			
			try {
				current.addEdge(edge);
			} catch (GraphComponentException e) {
				e.printStackTrace();
			} catch (GraphException e) {
				e.printStackTrace();
			} catch (AlgorithmException e) {
				e.printStackTrace();
			}
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
    throws SAXException {
        this.elementStack.pop();
    }

    @Override
    public void characters(char ch[], int start, int length)
    throws SAXException {
        
    }

//    private String currentElement() {
//        return this.elementStack.peek();
//    }
//
//    private String currentElementParent() {
//        if(this.elementStack.size() < 2) return null;
//        return this.elementStack.get(this.elementStack.size()-2);
//    }
    
    @Override
    public void startDocument() throws SAXException {
            
    }

    @Override
    public void endDocument() throws SAXException {
        
    }
}
