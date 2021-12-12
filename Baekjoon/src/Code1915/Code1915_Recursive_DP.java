package Code1915;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//	재귀dp
//	https://www.acmicpc.net/problem/1915

public class Code1915_Recursive_DP {
	public static int [][]dp;
	public static int [][]arr;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		arr = new int[y][x];
		dp = new int[y][x];
		
		for(int i=0; i<y; ++i) {
			String pop = br.readLine();
			for(int j=0; j<x; ++j) {
				arr[i][j] = pop.charAt(j) == '0' ? 0 : 1;
				Arrays.fill(dp[i], -1);
			}
		}
		
		int maxSize = Integer.MIN_VALUE;
		for(int i=0; i<y; ++i) {
			for(int j=0; j<x; ++j) {
				maxSize = Math.max(maxSize, solve(i, j));
			}
		}

		System.out.println(maxSize * maxSize);
	}

	private static int solve(int y, int x) {
		if(y < 0 || x < 0) return 0;
		if(arr[y][x] == 0) return dp[y][x] = 0;
		if(dp[y][x] != -1) return dp[y][x];
		
		int prev = solve(y-1, x-1);
		
		int bound;
		for(bound=1; bound<=prev; ++bound) {
			if(arr[y-bound][x] == 0 || arr[y][x-bound] == 0) {
				break;
			}
		}
		
		return dp[y][x] = bound;
	}
}
