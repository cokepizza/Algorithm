package Code12851;

import java.util.*;
import java.io.*;

public class Code12851_2 {
	private static int k;
	private static final int MAX = 100000;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		boolean []visitied = new boolean[MAX+1];
		LinkedList <Integer> queue = new LinkedList <>();
		queue.add(n);
		
		int c = 0;
		int nCount = 0;
		boolean pass = false;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i=0; i<size; ++i) {
				int num = queue.poll();
				visitied[num] = true;
				
				if(num == k) {
					pass = true;
					++nCount;
				}
				
				if(MAX >= num+1 && !visitied[num+1]) {
					queue.add(num+1);
				}
				if(num-1 >= 0 && !visitied[num-1]) {
					queue.add(num-1);
				}
				if(MAX >= num*2 && !visitied[num*2]) {
					queue.add(num*2);
				}
			}
			
			if(pass) break;
			++c;
		}
		
		System.out.println(c);
		System.out.println(nCount);
	}
}
