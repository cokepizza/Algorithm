package Code16197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

//	https://www.acmicpc.net/problem/16197

public class Code16197 {
	public static class Node {
		int y, x;
		public Node (int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	public static class Bundle {
		Node a, b;
		public Bundle(Node a, Node b) {
			this.a = a;
			this.b = b;
		}
	}
	
	public static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		boolean [][]road = new boolean[y][x];
		LinkedList <Bundle>queue = new LinkedList<>();
		
		int nodeCount = 0;
		Node []node = new Node[2];
		boolean [][][][]visited = new boolean[20][20][20][20];
		
		for(int i=0; i<y; ++i) {
			String roadStr = br.readLine();
			for(int j=0; j<x; ++j) {
				char pop = roadStr.charAt(j);
				if(pop == '#') road[i][j] = true;
				else if (pop == 'o') {
					node[nodeCount++] = new Node(i, j);
				}
			}
		}
		
		visited[node[0].y][node[0].x][node[1].y][node[1].x] = true;
		queue.add(new Bundle(node[0], node[1]));
		int loopCount = 0;
		boolean success = false;
		
		loop: while(!queue.isEmpty()) {
			++loopCount;
			int size = queue.size();
			for(int i=0; i<size; ++i) {
				Bundle bundle = queue.pop();
				Node a = bundle.a;
				Node b = bundle.b;
				
				for(int j=0; j<4; ++j) {
					int ady = a.y + dir[j][0];
					int adx = a.x + dir[j][1];
					int bdy = b.y + dir[j][0];
					int bdx = b.x + dir[j][1];
					
					int outCount = 0;
					if(ady < 0 || ady >= y || adx < 0 || adx >= x) ++outCount;
					if(bdy < 0 || bdy >= y || bdx < 0 || bdx >= x) ++outCount;
					
					if(outCount == 1) {
						success = true;
						break loop;
					} else if(outCount > 1) {
						continue;
					}
					
					if(road[ady][adx]) {
						ady = a.y;
						adx = a.x;
					}
					if(road[bdy][bdx]) {
						bdy = b.y;
						bdx = b.x;
					}
					
					if(visited[ady][adx][bdy][bdx]) continue;
					
					visited[ady][adx][bdy][bdx] = true;
					queue.add(new Bundle(new Node(ady, adx), new Node(bdy, bdx)));
				}
			}
		}
		
		System.out.println((success && loopCount <= 10) ? loopCount : -1);
	}
}
