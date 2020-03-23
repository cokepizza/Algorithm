package Round_2020_3_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A_Bad_Ugly_Numbers {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<t; ++i) {
			int n = Integer.parseInt(br.readLine());
			if(n == 1) sb.append("-1\n");
			else {
				StringBuilder ssb = new StringBuilder();
				for(int j=0; j<n-1; ++j) {
					ssb.append("9");	
				}
				ssb.append("4");
				sb.append(ssb+"\n");
			}
		}
		System.out.println(sb.substring(0, sb.length() - 1));
	}
}