package Round_2020_3_26;

import java.io.*;
import java.util.*;

public class A_Divisibility_Problem {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<t; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int left = a % b;
			if(left == 0) {
				sb.append(0 + "\n");
			} else {
				sb.append(b - left + "\n");
			}
		}
		System.out.print(sb.substring(0, sb.length() - 1));
	}
}
