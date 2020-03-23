package Round_2020_3_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_Yet_Another_Palindrome_Problem {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int i=0; i<t; ++i) {
			int n = Integer.parseInt(br.readLine());
			int []a = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; ++j) {
				a[j] = Integer.parseInt(st.nextToken()); 
			}
			
			boolean answer = false;
			loop: for(int j=0; j<n; ++j) {
				for(int k=j+2; k<n; ++k) {
					if(a[j] == a[k]) {
						answer = true;
						break loop;
					}
				}
			}
			System.out.println(answer ? "YES" : "NO");
		}
	}
}
