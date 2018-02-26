package spellingbee;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		
		String letters = "ycegmnr";
		String keyLetter = "r";
		
		if (args.length != 2) {
			System.err.println("Please pass in two arguments, first being the letters in the puzzle, the second specifying the special character for the puzzle, separated by a space.");
	        System.exit(1);
		} else {
			
			letters = args[0];
			keyLetter = args[1];
			if (letters.length() != 7 || keyLetter.length() != 1 ) {
				System.err.println("Argument" + args[0] + " must be 7 letters. Argument " + args[1] + " must be 1 letter.");
		        System.exit(1);
			}
		}
		

		String fileName = "c:/words.txt";

		List<String> words = new ArrayList<>();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

			//br returns as stream and convert it into a List
			words = br.lines().collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Dictionary dict = new Dictionary();

		for (String word : words) {
			dict.insert(word.toLowerCase());
		}
		
		dict.findAllWords(letters, keyLetter.charAt(0));
	}

}
