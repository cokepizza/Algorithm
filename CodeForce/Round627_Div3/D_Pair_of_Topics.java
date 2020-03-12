package Round627_Div3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//	정렬 후 이진탐색으로 시간복잡도를 낮췄음
public class D_Pair_of_Topics {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int []a = new int[t];
		for(int i=0; i<t; ++i) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		int []diff = new int[t];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<t; ++i) {
			diff[i] = a[i] - Integer.parseInt(st.nextToken());
		}
		
		long count = 0;
		Arrays.sort(diff);
		for(int i=0; i<t; ++i) {
			int idx = upper_bound(i+1, t, diff, -diff[i]);
			count += t - idx;
		}
		
		System.out.println(count);
	}
	
	private static int upper_bound(int s, int e, int[] list, int key) {
		if(s >= e) return e;
		int m = (s + e) / 2;
		if(key < list[m]) return upper_bound(s, m, list, key);
		else return upper_bound(m+1, e, list, key);
	}
}
