package Round_2020_3_26;

import java.io.*;
import java.util.*;
 
//	recursive stackoverflow

public class x_B_Kth_Beautiful_String_Recur {
	private static long [][]dp;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i=0; i<t; ++i) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			dp = new long[n-1][3];
			for(int j=0; j<n-1; ++j) {
				Arrays.fill(dp[j], -1);
			}
			String res = getString(n-2, 2, k);
			System.out.println(res);
		}
	}
 
	private static String getString(int a, int b, long k) {
		long minusA = getNum(a-1, b);
		long minusB = getNum(a, b-1);
		if(minusA > 0 && k <= minusA) {
			return "a" + getString(a-1, b, k);
		} else if(minusB > 0){
			return "b" + getString(a, b-1, k-minusA);
		} else {
			return "";
		}
	}
	
	private static long getNum(int a, int b) {
		if(a == 0 && b == 0) return 1;
		if(a < 0 || b < 0) return 0;
		if(dp[a][b] != -1) return dp[a][b];
		
		return dp[a][b] = getNum(a-1, b) + getNum(a, b-1);
	}
}