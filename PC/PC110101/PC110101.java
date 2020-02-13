package PC110101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PC110101 {
	private static int []dp;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}

		while(st.hasMoreTokens()) {
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			dp = new int[3000000];
			int max = -1;
			for(int i=s; i<=e; ++i) {
				max = Math.max(max, getLength(i));
			}
			System.out.println(max);	
		}
	}

	private static int getLength(int i) {
		if(dp[i] != 0) return dp[i];
		if(i == 1) return 1; 
		
		if(i % 2 == 0) {
			return dp[i] = getLength(i/2) +1;
		} else {
			return dp[i] = getLength(i*3+1) +1;
		}
	}
	
}
