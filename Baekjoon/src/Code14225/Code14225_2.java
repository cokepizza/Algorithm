package Code14225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//	https://www.acmicpc.net/problem/14225
//	부분수열을 재귀없이 for문으로 생성

public class Code14225_2 {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int []s = new int[n];
		int maxSum = 2000001;
		boolean []visited = new boolean[maxSum];
		
		for(int i=0; i<n; ++i) {
			s[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<(1 << n); ++i) {
			int sum = 0;
			for(int j=0; j<n; ++j) {
				if((i & (1<<j)) != 0) {
					sum += s[j];
				}
			}
			visited[sum] = true;
		}
		
		int ans = -1;
		for(int i=0; i<maxSum; ++i) {
			if(!visited[i]) {
				ans = i;
				break;
			}
		}
		
		System.out.println(ans);
	}
}
