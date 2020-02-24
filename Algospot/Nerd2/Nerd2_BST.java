package Nerd2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Nerd2_BST {
	public static class Node {
		int p, q;
		public Node(int p, int q) {
			this.p = p;
			this.q = q;
		}
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<t; ++i) {
			int n = Integer.parseInt(br.readLine());
			TreeSet <Node> treeSet = new TreeSet<>(new Comparator<Node>() {
				@Override
				public int compare(Node arg0, Node arg1) {
					return arg0.p - arg1.p;
				}
			});
			
			int count = 0;
			for(int j=0; j<n; ++j) {
				st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken());
				int q = Integer.parseInt(st.nextToken());
				Node node = new Node(p, q);
			
				Node upper = treeSet.ceiling(node);
				if(upper != null) {
					if(upper.q > node.q) {
						count += treeSet.size();
						continue;
					}
				}
				
				Node pop;
				while((pop = treeSet.floor(node)) != null) {
					if(pop.q < node.q) {
						treeSet.remove(pop);	
					} else {
						break;
					}
				}
				
				treeSet.add(node);
				count += treeSet.size();
			}
			sb.append(count + "\n");
		}
		System.out.print(sb.substring(0, sb.length() - 1));
	}
}
