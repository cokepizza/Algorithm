//	시간초과 로직

package Code1339;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class xCode1339 {
	private static HashSet <Integer> pushed;
	private static ArrayList <Integer> list;
	private static int[]alpha = new int[26];
	private static int max = Integer.MIN_VALUE;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		pushed = new HashSet<>();
		
		for(int i=0; i<n; ++i) {
			String word = br.readLine();
			int leng = word.length();
			for(int j=0; j<leng; ++j) {
				int index = word.charAt(j) - 65;
				alpha[index] += (int)(Math.pow(10, leng - j - 1));
				pushed.add(index);
			}
		}
		
		list = new ArrayList <>(pushed);
		getSum(0, 0);
		System.out.print(max);
	}

	private static void getSum(int visitied, long seat) {
		int count = Integer.bitCount(visitied);
		if(count == list.size()) {
			int value = 0;
			for(int i=0; i<count; ++i) {
				int num = list.get(i);
				value += alpha[num] * (seat % (long)Math.pow(10, i+1) / (long)Math.pow(10, i));
			}
			max = Math.max(max, value);
			return;
		}
		
		for(int i=0; i<10; ++i) {
			if((visitied & (1<<i)) == 0) {
				getSum((visitied | (1<<i)), seat + ((long)Math.pow(10, count) * i));
			}
		}
		
	}
}
