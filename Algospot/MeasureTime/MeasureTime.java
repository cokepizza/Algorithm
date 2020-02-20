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
		StringBuilder sb = new StringBuilder();
		
		for(int c=0; c < C; ++c) {
			int N = Integer.parseInt(br.readLine());
			int []A = new int [MAX_VALUE];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int totalCost = 0;
			
			//	MAX_VALUE만큼 배열을 선언했어도 펜윅트리 큰 그림은 2^n 형태라고 생각하면 될듯
			//	부분합을 이용해 num보다 큰 숫자들의 개수를 구한다 처음에 BST를 사용해볼까 했는데 펜윅이 더 좋은 듯 
			for(int j=0; j<N; ++j) {
				int pop = Integer.parseInt(st.nextToken());
				int count = j;
				
				//	num까지의 부분합
				int num = pop;
				while(num > 0) {
					count -= A[num];
					num -= (num & -num);
				}
		
				totalCost += count;
				
				//	update
				num = pop;
				while(num <= N) {
					A[num] += 1;
					num += (num & -num);	
				}
			}
			
			sb.append(totalCost + "\n");
		}
		System.out.print(sb.substring(0, sb.length() - 1));
	}
}
