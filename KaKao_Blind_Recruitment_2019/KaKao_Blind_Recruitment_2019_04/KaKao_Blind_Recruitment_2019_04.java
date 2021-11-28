package KaKao_Blind_Recruitment_2019_04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//	무지의 먹방 라이브
//	https://programmers.co.kr/learn/courses/30/lessons/42891

public class KaKao_Blind_Recruitment_2019_04 {
	public static void main(String args[]) {
		int res = solution(new int[] {3,1,2}, 5);
		System.out.println(res);
	}
	
	public static class Node {
		int index;
		int value;
		public Node (int index, int value) {
			this.index = index;
			this.value = value;
		}
	}
    
    public static int solution(int[] food_times, long k) {
		int size = food_times.length;
		
		ArrayList <Node> list = new ArrayList <>();
		for(int i=0; i<size; ++i) {
			list.add(new Node(i+1, food_times[i]));
		}
		
		Collections.sort(list, new Comparator <Node> () {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.value - o2.value;
			}
		});
		
		return getOrder(k, list);
    }
    
    private static int getOrder(long k, ArrayList<Node> list) {
		int s = 0, minus = 0;
		int size = list.size();
		while(size > s) {
			Node node = list.get(s);
			if(node.value == minus) {
				++s;
				continue;
			}
			long cycleCount = (long)(node.value-minus) * (size-s); 
			if(k >= cycleCount) {
				k -= cycleCount;
				++s;
				minus = node.value;
				continue;
			}
			
			k = k % (size-s);
			ArrayList <Integer> res = new ArrayList <>();
			for(int i=s; i<size; ++i) {
				res.add(list.get(i).index);
			}
			Collections.sort(res);
			
			return res.get((int)k);
		}
		return -1;
	}
}
