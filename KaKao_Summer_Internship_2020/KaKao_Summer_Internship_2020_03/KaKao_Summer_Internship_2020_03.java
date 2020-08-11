package KaKao_Summer_Internship_2020_03;

import java.util.HashMap;
import java.util.LinkedList;

public class KaKao_Summer_Internship_2020_03 {
	public static void main(String args[]) {
//		int []res = solution(new String[] {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"});
//		int []res = solution(new String[] {"AA", "AB", "AC", "AA", "AC"});
//		int []res = solution(new String[] {"XYZ", "XYZ", "XYZ"});
		int []res = solution(new String[] {"ZZZ", "YYY", "NNNN", "YYY", "BBB"});
		System.out.println(res[0]);
		System.out.println(res[1]);
	}
	
	private static HashMap <String, Integer> gemMap = new HashMap <>();
	public static class Node {
		String gem;
		int index;
		public Node(String gem, int index) {
			this.gem = gem;
			this.index = index;
		}
	}
	
	public static int[] solution(String[] gems) {
		int leng = gems.length;
		for(int i=0; i<leng; ++i) {
			if(!gemMap.containsKey(gems[i])) {
				gemMap.put(gems[i], 0);
			}
		}
		
		int unique = gemMap.size();
		int counter = 0;
		int resLeng = Integer.MAX_VALUE;
		int[] answer = new int[2];
		
		LinkedList <Node> queue = new LinkedList <>();
		for(int i=0; i<leng; ++i) {
			String gem = gems[i];
			
			if(gemMap.get(gem) == 0) {
				++counter;
			}
			
			gemMap.put(gem, gemMap.get(gem) + 1);
			queue.add(new Node(gem, i));
			
			while(true) {
				Node peekGem = queue.peekFirst();
				if(gemMap.get(peekGem.gem) > 1) {
					queue.pollFirst();
					gemMap.put(peekGem.gem, gemMap.get(peekGem.gem) - 1);
				} else {
					break;
				}
			}
			
			if(counter == unique) {
				Node peekFirstGem = queue.peekFirst();
				Node peekLastGem = queue.peekLast();
				int diff = peekLastGem.index - peekFirstGem.index;
				if(resLeng > diff) {
					resLeng = diff;
					answer[0] = peekFirstGem.index + 1;
					answer[1] = peekLastGem.index + 1;
				}
			}
			
		}
		
        return answer;
    }
}
