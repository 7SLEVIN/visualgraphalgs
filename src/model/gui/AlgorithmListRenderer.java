package model.gui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.algorithms.Algorithm;

@SuppressWarnings("serial")
public class AlgorithmListRenderer extends JLabel implements ListCellRenderer<Algorithm> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Algorithm> list,
			Algorithm value, int index, boolean isSelected, boolean cellHasFocus) {
		setText(value != null ? value.getName() : "");
		return this;
	}


}
