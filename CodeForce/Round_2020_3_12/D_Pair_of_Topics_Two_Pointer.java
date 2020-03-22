package Round_2020_3_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D_Pair_of_Topics_Two_Pointer {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int []a = new int[t];
		for(int i=0; i<t; ++i) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<t; ++i) {
			a[i] -= Integer.parseInt(st.nextToken());
		}
		
		long count = 0;
		Arrays.sort(a);
		int s = 0; int e = t-1;
		while(s <= e) {
			while(s < e && -a[s] < a[e]) --e;
			count += (t-1-e);
			++s;
		}
		for(int i=e+1; i<t; ++i) {
			count += (t-1-i);
		}
		
		System.out.println(count);
	}
	
}
