package KCode7;

import java.util.LinkedList;

public class KCode7 {
	public static class Node {
		int y, x;
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	private static int [][]dir = {{0, 0, 1, -1}, {1, -1, 0, 0}};
	public static void main(String args[]) {
		int result = Solution(new int[][] {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, { 0, 1, 0, 1, 1 }, { 1, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0 }});
		System.out.println(result);
	}
	
	public static int Solution (int [][]board) {
		int n = board.length;
		LinkedList <Node> list = new LinkedList <>();
		list.add(new Node(0, 1));
		
		return 0;
	}
}
