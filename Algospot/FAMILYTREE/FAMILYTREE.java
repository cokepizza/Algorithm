package FAMILYTREE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FAMILYTREE {
	private static int[][] cache;
	private static int[] depth;
	private static int MAX_SQUARD = 18;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		StringBuilder sb  = new StringBuilder();
		for(int c=0; c < C; ++c) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			cache = new int[N][MAX_SQUARD];
			depth = new int[N];
			Arrays.fill(depth, -1);
			depth[0] = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; ++i) {
				Arrays.fill(cache[i], -1);
			}
			
			for(int i=1; i<N; ++i) {
				cache[i][0] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=1; i<N; ++i) {
				depth[i] = getDepth(i);
			}
			
			for(int i=1; i < MAX_SQUARD; ++i) {
				for(int j=0; j < N; ++j) {
					if(cache[j][i-1] < 0) continue;
					cache[j][i] = cache[cache[j][i-1]][i-1];
				}
			}
			
			for(int i=0; i < Q; ++i) {
				st = new StringTokenizer(br.readLine());
				int q1 = Integer.parseInt(st.nextToken());
				int q2 = Integer.parseInt(st.nextToken());
				int far = getQuery(q1, q2);
				sb.append(far + "\n");	
			}
			
			
//			for(int i=0; i<N; ++i) {
//				for(int j=0; j< MAX_SQUARD; ++j) {
//					System.out.print(cache[i][j] + " ");
//				}
//				System.out.println();
//			}
		}
		System.out.print(sb.substring(0, sb.length() - 1));
	}

	private static int getDepth(int n) {
		if(depth[n] != -1) return depth[n];
		return depth[n] = getDepth(cache[n][0]) + 1;
	}

	private static int getQuery(int q1, int q2) {
		if(depth[q1] < depth[q2]) return getQuery(q2, q1);
		
		// q1 > q2
		int diff = depth[q1] - depth[q2];
		for(int i=MAX_SQUARD-1; i>=0; --i) {
			int height = 1 << i;
			if(diff - height < 0) continue;
			diff -= height;
			q1 = cache[q1][i];
		}
		
		// same depth
		for(int i=MAX_SQUARD-1; i>=0; --i) {
			if(cache[q1][i] == -1 || cache[q2][i] == -1) continue;
			if(cache[q1][i] == cache[q2][i]) return 1 << (i+1);
		}
		
		return 0;
	}
}
