package Round_2020_3_12;

import java.util.Arrays;
import java.util.Scanner;
 
public class D {
	
	public static void main(String[] args)
	{
		Scanner s=new Scanner(System.in);
		
		int n=s.nextInt();
		
		long[] arr=new long[n];
		long[] brr=new long[n];
		
		for(int i=0;i<n;i++)
		{
			arr[i]=s.nextLong();
		}
		
		for(int j=0;j<n;j++)
		{
			brr[j]=s.nextLong();
		}
		
		Long[] dp=new Long[n];
		
		for(int i=0;i<n;i++)
		{
			dp[i]=arr[i]-brr[i];
		}
		
		Arrays.sort(dp);
		
		long count=0;
		
		for(int i=0;i<n;i++)
		{
			long val=binarySearch(dp,i);
			
			if(val!=-1)
			count=count+n-val;
		}
		
		System.out.println(count);
		
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