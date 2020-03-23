package Round_2020_3_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//	단순 DP
public class E_Sleeping_Schedule {
	private static int []a;
	private static int n, h, l, r;
	private static int [][]dp;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		a = new int[n];
		for(int i=0; i<n; ++i) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[n+1][h+1];
		for(int i=0; i<=n; ++i) {
			Arrays.fill(dp[i], -1);
		}
		
		int res = getOpt(0, 0);
		System.out.print(res);
	}

	private static int getOpt(int idx, int now) {
		if(idx == n) return 0;
		if(dp[idx][now] != -1) return dp[idx][now];
		
		int next = (a[idx] + now) % h;		
		int max = -1;
		if(l <= next && next <= r) {
			max = Math.max(max, getOpt(idx+1, (a[idx] + now) % h) + 1);	
		} else {
			max = Math.max(max, getOpt(idx+1, (a[idx] + now) % h));
		}
		
		int mNext = (a[idx]-1 + now) % h;
		if(l <= mNext && mNext <= r) {
			max = Math.max(max, getOpt(idx+1, (a[idx]-1 + now) % h) + 1);	
		} else {
			max = Math.max(max, getOpt(idx+1, (a[idx]-1 + now) % h));
		}
		
		return dp[idx][now] = max;
	}
}
