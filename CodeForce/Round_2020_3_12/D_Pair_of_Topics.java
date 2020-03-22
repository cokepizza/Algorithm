package Round_2020_3_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D_Pair_of_Topics {
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
		for(int i=0; i<t; ++i) {
			if(a[i] > 0) {
				count += t-i-1;
			} else {
				long idx = upper_bound(i+1, t, a, -a[i]);
				count += t-idx;
			}
		}
		
		System.out.println(count);
//		int ans = upper_bound(0, 5, new int[] {1, 2, 2, 2, 3}, 2);
//		System.out.println(ans);
	}
	
//	private static int upper_bound(int s, int e, int[] list, int key) {
//		if(s >= e) return e;
//		int m = (s + e) / 2;
//		if(key < list[m]) return upper_bound(s, m, list, key);
//		else return upper_bound(m+1, e, list, key);
//	}
	
	private static int upper_bound(int s, int e, int[] list, int key) {
		while(s < e) {
			int m = (s + e) / 2;
			if(key < list[m]) {
				e = m;			
			} else {
				s = m+1;			
			}
		}
		
		return e;
	}
	
	public static long binarySearch(Long[] dp,int curr)
	{
		int start=curr+1;
		int end=dp.length-1;
		
		long ans=-1;
		
		while(start<=end)
		{
			int mid=(start+end)/2;
			
			if(dp[mid]+dp[curr]>0)
			{
				ans=mid;
				
				end=mid-1;
			}
			else
			{
				start=mid+1;
			}
		}
		
		return ans;
	}
}
