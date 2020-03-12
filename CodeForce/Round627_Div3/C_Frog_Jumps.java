package Round627_Div3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C_Frog_Jumps {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int i=0; i<t; ++i) {
			String word = br.readLine();
			int leng = word.length();
			int l = 0, maxLeng = 0;
			for(int j=0; j<leng; ++j) {
				if(word.charAt(j) == 'L') {
					++l;
				} else {
					l = 0;
				}
				maxLeng = Math.max(maxLeng, l);
			}
			System.out.println(maxLeng + 1);
		}
	}
}
