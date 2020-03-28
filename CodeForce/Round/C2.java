package Round;

import java.io.*;
import java.util.*;

public class C2 {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<t; ++i) {
			int n = Integer.parseInt(br.readLine());
			boolean []visitied = new boolean[n+1];
			boolean optimal = true;
			int left = -1;
			for(int j=1; j<=n; ++j) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				boolean pass = false;
				for(int k=0; k<num; ++k) {
					int next = Integer.parseInt(st.nextToken());
					if(!pass && !visitied[next]) {
						pass = true;
						visitied[next] = true;
					}
				}
				if(!pass && left == -1) {
					optimal = false;
					left = j;
				}
			}
			
			if(optimal) {
				sb.append("OPTIMAL\n");
			} else {
				for(int j=1; j<=n; ++j) {
					if(!visitied[j]) {
						sb.append("IMPROVE\n");
						sb.append(left + " " + j + "\n");
						break;
					}
				}
			}
			
		}
		
		System.out.print(sb.substring(0, sb.length() - 1));
	}
}
