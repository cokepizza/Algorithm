package Mordor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Mordor {
	public static class Node {
		int max, min;
		public Node(int max, int min) {
			this.max = max;
			this.min = min;
		}
	}
	
	private static Node[] segTree;
	private static int[] h;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<C; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			h = new int[N];
			segTree= new Node[4*N];
			
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; ++j) {
				h[j] = Integer.parseInt(st.nextToken());
			}
			
			initSegTree(0, N-1, 1);
			
			for(int j=0; j<Q; ++j) {
				st = new StringTokenizer(br.readLine());
				int ds = Integer.parseInt(st.nextToken());
				int de = Integer.parseInt(st.nextToken());
				Node node = querySegTree(ds, de, 0, N-1, 1);
				sb.append(node.max - node.min + "\n");
			}
		}
		System.out.print(sb.substring(0, sb.length() - 1));
	}

	private static Node querySegTree(int ds, int de, int s, int e, int index) {
 		if(ds <= s && e <= de) return segTree[index];
		if(de < s || e < ds) return new Node(Integer.MIN_VALUE, Integer.MAX_VALUE);
		
		int m = (s + e) / 2;
		Node left = querySegTree(ds, de, s, m, index*2);
		Node right = querySegTree(ds, de, m+1, e, index*2+1);
		return new Node(Math.max(left.max, right.max), Math.min(left.min,  right.min));
	}

	private static Node initSegTree(int s, int e, int index) {
		if(s == e) return segTree[index] = new Node(h[s], h[s]);
		
		int m = (s + e) / 2;
		Node left = initSegTree(s, m, index*2);
		Node right = initSegTree(m+1, e, index*2+1);
		return segTree[index] = new Node(Math.max(left.max, right.max), Math.min(left.min,right.min));
	}
}
