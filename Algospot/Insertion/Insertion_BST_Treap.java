package Insertion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Insertion_BST_Treap {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int i=0; i<t; ++i) {
			int n = Integer.parseInt(br.readLine());
			int []arr = new int[n+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			TreeMap <Integer, Integer> treeMap = new TreeMap<>();
			for(int j=1; j<=n; ++j) {
				treeMap.put(j, j);
			}
			
			for(int j=1; j<=n; ++j) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			
			for(int j=n; j>=2; --j) {
				int jValue = treeMap.remove(j);
				int arrValue = treeMap.remove(j-arr[j]);
				treeMap.put(j-arr[j], jValue);
				treeMap.put(j, arrValue);
			}
			
			System.out.println(treeMap);
			
		}
	}
}
