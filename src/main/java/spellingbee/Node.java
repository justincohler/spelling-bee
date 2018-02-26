package spellingbee;

import java.util.HashMap;
import java.util.Map;

public class Node {
	public Character value;
	public Boolean endOfWord = false; //Does this Node mark the end of a particular word?
	public Map<Character, Node> children = new HashMap<Character, Node>();
}