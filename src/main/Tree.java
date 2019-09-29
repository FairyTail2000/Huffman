package main;

import java.util.ArrayList;
import java.util.List;

/**
 * This class isn't necessary but it helps with Abstraction
 * @author rafael
 *
 */
public class Tree {
	private List<List<Node>> nodes = new ArrayList<>();
	
	public void addLayer (List<Node> layer) {
		this.nodes.add(layer);
	}
	
	/**
	 * 
	 * @param index
	 * @return the List of Nodes in the Layer
	 * @throws IndexOutOfBoundsException
	 */
	public List<Node> getLayer (int index) {
		return nodes.get(index);
	}
	
	/**
	 * @return the Number of Layers this Huffman tree has
	 */
	public int getSize() {
		return nodes.size();
	}
}
