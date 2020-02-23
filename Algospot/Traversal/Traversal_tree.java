package Traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Traversal_tree {
	private static int[] preOrder, inOrder;
	private static int preIndex;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<t; ++i) {
			int n = Integer.parseInt(br.readLine());
			preOrder = new int[n];
			inOrder = new int[n];
			preIndex = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; ++j) {
				preOrder[j] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; ++j) {
				inOrder[j] = Integer.parseInt(st.nextToken());
			}
			
			postOrder(0, n-1);
			System.out.println();
		}
	}
	
	private static void postOrder(int s, int e) {
		if(s > e) return;
		
		int idx;
		for(idx=s; idx<e; ++idx) {
			if(inOrder[idx] == preOrder[preIndex]) break;
		}
		
		++preIndex;
		postOrder(s, idx-1);
		postOrder(idx+1, e);
		System.out.print(inOrder[idx] + " ");
	}
}
