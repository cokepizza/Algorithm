package Round_2020_3_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
x
public class D1_Prefix_Suffix_Palindrome_Easy {
	private static String word;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for(int i=0; i<n; ++i) {
			word = br.readLine();
			String ans = getMax(0, word.length()-1);
			if(ans.equals("")) ans = String.valueOf(word.charAt(0));
			System.out.println(ans);
		}
	}

	private static String getMax(int s, int e) {
		if(s > e) return "";
		char peek = word.charAt(s);
		char pop = word.charAt(e);
		if(s == e) return String.valueOf(peek);
		
		if(peek == pop) {
			return peek + getMax(s+1, e-1) + pop;
		} else {
			String middle = "";
			for(int i=s+1; i<=e-1; ++i) {
				if(peek == word.charAt(i)) {
					boolean left = palin(s, i);
					if(left && middle.length() < i-s+1) {
						middle = word.substring(s, i+1);
					}
				}	
				if(pop == word.charAt(i)) {
					boolean right = palin(i, e);
					if(right && middle.length() < e-i+1) {
						middle = word.substring(i, e+1);
					}
				}
			}
			return middle;
		}
	}

	private static boolean palin(int s, int e) {
		if(s >= e) return true;
		if(word.charAt(s) == word.charAt(e)) {
			return palin(s+1, e-1);
		}
		return false;
	}
}
