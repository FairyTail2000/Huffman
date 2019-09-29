package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	static double newLen = 0;
	
	public static void main(String[] args) {
		//Scanner s = new Scanner (System.in);
		String to_Convert = "test";
		double oldSize = to_Convert.length() * 7;
		System.out.println("Testing: " + to_Convert + " with " + oldSize + " bit length" + "\n");
		Map<Character, Integer> map = count(to_Convert);
		List<Node> nodes = new ArrayList<>();
		
		map.forEach((c, i) -> {
			nodes.add(new Node(c, i, true));
		});
		
		Tree tree = new Tree();
		tree.addLayer(nodes);
		List<Node> newMap = encodeLayer(nodes, true);
		tree.addLayer(newMap);
		while ((newMap = encodeLayer(newMap, false)).size() != 1) {
			tree.addLayer(newMap);
		}
		newMap.get(0).updateNodes(false, "", true);
		tree.addLayer(newMap);
		
		tree.getLayer(0).forEach((node) -> {
			newLen += node.getByteSequence().length() * node.getCount();
		});
		System.out.println("New length: " + newLen);
		System.out.printf("Kompressionfactor: %.0f%c", oldSize / newLen * 10, '%');
		//s.close();
	}
	
	
	private static Map<Character, Integer> count (String toMap) {
		Map<Character, Integer> map = new HashMap<>();
		int length = toMap.length();
		for (char i = 0; i < 256 + 1; i++) {
			//If the string doesn't contain it, fuck it
			if (!toMap.contains("" + i)) {
				continue;
			}
			//Inefficient but fuck it
			String result = toMap.replaceAll("" + i, "");
			map.put(i, length - result.length());
		}
		return map;
	}
	
	private static List<Node> encodeLayer (List<Node> list, boolean firstLayer) {
		List<Node> locallist = new ArrayList<>();
		List<Node> nextLayer = new ArrayList<>();
		list.forEach(locallist::add);
		
			while (!locallist.isEmpty()) {
				Node act = locallist.get(0);
				if (locallist.size() == 1) {
					nextLayer.add(act);
					break;
				}
				
				Node[] arr = new Node[2];
				int index = 0;
				int result = 0;
				for (Node n : locallist) {
					if (n.getCount() <= act.getCount()) {
						act = n;
						result = index;
					}
					index++;
				}
				arr[0] = act;
				locallist.remove(result);
				
				index = 0;
				result = 0;
				act = locallist.get(0);
				for (Node n : locallist) {
					if (n.getCount() <= act.getCount()) {
						act = n;
						result = index;
					}
					index++;
				}
				arr[1] = act;
				locallist.remove(result);
				
				if (firstLayer) {
					System.out.println("Adding Nodes: " + arr[0].getCharacter() + ":" + arr[0].getCount() + " and " + arr[1].getCharacter() + ":" + arr[1].getCount());
					nextLayer.add(new Node(arr[0], arr[1], arr[0].getCharacter() + "" +  arr[1].getCharacter()));
				} else {
					if (arr[1].getCharacters() == null) {
						System.out.println("Adding Nodes: " + arr[0].getCharacters() + ":" + arr[0].getCount() + " and " + arr[1].getCharacter() + ":" + arr[1].getCount());
						nextLayer.add(new Node(arr[0], arr[1], arr[0].getCharacters() + "" + arr[1].getCharacter()));
					} else if (arr[0].getCharacters() == null) {
						System.out.println("Adding Nodes: " + arr[0].getCharacter() + ":" + arr[0].getCount() + " and " + arr[1].getCharacters() + ":" + arr[1].getCount());
						nextLayer.add(new Node(arr[0], arr[1], arr[0].getCharacter() + "" + arr[1].getCharacters()));
					} else {
						System.out.println("Adding Nodes: " + arr[0].getCharacters() + ":" + arr[0].getCount() + " and " + arr[1].getCharacters() + ":" + arr[1].getCount());
						nextLayer.add(new Node(arr[0], arr[1], arr[0].getCharacters() + "" + arr[1].getCharacters()));						
					}
				}
				System.out.println("Result: " + nextLayer.get(nextLayer.size() - 1).getCharacters());
			}
		return nextLayer;
	}
}
