package MeasureTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MeasureTime_MergeSort {
	private static long count;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int c=0; c<C; ++c) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] A = new int[N];
			for(int j=0; j<N; ++j) {
				A[j] = Integer.parseInt(st.nextToken());
			}
			
			count = 0;
			getMerge(A);
			sb.append(count + "\n");
		}
		System.out.print(sb.substring(0, sb.length() - 1));
	}
	
	private static int[] getMerge(int []A) {
		if(A.length == 1) return A;
		
		int e = A.length -1;
		int m = e / 2;
		int []left = new int[m+1];
		int []right = new int[e-m];
		
		for(int i=0; i<=m; ++i) {
			left[i] = A[i];
		};
		
		for(int i=m+1; i<=e; ++i) {
			right[i-m-1] = A[i];
		}
		
		left = getMerge(left);
		right = getMerge(right);
		
		int []arr = new int[e+1];
		int lIdx = 0;
		int rIdx = 0;
		int tIdx = 0;
		while(lIdx < left.length && rIdx < right.length) {
			if(left[lIdx] <= right[rIdx]) {
				arr[tIdx++] = left[lIdx++];
			} else {
				count += left.length - lIdx;
				arr[tIdx++] = right[rIdx++];
			}
		}
		while(lIdx < left.length) {
			arr[tIdx++] = left[lIdx++];
		}
		while(rIdx < right.length) {
			arr[tIdx++] = right[rIdx++];
		}
		
		return arr;
	}
}
