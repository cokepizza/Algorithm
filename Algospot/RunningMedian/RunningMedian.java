package RunningMedian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class RunningMedian {
	private static final int MOD = 20090711;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<t; ++i) {
			long sum = 0;
			
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			PriorityQueue <Long> minHeap = new PriorityQueue<>();
			PriorityQueue <Long> maxHeap = new PriorityQueue<>(new Comparator <Long>() {
				@Override
				public int compare(Long arg0, Long arg1) {
					if(arg1 > arg0) return 1;
					return -1;
				}
			});
			
			long val = 1983;
			for(int j=0; j<n; ++j) {
				if(maxHeap.peek() == null) {
					maxHeap.add(val);
				} else {
					if(val <= maxHeap.peek()) {
						maxHeap.add(val);
					} else {
						minHeap.add(val);
					}					
				}
				
				while(maxHeap.size() < minHeap.size()) {
					long popedVal = minHeap.poll();
					maxHeap.add(popedVal);
				}
				
				while(maxHeap.size() > minHeap.size() + 1) {
					long popedVal = maxHeap.poll();
					minHeap.add(popedVal);
				}
				sum = (sum + maxHeap.peek()) % MOD;
				
				val = (val * a + b) % MOD;
			}
			sb.append(sum + "\n");
		}
		System.out.print(sb.substring(0, sb.length() - 1));
	}
}
