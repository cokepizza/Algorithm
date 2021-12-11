package Code14225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

//	https://www.acmicpc.net/problem/14225

public class Code14225 {
	public static HashSet <Integer> hashSet = new HashSet <>();
	public static int []s;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		s = new int[n];
		for(int i=0; i<n; ++i) {
			s[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<=n; ++i) {
			combination(n, i, 0, 0);
		}
		
		int idx = 1;
		while(true) {
			if(!hashSet.contains(idx)) break;
			++idx;
		}
		System.out.println(idx);
	}

	private static void combination(int n, int c, int prev, int visited) {
		if(Integer.bitCount(visited) == c) {
			int sum = 0;
			for(int i=0; i<n; ++i) {
				if((visited & (1 << i)) != 0) {
					sum += s[i];
				}
			}
			hashSet.add(sum);
			return;
		}
		
		for(int i=prev; i<n; ++i) {
			if((visited & (1 << i)) == 0) {
				combination(n, c, i+1, visited | 1 << i);
			}
		}
		
	}
}
