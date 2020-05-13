package KaKao_Winter_Internship_2019_01;

import java.util.LinkedList;

public class KaKao_Winter_Internship_2019_01 {
	public static void main(String args[]) {
		int res = solution(new int[][] {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}}, new int[] {1,5,3,5,1,2,1,4});
		System.out.println(res);
	}
	
	public static int solution(int[][] board, int[] moves) {
		int height = board.length;
		int leng = moves.length;
		LinkedList <Integer> queue = new LinkedList <>();
		int count = 0;
		
		for(int i=0; i<leng; ++i) {
			int move = moves[i] - 1;
			int doll = -1;
			for(int j=0; j<height; ++j) {
				doll = board[j][move];
				if(doll != 0) {
					board[j][move] = 0;
					break;
				}
			}
			
			if(doll != 0) {
				int peek = queue.size() > 0 ? queue.peekLast() : -1;
				if(peek == doll) {
					count += 2;
					queue.pollLast();
				} else {
					queue.add(doll);
				}
			}
		}
		
        return count;
    }
}
