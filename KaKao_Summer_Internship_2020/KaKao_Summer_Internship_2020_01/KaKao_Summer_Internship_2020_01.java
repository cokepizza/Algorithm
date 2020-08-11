package KaKao_Summer_Internship_2020_01;

import java.util.HashMap;

public class KaKao_Summer_Internship_2020_01 {
	public static void main(String args[]) {
//		String res = solution(new int[] {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right");
//		String res = solution(new int[] {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}, "left");
		String res = solution(new int[] {0, 0, 0}, "left");
		System.out.println(res);
	}
	
	public static class Node {
		int y, x;
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	private static HashMap <Integer, Node> hashMap;
	public static String solution(int[] numbers, String hand) {
		hashMap = new HashMap<>();
		hashMap.put(0, new Node(3, 1));
		hashMap.put(1, new Node(0, 0));
		hashMap.put(2, new Node(0, 1));
		hashMap.put(3, new Node(0, 2));
		hashMap.put(4, new Node(1, 0));
		hashMap.put(5, new Node(1, 1));
		hashMap.put(6, new Node(1, 2));
		hashMap.put(7, new Node(2, 0));
		hashMap.put(8, new Node(2, 1));
		hashMap.put(9, new Node(2, 2));
		
        int leng = numbers.length;
        String answer = "";
        
        Node left = new Node(3, 0);
        Node right = new Node(3, 2);
		for(int i=0; i<leng; ++i) {
			Node node = hashMap.get(numbers[i]);
			if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
				left = new Node(node.y, node.x);
				answer += "L";
			} else if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
				right = new Node(node.y, node.x);
				answer += "R";
			} else {
//				int leftDistance = (node.y - left.y) * (node.y - left.y) + (node.x - left.x) * (node.x - left.x);
//				int rightDistance = (node.y - right.y) * (node.y - right.y) + (node.x - right.x) * (node.x - right.x);
				int leftDistance = Math.abs(node.y - left.y) + Math.abs(node.x - left.x);
				int rightDistance = Math.abs(node.y - right.y) + Math.abs(node.x - right.x);
				
				if(leftDistance < rightDistance) {
					left = new Node(node.y, node.x);
					answer += "L";
				} else if(leftDistance > rightDistance) {
					right = new Node(node.y, node.x);
					answer += "R";
				} else {
					if(hand.equals("left")) {
						left = new Node(node.y, node.x);
						answer += "L";
					} else {
						right = new Node(node.y, node.x);
						answer += "R";
					}
				}
			}
		}
        return answer;
    }
}
