package Code12851;

import java.util.*;
import java.io.*;

//	메모리 초과
public class x_Code12851 {
	private static int k;
	private static final int MAX = 100000;
	private static int [][]dp;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		boolean []visitied = new boolean[MAX+1];
		LinkedList <Integer> queue = new LinkedList <>();
		queue.add(n);
		visitied[n] = true;
		
		int c = 0;
		loop: while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i=0; i<size; ++i) {
				int num = queue.poll();
				if(num == k) break loop;
				
				if(MAX >= num+1 && !visitied[num+1]) {
					visitied[num+1] = true;
					queue.add(num+1);
				}
				if(num-1 >= 0 && !visitied[num-1]) {
					visitied[num-1] = true;
					queue.add(num-1);
				}
				if(MAX >= num * 2 && !visitied[num*2]) {
					visitied[num*2] = true;
					queue.add(num * 2);
				}
			}
			++c;
		}
		
		System.out.println(c);
		dp = new int[MAX+1][c+1];
		for(int i=0; i<=MAX; ++i) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(getNum(n, c));
	}
	
	private static int getNum(int n, int c) {
		if(n < 0 || n > MAX || c < 0) return 0;
		if(n == k && c == 0) return 1;
		if(dp[n][c] != -1) return dp[n][c];
		
		int sum = 0;
		sum += getNum(n+1, c-1);
		sum += getNum(n-1, c-1);
		sum += getNum(n*2, c-1);
		
		return dp[n][c] = sum;
	}
}
