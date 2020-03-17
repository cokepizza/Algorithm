package SoLong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SoLong_Trie {
	public static class Trie {
		Trie []children;
		int priority;
		String word;
		public Trie() {
			this.children = new Trie[26];
			this.priority = -1;
			this.word = "";
		}
		
		public void insert(String word, int index, int priority) {
			if(index != 0 && this.priority < priority) {
				this.priority = priority;
				this.word = word;
			} else if(index !=0 && this.priority == priority) {
				if(this.word.compareTo(word) > 0) {
					this.word = word;
				}
			}
			
			if(word.length() == index) return;
			
			int next = word.charAt(index) - 'A';
			if(this.children[next] == null) {
				this.children[next] = new Trie();
			}
			
			this.children[next].insert(word, index+1, priority);
		}
		
		public int find(String word, int index) {
			if(word.length() == index) return 0;
			if(this.word.equals(word)) return 1;
			
			int next = word.charAt(index) - 'A';
			if(this.children[next] == null) return word.length() - index;  
			
			return this.children[next].find(word, index+1) + 1;
		}
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<t; ++i) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			Trie root = new Trie();
			int count = 0;
			for(int j=0; j<n; ++j) {
				st = new StringTokenizer(br.readLine());
				String word = st.nextToken();
				int priority = Integer.parseInt(st.nextToken());
				root.insert(word, 0, priority);
			}
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; ++j) {
				String word = st.nextToken();
				count += root.find(word, 0);
			}
			sb.append((count + m - 1) + "\n");
		}
		System.out.print(sb.substring(0, sb.length() - 1));
	}
}
