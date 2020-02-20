package MeasureTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MeasureTime {
	private static final int MAX_VALUE = 1000000;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		for(int c=0; c < C; ++c) {
			int N = Integer.parseInt(br.readLine());
			int []A = new int [MAX_VALUE];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			// MAX_VALUE만큼 배열을 선언했어도 펜윅트리 큰 그림은 2^n 형태라고 생각하면 될듯
			for(int j=0; j<N; ++j) {
				int pop = Integer.parseInt(st.nextToken());
				
				int count = 0;
				int num = pop;
				while(num > 0) {
					count -= A[num];
					num -= num & (-num);
				}
				
				num = MAX_VALUE-1;
				while(num > 0) {
					count += A[num];
					num -= num & (-num);
				}
		
				System.out.println(count);
				
				//	update
				num = pop;
				while(num < MAX_VALUE) {
					A[num] += num;
					num += num & (-num);	
				}
			}
			
			for(int j=0; j<6; ++j) {
				System.out.println(A[j]);
			}
		}
		
	}
}
