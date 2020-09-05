package KaKao_Summer_Internship_2020_04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KaKao_Summer_Internship_2020_04 {
	public static class Node {
		int dist, y, x, d;
		public Node(int dist, int y, int x, int d) {
			this.dist = dist;
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
	
	public static void main(String args[]) {
//		int res = solution(new int[][] {{0,0,0},{0,0,0},{0,0,0}});
//		int res = solution(new int[][] {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0}, {0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}});
//		int res = solution(new int[][] {{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}});
		int res = solution(new int[][] {{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}});
		
		System.out.println(res);
		
	}

	private static int [][]dir = new int[][] {{0, 0, 1, -1}, {1, -1, 0, 0}};
	public static int solution(int[][] board) {
		int size = board.length;
		PriorityQueue <Node> queue = new PriorityQueue<>(new Comparator <Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.dist - o2.dist;
			}
		});
		
		queue.add(new Node(0, 0, 0, 0));
		queue.add(new Node(0, 0, 0, 2));
		
		int [][][]minDist = new int[size][size][4];
		for(int i=0; i<size; ++i) {
			for(int j=0; j<size; ++j) {
				Arrays.fill(minDist[i][j], Integer.MAX_VALUE);
			}
		}
		int dist = -1;
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			dist = node.dist;
			int y = node.y;
			int x = node.x;
			int d = node.d;
			if(y == size-1 && x == size-1) break;
			for(int i=0; i<4; ++i) {
				int dy = y + dir[0][i];
				int dx = x + dir[1][i];
				if(dy < 0 || dx < 0 || dy >= size || dx >= size || board[dy][dx] == 1) continue;
				int nextDist = dist + (d == i ? 100 : 600);
				if(minDist[dy][dx][i] > nextDist) {
					queue.add(new Node(nextDist, dy, dx, i));
					minDist[dy][dx][i] = nextDist;
				}
			}
		}
		
        return dist;
    }
}
