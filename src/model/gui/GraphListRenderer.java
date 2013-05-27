package model.gui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.Graph;

@SuppressWarnings("serial")
public class GraphListRenderer extends JLabel implements ListCellRenderer<Graph> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Graph> list,
			Graph value, int index, boolean isSelected, boolean cellHasFocus) {
		setText(value != null ? value.getName() : "");
		return this;
	}


}
