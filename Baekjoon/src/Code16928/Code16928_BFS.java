package Code16928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Code16928_BFS {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int []move = new int[101];
		int up = Integer.parseInt(st.nextToken());
		int down = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<up+down; ++i) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			move[s] = e;
		}
		
		LinkedList <Integer> list = new LinkedList <>();
		list.add(1);
		boolean []visitied = new boolean[101];
		int count = 0;
		loop: while(!list.isEmpty()) {
			int size = list.size();
			for(int i=0; i<size; ++i) {
				int pop = list.poll();
				if(pop == 100) break loop;
				
				for(int j=1; j<=6; ++j) {
					if(pop + j > 100) break;
					
					if(move[pop + j] != 0) {
						if(visitied[move[pop+j]]) continue;
						visitied[move[pop+j]] = true;
						list.add(move[pop+j]);
					} else {
						if(visitied[pop+j]) continue;
						visitied[pop+j] = true;
						list.add(pop + j);
					}
				}
			}
			++count;
		}
		System.out.print(count);
	}
}
