package model.gui;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import model.algorithms.Algorithm;

public class AlgorithmComboBoxModel implements ComboBoxModel<Algorithm> {
	
	private ArrayList<Algorithm> algorithms;
	private Algorithm selected;

	public AlgorithmComboBoxModel(ArrayList<Algorithm> algorithms) {
		this.algorithms = algorithms;
	}
	
	@Override
	public int getSize() {
		return this.algorithms.size();
	}

	@Override
	public Algorithm getElementAt(int index) {
		return this.algorithms.get(index);
	}

	@Override
	public void setSelectedItem(Object selected) {
		this.selected = (Algorithm) selected;
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
