package Round;

import java.io.*;
import java.util.*;

public class C1 {
	private static int k;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<t; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			k =  Integer.parseInt(st.nextToken());
			
		}
		System.out.print(sb.substring(0, sb.length() -1));
	}
}
