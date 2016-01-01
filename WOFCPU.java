import java.util.Scanner;

public class WOFCPU {

	private int totalMoney;
	private String name;

	public WOFCPU(String n) {
		name = n;
		totalMoney = 0;
	}

	public String makeMove(String knownLetters, String word) {
		System.out.println(name + "has a total of $" + totalMoney + " so far");
		System.out.println(knownLetters);
		int num;
		while (true) {
			num = (int)(Math.random() * 3) + 1;
			if (num == 2 && totalMoney < 100) continue;
			break;
		}
		Prompt.kb.nextLine();
		System.out.print(name + " chose to ");
		switch (num) {
			case 1: default: System.out.println("spin."); return spin(knownLetters, word);
			case 2: System.out.println("buy a vowel."); return buyVowel(knownLetters, word);
			case 3: System.out.println("solve the puzzle."); return solvePuzzle(knownLetters, word);
		}
	}

	public String spin(String knownLetters, String word) {
		String s;
		int ranMoney = 100 * ((int)(Math.random() * 26) + 5);
		System.out.println("The spin landed on $" + ranMoney);
		do {
			s = (char)((int)(Math.random() * 26) + 'a') + "";
		} while (s.equalsIgnoreCase("a") ||
				s.equalsIgnoreCase("e") ||
				s.equalsIgnoreCase("i") ||
				s.equalsIgnoreCase("o") ||
				s.equalsIgnoreCase("u"));
		System.out.println("The computer guessed the letter " + s.toUpperCase() + ".");
		char[] letters = knownLetters.toCharArray();
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == s.charAt(0)) {
				letters[i] = s.charAt(0);
				totalMoney += ranMoney;
			}
		}
		return new String(letters);
	}

	public String buyVowel(String knownLetters, String word) {
		String s;
		int n = (int)(Math.random() * 5);
		switch (n) {
			case 0: s = "a"; break;
			case 1: s = "e"; break;
			case 2: s = "i"; break;
			case 3: s = "o"; break;
			case 4: s = "u"; break;
			default: s = "?";
		}
		char[] letters = knownLetters.toCharArray();
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == s.charAt(0)) {
				letters[i] = s.charAt(0);
			}
		}
		totalMoney -= 100;
		return new String(letters);
	}

	public String solvePuzzle(String knownLetters, String word) {
		String guess = findWord(knownLetters);
		if (guess.equalsIgnoreCase(word)) {
			System.out.println("That's correct! The word is " + word.toUpperCase());
			knownLetters = new String(word);
		}
		return knownLetters;
	}

	public String findWord(String knownLetters) {
		Scanner scan = OpenFile.openToRead("wordlist.txt");
		while (scan.hasNext()) {
			String nextWord = scan.nextLine();
			if (nextWord.length() != knownLetters.length()) continue;
			boolean match = true;
			for (int i = 0; i < nextWord.length(); i++) {
				if (knownLetters.charAt(i) == '-') continue;
				if (nextWord.charAt(i) != knownLetters.charAt(i)) {
					match = false;
					break;
				}
			}
			if (match) return nextWord;
		}
		return "";
	}
}