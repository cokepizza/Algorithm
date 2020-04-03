package KCode4;

import java.util.ArrayList;
import java.util.HashMap;

public class KCode4 {
	public static class Trie {
		Trie[] alpha;
		HashMap <Integer, Integer> countMap;
		public Trie () {
			alpha = new Trie[26];
			countMap = new HashMap<>();
		}
		
		public void add(String word, int index) {
			if(word.length() == index) return;
			int next = word.charAt(index) - 'a';
			if(this.alpha[next] == null) {
				this.alpha[next] = new Trie();
			}
			
			int diff = word.length() - index - 1;
			if(this.countMap.containsKey(diff)) {
				this.countMap.put(diff, this.countMap.get(diff)+1);
			} else {
				this.countMap.put(diff, 1);
			}
			this.alpha[next].add(word, index + 1);
		}
		
		public int find(String word, int index) {
			if(word.charAt(index+1) == '?') {
				int diff = word.length() - index-1;
				return this.countMap.get(diff);
			}
			
			int next = word.charAt(index) - 'a';
			if(this.alpha[next] == null) {
				return 0;
			} else {
				return this.alpha[next].find(word, index + 1);	
			}
		}
	}
	
	public static void main(String args[]) {
//		int[] res = solution(new String[] {"frodo", "front", "frost", "frozen", "frame", "kakao"}, new String[] {"fro??", "????o", "fr???", "fro???", "pro?"});
		int[] res = solution(new String[] {"frodo", "front", "frost", "frozen", "frame", "kakao"}, new String[] {"fro??"});
		for(int i=0; i<res.length; ++i) {
			System.out.println(res[i]);
		}
	}
	
    public static int[] solution(String[] words, String[] queries) {
    	int leng = words.length;
    	Trie trie = new Trie();
    	Trie reverseTrie = new Trie();
    	
    	for(int i=0; i<leng; ++i) {
    		String word = words[i];
    		trie.add(word, 0);
    		
    		StringBuilder reverser = new StringBuilder();
    		for(int j=word.length()-1; j >=0; --j) {
    			reverser.append(word.charAt(j));
    		}
    		reverseTrie.add(reverser.reverse().toString(), 0);
    	}
    	
    	int queryLeng = queries.length;
    	ArrayList <Integer> resArr = new ArrayList<>();
    	for(int i=0; i<queryLeng; ++i) {
    		String query = queries[i];
    		if(query.charAt(0) == '?') {
    			
    		} else {
    			int res = trie.find(query, 0);
    			resArr.add(res);
    		}
    	}
    	
    	int[] answer = new int[resArr.size()];
    	int index = 0;
    	for(int pop : resArr) {
    		answer[index++] = pop;
    	}
    	
        return answer;
    }
}
