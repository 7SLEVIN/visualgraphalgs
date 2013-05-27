package model.gui;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import model.Graph;

public class GraphComboBoxModel implements ComboBoxModel<Graph> {
	
	private ArrayList<Graph> graphs;
	private Graph selected;

	public GraphComboBoxModel(ArrayList<Graph> graphs) {
		this.graphs = graphs;
	}
	
	@Override
	public int getSize() {
		return this.graphs.size();
	}

	@Override
	public Graph getElementAt(int index) {
		return this.graphs.get(index);
	}

	@Override
	public void setSelectedItem(Object selected) {
		this.selected = (Graph) selected;
	}

	@Override
	public Object getSelectedItem() {
		return this.selected;
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	
}
