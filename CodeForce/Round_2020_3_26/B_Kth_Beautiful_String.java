package Round_2020_3_26;

import java.io.*;
import java.util.*;

//	점화식을 바꾸고 문자열 생성과정을 재귀에서 for문으로 변경
//	long 자료형이지만 i*(i-1) 에서 Integer범위 overflow가 날 수 있기에 주의함

public class B_Kth_Beautiful_String {
	private static long [][]dp;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		dp = new long[100001][3];
		
		for(int i=0; i<100001; ++i) {
			for(int j=0; (j<3 && j <= i); ++j) {
				if(j == 0) dp[i][j] = 1;
				else if(j == 1) dp[i][j] = i;
				else if(j == 2) dp[i][j] = (long)i* (long)(i-1) / 2;
			}
		}
		
		
		for(int i=0; i<t; ++i) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			StringBuilder sb = new StringBuilder();
			int b = 2;
			while(n != 0 || b != 0) {
				long next = n-1 >= 0 ? dp[n-1][b] : 0;
				if(next > 0 && k <= next) {
					sb.append('a');
					n-=1;
				} else {
					sb.append('b');
					n-=1;
					b-=1;
					k-=next;
					
				}
			}
			String res = sb.toString();
			System.out.println(res);
		}
	}
}
