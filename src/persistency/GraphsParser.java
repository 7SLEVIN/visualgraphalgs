package persistency;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import model.Coordinate;
import model.Edge;
import model.Graph;
import model.Vertex;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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
			this.graphs.add(new Graph(atts.getValue("name")));
        } else if (qName.equals("vertex")) {
			Vertex vertex = new Vertex(atts.getValue("name"), new Coordinate(
					Integer.parseInt(atts.getValue("x")), Integer.parseInt(atts
							.getValue("y"))));
			current.addVertex(vertex);
			String attribute = atts.getValue("attribute");
			if (attribute != null && !attribute.equals("")) {
				vertex.setAttribute(attribute);
			}
        } else if (qName.equals("edge")) {
			Vertex from = current.getVertex(atts.getValue("from"));
			Vertex to = current.getVertex(atts.getValue("to"));
			Edge edge = new Edge(from, to);
			current.addEdge(edge);
			String attribute = atts.getValue("attribute");
			if (attribute != null && !attribute.equals("")) {
				edge.setAttribute(attribute);
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
