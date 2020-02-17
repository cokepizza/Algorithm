package Code2529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Code2529 {
	private static int num;
	private static long max = Long.MIN_VALUE, min = Long.MAX_VALUE;
	private static String[] arr;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		arr = br.readLine().split(" ");
		
		int visitied = 0;
		getIndex(0, visitied, 0);
		StringBuilder sb = new StringBuilder();
		sb.append(serialize(max) + '\n' + serialize(min));
		System.out.print(sb);
	}
	
	private static String serialize(long value) {
		String strVal = String.valueOf(value);
		if(strVal.length() != num+1) {
			strVal = '0' + strVal;
		}
		
		return strVal;
	}

	private static void getIndex(int index, int visitied, long total) {
		if(index == num+1) {
			max = Math.max(max, total);
			min = Math.min(min, total);
			return;
		};
		
		for(int i=0; i<10; ++i) {
			if((visitied & (1 << i)) == 0) {
				if(index > 0) {
					int prev = (int)(total / (long)Math.pow(10, index -1));
					if(arr[num-index].equals("<")) {
						if(i > prev) continue;
					} else {
						if(i < prev) continue;
					}
				}
				
				getIndex(index+1, visitied | (1 << i), total + ((long)Math.pow(10, index) * i) );
			}
		}
	}
}
