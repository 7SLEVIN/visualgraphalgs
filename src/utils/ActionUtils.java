package utils;

import java.awt.event.ActionListener;
import java.beans.EventHandler;

import javax.swing.JButton;

public class ActionUtils {
	
	public static void addActionListener(JButton btn, Object target, String method) {
		btn.addActionListener((ActionListener)EventHandler.create(ActionListener.class, target, method));
	}
	
//	public static void addItemListener(JComboBox<?> comboBox, Object target, String method, String e) {
//		comboBox.addItemListener((ItemListener)EventHandler.create(ItemListener.class, target, method, e));
//	}
	
}
