package KaKao_Code_Qualification_2017_01;

import java.util.LinkedList;

public class KaKao_Code_Qualification_2017_01 {
	public static void main(String args[]) {
		int []answer = solution(6, 4, new int[][] {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}});
		
		System.out.println(answer[0]);
		System.out.println(answer[1]);
	}
	
	public static class Node {
		int y, x;
		public Node (int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	private static int [][]dir = {{1, -1, 0, 0}, {0, 0, 1, -1}};
	public static int[] solution(int m, int n, int [][]picture) {
		boolean visitied[][] = new boolean[m][n];
				
		int maxValue = 0;
		int diverse = 0;
		for(int i=0; i<m; ++i) {
			for(int j=0; j<n; ++j) {
				if(picture[i][j] != 0 && !visitied[i][j]) {
					int color = picture[i][j];
					LinkedList <Node> queue = new LinkedList <>();
					queue.add(new Node(i, j));
					visitied[i][j] = true;
					int counter = 0;
					++diverse;
					while(!queue.isEmpty()) {
						int size = queue.size();
						counter += size;
						for(int k=0; k<size; ++k) {
							Node node = queue.poll();
							for(int d=0; d<4; ++d) {
								int dy = node.y + dir[0][d];
								int dx = node.x + dir[1][d];
								if(dy >= m || dx >= n || dy < 0 || dx < 0 || visitied[dy][dx] || color != picture[dy][dx]) continue;
								visitied[dy][dx] = true;
								queue.add(new Node(dy, dx));
							}
						}
					}
					maxValue = Math.max(maxValue, counter);
				}
			}
		}
		
		int []answer = new int[2];
		answer[0] = diverse;
		answer[1] = maxValue;
		return answer; 
	}
}
