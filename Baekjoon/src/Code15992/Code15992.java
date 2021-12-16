package Code15992;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Code15992 {
	public static final int MOD = 1000000009;
	public static int [][]dp;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		dp = new int[1001][1001];
		for(int j=0; j<1001; ++j) {
			Arrays.fill(dp[j], -1);
		}
		for(int i=0; i<t; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int res = getNumberOfCases(n, m);
			System.out.println(res);
		}
	}
	
	private static int getNumberOfCases(int n, int m) {
		if(n < 0 || m < 0) return 0;
		if(n == 0) {
			if(m == 0) return 1;
			return 0;
		}
		if(dp[n][m] != -1) return dp[n][m];
		
		int cases = 0;
		
		for(int i=1; i<=3; ++i) {
			cases = (cases + getNumberOfCases(n-i, m-1)) % MOD;
		}
		
		return dp[n][m] = cases;
	}
}
