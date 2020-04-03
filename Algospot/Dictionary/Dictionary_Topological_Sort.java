package Dictionary;

import java.io.*;
import java.util.*;

public class Dictionary_Topological_Sort {
	private static ArrayList <ArrayList<Integer>> list;
	private static boolean [] visitied;
	private static boolean [] finished;
	private static StringBuilder result;
	private static boolean cycle;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int i=0; i<t; ++i) {
			list = new ArrayList <>();
			for(int j=0; j<26; ++j) {
				list.add(new ArrayList <>());
			}
			
			int n = Integer.parseInt(br.readLine());
			String origin = br.readLine();
			for(int j=1; j<n; ++j) {
				String target = br.readLine();
				compare(origin, target);
				origin = target;
			}
			
			result = new StringBuilder();
			visitied = new boolean[26];
			finished = new boolean[26];
			cycle = false;
			for(int j=0; j<26; ++j) {
				if(!visitied[j]) {
					topological_dfs(j);
				}
			}
			
			if(cycle) {
				System.out.println("INVALID HYPOTHESIS");
			} else {
				System.out.println(result.reverse().toString());	
			}
		}
	}
	
	private static void topological_dfs(int root) {
		visitied[root] = true;
		for(int pop : list.get(root)) {
			if(!visitied[pop]) {
				topological_dfs(pop);
			} else if(visitied[pop] && !finished[pop]) {
				cycle = true;
				return;
			}
		}
		finished[root] = true;
		result.append((char)(root + 'a'));
	}

	private static void compare(String origin, String target) {
		int index = 0;
		while(origin.length()-1 >= index && target.length()-1 >= index) {
			int ori = origin.charAt(index) - 'a';
			int tar = target.charAt(index) - 'a';
			if(ori == tar) {
				++index;
				continue;
			} else {
				list.get(ori).add(tar);
				return;
			}
		}
		
	}
}
