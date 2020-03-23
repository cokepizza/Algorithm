package Round_2020_3_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_Maximums {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num = -1;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; ++i) {
			int b = Integer.parseInt(st.nextToken());
			if(num == -1) num = b;
			else {
				if(b < 0) {
					sb.append(num + b + " ");
					continue;
				} else {
					num += b;	
				}
			}
			sb.append(num + " ");
		}
		System.out.print(sb.substring(0, sb.length() - 1));
	}
}
