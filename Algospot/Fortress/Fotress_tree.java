package Fortress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Fotress_tree {
	public static class Tree {
		int x, y, r;
		public Tree(int x, int y, int r) {
			this.x = x;
			this.y = y;
			this.r = r;
		}
	}
	
	private static int []parent, height;
	private static Tree[]wall;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<t; ++i) {
			int n = Integer.parseInt(br.readLine());
			wall = new Tree[n];
			parent = new int[n];
			height = new int[n];
			Arrays.fill(parent, -1);
			StringTokenizer st;
			for(int j=0; j<n; ++j) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				wall[j] = new Tree(x, y, r);
			}
			
			Arrays.sort(wall, new Comparator <Tree>() {
				@Override
				public int compare(Tree o1, Tree o2) {
					return o2.r - o1.r;
				}
			});
			
			for(int j=0; j<n; ++j) {
				getParent(j);
			}
			
			boolean []visitied = new boolean[n];
			for(int j=1; j<n; ++j) {
				visitied[parent[j]] = true;
			}
			
			//	leaf node
			ArrayList <Integer> list = new ArrayList <>();
			for(int j=0; j<n; ++j) {
				if(!visitied[j]) {
					list.add(j);
				}
			}
			
			list.add(0);
			int size = list.size();
			int max = -1;
			for(int j=0; j<size; ++j) {
				for(int k=j+1; k<size; ++k) {
					max = Math.max(max, getDistance(list.get(j), list.get(k)));
				}
			}
			sb.append(max + "\n");
		}
		System.out.print(sb.substring(0, sb.length() - 1));
	}
	
	private static int getDistance(int j, int k) {
		int diff = height[j] - height[k];
		if(diff < 0) return getDistance(k, j);
		int dist = diff;
		
		while(diff > 0) {
			--diff;
			j = parent[j];
		}
		
		
		if(j == k) return dist;
		
		while(j != k) {
			dist += 2;
			j = parent[j];
			k = parent[k];
		}
		
		return dist;
	}

	private static int getParent(int idx) {
		if(idx == 0) return idx;
		if(parent[idx] != -1) return parent[idx];
		for(int i=idx-1; i>=0; --i) {
			if(isInclude(wall[idx], wall[i])) {
				height[idx] = height[i] + 1;
				return parent[idx] = i;
			}
		}
		
		return -1;
	}

	private static boolean isInclude(Tree b, Tree s) {
		int d2 = (b.x - s.x) * (b.x - s.x) + (b.y - s.y) * (b.y - s.y);
		int r2r1 = (b.r - s.r) * (b.r - s.r);
		if(d2 < r2r1) {
			return true;
		}
		return false;
	}
}
