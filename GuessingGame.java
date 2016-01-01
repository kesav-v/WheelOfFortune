import java.util.ArrayList;
import java.util.Scanner;

public class GuessingGame {
	public static void main(String[] args) {
		ArrayList<String> words = new ArrayList<String>(200000);
		Scanner input = OpenFile.openToRead("wordlist.txt");
		while (input.hasNext()) {
			words.add(input.nextLine());
		}
		int ran = (int)(Math.random() * words.size());
		String word = words.get(ran);
		String knownLetters = new String();
		for (int i = 0; i < word.length(); i++) {
			knownLetters += "-";
		}
		boolean[] letters = new boolean[word.length()];
		for (int i = 0; i < letters.length; i++) {
			letters[i] = false;
		}
		int totalMoney = 0;
		do {
			System.out.println(knownLetters);
			System.out.println("You have $" + totalMoney + " so far");
			int num;
			while (true) {
				num = Prompt.getInt("Enter 1 to spin, 2 to buy a vowel, or 3 to solve the puzzle -> ", 1, 3);
				if (num == 2 && totalMoney < 100) continue;
				break;
			}
			Prompt.kb.nextLine();
			switch (num) {
				case 1:
				String s;
				int money = 100 * ((int)(Math.random() * 26) + 5);
				System.out.println("$" + money);
				do {
					s = Prompt.getString("Enter a consonant -> ");
				} while (s.equalsIgnoreCase("a") ||
						s.equalsIgnoreCase("e") ||
						s.equalsIgnoreCase("i") ||
						s.equalsIgnoreCase("o") ||
						s.equalsIgnoreCase("u") || s.length() != 1);
				knownLetters = addLetters(s.toUpperCase(), knownLetters.toUpperCase(), word.toUpperCase(), letters);
				totalMoney += money * cntConsonants(s, word);
				break;
				case 2:
				String s1;
				do {
					s1 = Prompt.getString("Enter a vowel -> ");
				} while (!(s1.equalsIgnoreCase("a") ||
						s1.equalsIgnoreCase("e") ||
						s1.equalsIgnoreCase("i") ||
						s1.equalsIgnoreCase("o") ||
						s1.equalsIgnoreCase("u")) || s1.length() != 1);
				totalMoney -= 100;
				knownLetters = addLetters(s1.toUpperCase(), knownLetters.toUpperCase(), word.toUpperCase(), letters);
				break;
				case 3:
				String s2 = Prompt.getString("Enter the word -> ");
				if (s2.equals(word)) {
					for (int i = 0; i < letters.length; i++) {
						letters[i] = true;
					}
				}
				break;
			}
		} while (!allTrue(letters));
		System.out.println("\n" + word.toUpperCase());
	}

	public static boolean allTrue(boolean[] bs) {
		for (boolean b : bs) {
			if (!b) return false;
		}
		return true;
	}

	public static String addLetters(String l, String s, String word, boolean[] letters) {
		char[] cs = s.toCharArray();
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == l.charAt(0)) {
				cs[i] = l.charAt(0);
				letters[i] = true;
			}
		}
		return new String(cs);
	}

	public static int cntConsonants(String s, String word) {
		int cnt = 0;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == s.charAt(0)) cnt++;
		}
		return cnt;
	}
}