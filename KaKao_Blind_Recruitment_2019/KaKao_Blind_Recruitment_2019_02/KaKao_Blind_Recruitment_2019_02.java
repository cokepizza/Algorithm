package KaKao_Blind_Recruitment_2019_02;

import java.util.Arrays;
import java.util.Comparator;

//	실패율
//	https://programmers.co.kr/learn/courses/30/lessons/42889

public class KaKao_Blind_Recruitment_2019_02 {
	public static void main(String args[]) {
//		int []res = solution(5, new int[] {2, 1, 2, 6, 2, 4, 3, 3});
		int []res = solution(7, new int[] {5, 4, 1, 1, 1, 2, 3, 3});
//		int []res = solution(4, new int[] {4, 4, 4, 4, 4});
		
		for(int pop: res) {
			System.out.println(pop);
		}
	}
	
	public static class Node {
		int stage;
		double ratio;
		public Node(int stage, double ratio) {
			this.stage = stage;
			this.ratio = ratio;
		}
	}
	
	public static int[] solution(int N, int[] stages) {
		Arrays.sort(stages);
		Node[] node = new Node[N];
		int leng = stages.length;
		for(int i=1; i<=N; ++i) {
			int low = lowerBound(stages, 0, leng, i);
			int high = upperBound(stages, 0, leng, i);
			double failureRate = (double)(high - low) / (double)(leng - low);
			if(leng - low == 0) {
				failureRate = 0;
			} else {
				failureRate = (double)(high - low) / (double)(leng - low);
			}
			
			node[i-1] = new Node(i, failureRate);
		}
		
		Arrays.sort(node, new Comparator <Node>() {
			@Override
			public int compare(Node o1, Node o2) { 
				if(o1.ratio < o2.ratio) return 1;
				else if(o1.ratio == o2.ratio) {
					if(o1.stage > o2.stage) return 1;
					else return -1;
				}
				else return -1;
			}
		});
		
		int[] answer = new int[N];
		int index = 0;
		for(Node pop: node) {
			answer[index++] = pop.stage;
		}
		
		return answer;
	}

	private static int upperBound(int[] arr, int s, int e, int target) {
		if(s >= e) return s;
		int m = (s + e) / 2;
		if(arr[m] <= target) return upperBound(arr, m+1, e, target);
		else return upperBound(arr, s, m, target);
	}

	private static int lowerBound(int[] arr, int s, int e, int target) {
		if(s >= e) return s;
		int m = (s + e) / 2;
		if(arr[m] < target) return lowerBound(arr, m+1, e, target);
		else return lowerBound(arr, s, m, target);
	}
}
