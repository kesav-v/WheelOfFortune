public class WOFPlayer {

	private int totalMoney;
	private String name;

	public WOFPlayer(String n) {
		name = n;
		totalMoney = 0;
	}

	public String makeMove(String knownLetters, String word) {
		System.out.println(name + ", you have a total of $" + totalMoney + " so far");
		System.out.println(knownLetters);
		int num;
		while (true) {
			num = Prompt.getInt(name + ", it's your turn. Enter 1 to spin, 2 to buy a vowel, or 3 to solve the puzzle -> ", 1, 3);
			if (num == 2 && totalMoney < 100) continue;
			break;
		}
		Prompt.kb.nextLine();
		switch (num) {
			case 1: default: return spin(knownLetters, word);
			case 2: return buyVowel(knownLetters, word);
			case 3: return solvePuzzle(knownLetters, word);
		}
	}

	public String spin(String knownLetters, String word) {
		String s;
		int ranMoney = 100 * ((int)(Math.random() * 26) + 5);
		System.out.println("Your spin landed on $" + ranMoney);
		do {
			s = Prompt.getString("Enter a consonant -> ");
		} while (s.equalsIgnoreCase("a") ||
				s.equalsIgnoreCase("e") ||
				s.equalsIgnoreCase("i") ||
				s.equalsIgnoreCase("o") ||
				s.equalsIgnoreCase("u") || s.length() != 1);
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
		do {
			s = Prompt.getString("Enter a vowel -> ");
		} while (!(s.equalsIgnoreCase("a") ||
				s.equalsIgnoreCase("e") ||
				s.equalsIgnoreCase("i") ||
				s.equalsIgnoreCase("o") ||
				s.equalsIgnoreCase("u")) || s.length() != 1);
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
		String guess = Prompt.getString("Enter the word -> ");
		if (guess.equalsIgnoreCase(word)) {
			System.out.println("That's correct! The word is " + word.toUpperCase());
			knownLetters = new String(word);
		}
		return knownLetters;
	}
}