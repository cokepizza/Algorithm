package EditorWars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EditorWars {
	private static int []parent;
	private static int maxGroup;
	private static int unknown;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<t; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			parent = new int[n];
			Arrays.fill(parent, -1);
			maxGroup = 0;
			unknown = n;
			
			int pass = -1;
			for(int j=0; j<m; ++j) {
				st = new StringTokenizer(br.readLine());
				String order = st.nextToken();
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(order.equals("ACK")) {
					merge(a, b);
				} else {
					if(find(a) == find(b)) {
						pass = j;
					} else {
//						merge()
					}
				}	
			}
			
			if(pass != -1) {
				sb.append("CONTRADICTION AT " + (pass+1) + "\n");
			} else {
				sb.append("MAX PARTY SIZE IS "+ (unknown + maxGroup) + "\n");
			}
		}
		System.out.print(sb.substring(0, sb.length() - 1));
	}
	
	private static void merge(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		if(parentA != parentB) {
			if(parent[parentA] == -1) unknown += parent[parentA];
			if(parent[parentB] == -1) unknown += parent[parentB];
			
			if(parent[parentA] < parent[parentB]) {
				parent[parentA] += parent[parentB];
				parent[parentB] = parentA;
				maxGroup = Math.max(maxGroup, -parent[parentA]);
			} else {
				parent[parentB] += parent[parentA];
				parent[parentA] = parentB;
				maxGroup = Math.max(maxGroup, -parent[parentB]);
			}
		}
	}

	private static int find(int idx) {
		if(parent[idx] < 0) return idx;
		return parent[idx] = find(parent[idx]);
	}
}
