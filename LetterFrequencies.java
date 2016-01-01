import java.util.Scanner;
import java.util.Arrays;
import java.io.PrintWriter;

public class LetterFrequencies {
	public static void main(String[] args) {
		Scanner scan = OpenFile.openToRead("wordlist.txt");
		PrintWriter write = OpenFile.openToWrite("letterlist.txt");
		int[] letterNums = new int[26];
		for (int i = 0; i < letterNums.length; i++) {
			letterNums[i] = 0;
		}
		while (scan.hasNext()) {
			String s = scan.nextLine();
			for (int i = 0; i < s.length(); i++) {
				letterNums[s.charAt(i) - 'a']++;
			}
		}
		int[] original = Arrays.copyOf(letterNums, letterNums.length);
		Arrays.sort(letterNums);
		for (int i = letterNums.length - 1; i >= 0; i--) {
			write.println((char)(indexOf(letterNums[i], original) + 'a'));
		}
		write.close();
	}

	public static int indexOf(int i, int[] nums) {
		for (int j = 0; j < nums.length; j++) {
			if (nums[j] == i) return j;
		}
		return -1;
	}
}