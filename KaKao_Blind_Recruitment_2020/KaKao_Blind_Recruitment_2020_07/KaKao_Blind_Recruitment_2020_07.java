package KaKao_Blind_Recruitment_2020_07;

import java.util.LinkedList;

//	블럭 이동하기
//	https://programmers.co.kr/learn/courses/30/lessons/60063

public class KaKao_Blind_Recruitment_2020_07 {
	public static class Node {
		int y1, x1, y2, x2;
		public Node(int y1, int x1, int y2, int x2) {
			this.y1 = y1;
			this.x1 = x1;
			this.y2 = y2;
			this.x2 = x2;
		}
	}
	
	private static int [][]moveDir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	private static boolean [][][][]visitied;
	private static int n;
	private static LinkedList <Node> queue;
	private static int [][]board;
	public static void main(String args[]) {
		int result = solution(new int[][] {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, { 0, 1, 0, 1, 1 }, { 1, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0 }});
		System.out.println(result);
	}
	
	public static int solution (int [][]preBoard) {
		queue = new LinkedList <>();
		queue.add(new Node(0, 0, 0, 1));
		
		board = preBoard;
		n = board.length;		
		visitied = new boolean[n][n][n][n];
		int count = 0;
		loop: while(!queue.isEmpty()) {
			int qSize = queue.size();
			for(int c=0; c<qSize; ++c) {
				
				Node node = queue.pop();
				int y1 = node.y1;
				int x1 = node.x1;
				int y2 = node.y2;
				int x2 = node.x2;
				
				if((y1 == n-1 && x1 == n-1) || (y2 == n-1 && x2 == n-1)) {
					break loop;
				}
				
				for(int i=0; i<4; ++i) {
					int nextY1 = y1 + moveDir[i][0];
					int nextX1 = x1 + moveDir[i][1];
					int nextY2 = y2 + moveDir[i][0];
					int nextX2 = x2 + moveDir[i][1];
					if(checkBoard(nextY1, nextX1, nextY2, nextX2)) applyMoveRuleAndAddQueue(nextY1, nextX1, nextY2, nextX2);
				}
				
				int yDiff = y2 - y1;
				int xDiff = x2 - x1;
				
				//	가로
				if(yDiff == 0) {
					//	축: X2
					if(checkBoard(y1-1, x2, y2, x2)) applyRotationRuleAndAddQueue(y1-1, x2, y2, x2, y1-1, x1);
					if(checkBoard(y1+1, x2, y2, x2)) applyRotationRuleAndAddQueue(y1+1, x2, y2, x2, y1+1, x1);		
					
					//	축: X1
					if(checkBoard(y1, x1, y2-1, x1)) applyRotationRuleAndAddQueue(y1, x1, y2-1, x1, y2-1, x2);
					if(checkBoard(y1, x1, y2+1, x1)) applyRotationRuleAndAddQueue(y1, x1, y2+1, x1, y2+1, x2);
				}
				
				//	세로
				if(xDiff == 0) {
					//	축: Y2
					if(checkBoard(y2, x1-1, y2, x2)) applyRotationRuleAndAddQueue(y2, x1-1, y2, x2, y1, x1-1);
					if(checkBoard(y2, x1+1, y2, x2)) applyRotationRuleAndAddQueue(y2, x1+1, y2, x2, y1, x1+1);		
					
					//	축: Y1
					if(checkBoard(y1, x1, y1, x2-1)) applyRotationRuleAndAddQueue(y1, x1, y1, x2-1, y2, x2-1);
					if(checkBoard(y1, x1, y1, x2+1)) applyRotationRuleAndAddQueue(y1, x1, y1, x2+1, y2, x2+1);
				}
			}
			++count;
		}
		return count;
	}
	
	public static boolean checkBoard(int nextY1, int nextX1, int nextY2, int nextX2) {
		if(nextY1 > nextY2 || nextX1 > nextX2) return checkBoard(nextY2, nextX2, nextY1, nextX1);
		
		if(nextY1 < 0 || nextX1 < 0 || nextY1 >= n || nextX1 >= n) return false;
		if(nextY2 < 0 || nextX2 < 0 || nextY2 >= n || nextX2 >= n) return false;
		
		return true;
	}
	
	public static void applyMoveRuleAndAddQueue(int nextY1, int nextX1, int nextY2, int nextX2) {
		if(nextY1 > nextY2 || nextX1 > nextX2) {
			applyMoveRuleAndAddQueue(nextY2, nextX2, nextY1, nextX1);
			return;
		}
		
		if(board[nextY1][nextX1] == 0 && board[nextY2][nextX2] == 0 && !visitied[nextY1][nextX1][nextY2][nextX2]) {
			visitied[nextY1][nextX1][nextY2][nextX2] = true;
			queue.add(new Node(nextY1, nextX1, nextY2, nextX2));
		}
	}
	
	public static void applyRotationRuleAndAddQueue(int nextY1, int nextX1, int nextY2, int nextX2, int holeY, int holeX) {
		if(nextY1 > nextY2 || nextX1 > nextX2) {
			applyRotationRuleAndAddQueue(nextY2, nextX2, nextY1, nextX1, holeY, holeX);
			return;
		}
		
		if(board[holeY][holeX] == 0 && board[nextY1][nextX1] == 0 && board[nextY2][nextX2] == 0 && !visitied[nextY1][nextX1][nextY2][nextX2]) {
			visitied[nextY1][nextX1][nextY2][nextX2] = true;
			queue.add(new Node(nextY1, nextX1, nextY2, nextX2));
		}
	}
}
