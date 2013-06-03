package model;

import java.util.Comparator;

import model.elements.Edge;

import utils.StringUtils;


public class EdgeComparator implements Comparator<Edge> {

	private EdgeComparatorType type;
	
	/**
	 * @param type
	 */
	public EdgeComparator(EdgeComparatorType type) {
		this.type = type;
	}

	@Override
	public int compare(Edge n1, Edge n2) {
		if (this.type == EdgeComparatorType.Name) {
			if (StringUtils.placeInAlphabet(n1.getTo().getName())[0] <= 
					StringUtils.placeInAlphabet(n2.getTo().getName())[0]) {
				return -1;
			} else {
				return 1;
			}
		} else if (this.type == EdgeComparatorType.Weight) {
			if (Integer.parseInt(n1.getAttribute()) <= 
				Integer.parseInt(n2.getAttribute())) {
				return -1;
			} else {
				return 1;
			}
		}
		return 0;
	}

}
