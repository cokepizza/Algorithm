package Round_2020_3_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_Yet_Another_Tetris_Problem {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<t; ++i) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int minHeight = 1000000;
			int []a = new int[n];
			for(int j=0; j<n; ++j) {
				a[j] = Integer.parseInt(st.nextToken());
				minHeight = Math.min(minHeight, a[j]);
			}
			boolean answer = true;
			for(int j=0; j<n; ++j) {
				if((a[j] - minHeight) % 2 != 0) {
					answer = false;
					break;
				}
			}
			sb.append((answer ? "YES" : "NO") + "\n");
		}
		System.out.print(sb.substring(0, sb.length() - 1));
	}
}
