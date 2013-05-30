package utils;

import java.util.Comparator;

import model.Edge;

public class EdgeComparator implements Comparator<Edge> {

	@Override
	public int compare(Edge n1, Edge n2) {
        if (StringUtils.placeInAlphabet(n1.getTo().getName())[0] < 
        		StringUtils.placeInAlphabet(n2.getTo().getName())[0]) {
        	return -1;
        } else {
        	return 1;
        }
	}

}
