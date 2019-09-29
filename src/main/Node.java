package main;

public class Node {
	private Node left = null, right = null;
	private char charackter = '\0';
	private CharSequence chars = null;
	private int count = 0;
	private String byteSequence = "";
	public boolean rootNode = false;

	public Node(char character, int count, boolean rootNode) {
		this.charackter = character;
		this.count = count;
		this.rootNode = rootNode;
	}

	public Node(Node left, Node right, CharSequence characters) {
		this.left = left;
		this.right = right;
		this.chars = characters;
		this.count = this.left.getCount() + this.right.getCount();
	}

	public Node getLeft() {
		return this.left;
	}

	public Node getRight() {
		return this.right;
	}

	public char getCharacter() {
		return charackter;
	}

	public CharSequence getCharacters() {
		return chars;
	}

	public int getCount() {
		return this.count;
	}

	public boolean hasLeft() {
		return left != null;
	}

	public boolean hasRight() {
		return right != null;
	}

	public void setByteSequence(String byteSequence) {
		this.byteSequence = byteSequence;
	}

	public String getByteSequence() {
		return this.byteSequence;
	}

	public void updateNodes (boolean left, CharSequence parent, boolean top) {
		if (top) {
			this.byteSequence = "";
		} else {
			if (left) {
				this.byteSequence = parent + "0";
			} else {
				this.byteSequence = parent + "1";
			}
		}
		
		if (!this.rootNode) {
			this.left.updateNodes(true, this.byteSequence, false);
			this.right.updateNodes(false, this.byteSequence, false);
		}
	}
	
	
	
	@Override
	public String toString() {
		String string = "";
		string += "char: " + this.charackter + "\n";
		string += "chars: " + this.chars + "\n";
		string += "count: " + this.count + "\n";
		string += "hasLeft: " + this.hasLeft() + "\n";
		string += "hasRight: " + this.hasRight() + "\n";
		string += "byteSequence: " + this.getByteSequence() + "\n";
		string += "isRootNode: " + this.rootNode + "\n";
		return string;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Node) {
			Node n = (Node) obj;
			if (n.getCount() == this.getCount() && n.getCharacter() == this.getCharacter()
					|| n.getCharacters() == this.getCharacters()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}
}
