package spellingbee;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {
	private Map<Character,Node> roots = new HashMap<Character,Node>();
	
	/**
	 * Search through the dictionary for a word.
	 * @param string The word to search for.
	 * @return Whether or not the word exists in the dictionary.
	 */
	public boolean search(String string) {
		if (roots.containsKey(string.charAt(0))) {
			if (string.length()==1 && roots.get(string.charAt(0)).endOfWord) {
				return true;
			}
			return searchFor(string.substring(1),roots.get(string.charAt(0)));
		} else {
			return false;
		}	
	}
	
	/**
	 * Insert a word into the dictionary.
	 * @param string The word to insert.
	 */
	public void insert(String string) {
		try {
			if (!roots.containsKey(string.charAt(0))) {
				roots.put(string.charAt(0), new Node());
			}
			
			insertWord(string.substring(1),roots.get(string.charAt(0)));

		} catch(Exception e) {
			return;
		}
	}
	

	//Recursive method that inserts a new word into the trie tree.
	private void insertWord(String string, Node node) {
		final Node nextChild;
		if (node.children.containsKey(string.charAt(0))) {
			nextChild = node.children.get(string.charAt(0));
		} else {
			nextChild = new Node();
			node.children.put(string.charAt(0), nextChild);
		}
				
		if (string.length() == 1) {
			nextChild.endOfWord = true;
			return;
		} else {
			insertWord(string.substring(1),nextChild);
		}
	}

	public void findAllWords(String letters, Character keyLetter) {
		for (Character key : this.roots.keySet()) {
			for (Character c : letters.toCharArray()) {	
				if (key.toString().equals(c.toString())) {
					String word = new String(c.toString());
					searchForAll(letters, keyLetter, word, this.roots.get(key));
				}
			}
		}
	}
	
	public void searchForAll(String letters, Character keyLetter, String word, Node node) {
		if (node.endOfWord) {
			if (word.length() > 5 && word.contains(keyLetter.toString())) {
				System.out.println(word);
			}
		}  
		for (Character c : letters.toCharArray()) {
			if (node.children.keySet().contains(c)) {
				String w = new String(word.concat(c.toString()));
				searchForAll(letters, keyLetter, w, node.children.get(c));	
			}
		}
	}
	
	//Recursive method that searches through the Trie Tree to find the value.
	private boolean searchFor(String string, Node node) {
		if (string.length()==0) {
			if (node.endOfWord) {
				return true;
			} else {
				return false;
			}
		}
		
		if (node.children.containsKey(string.charAt(0))) {
			return searchFor(string.substring(1),node.children.get(string.charAt(0)));
		} else {
			return false;
		}
	}
}
