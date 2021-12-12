package Code1915;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//	for문 dp
//	https://www.acmicpc.net/problem/1915

public class Code1915_2_Forloop_DP {
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
			}
		}
		
		int maxSize = Integer.MIN_VALUE;
		for(int i=0; i<y; ++i) {
			for(int j=0; j<x; ++j) {
				if(arr[i][j] == 0) continue;
				
				int k;
				for(k=1; k<=(i-1 >= 0 && j-1 >= 0 ? dp[i-1][j-1] : 0); ++k) {
					if(arr[i-k][j] == 0 || arr[i][j-k] == 0) break;
				}
				
				dp[i][j] = k;
				maxSize = Math.max(maxSize, k);
			}
		}
		
		System.out.println(maxSize * maxSize);
	}
}
