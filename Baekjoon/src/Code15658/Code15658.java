package Code15658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Code15658 {
	public static int n;
	public static int []a;
	public static int maxCalc = Integer.MIN_VALUE;
	public static int minCalc = Integer.MAX_VALUE;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		a = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; ++i) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int plus = Integer.parseInt(st.nextToken());
		int minus = Integer.parseInt(st.nextToken());
		int multi = Integer.parseInt(st.nextToken());
		int divide = Integer.parseInt(st.nextToken());
		calc(plus, minus, multi, divide, 1, a[0]);
		System.out.println(maxCalc);
		System.out.println(minCalc);
	}

	private static void calc(int plus, int minus, int multi, int divide, int c, int res) {
		if(n == c) {
			maxCalc = Math.max(maxCalc, res);
			minCalc = Math.min(minCalc, res);
			return;
		}
		
		if(plus > 0) calc(plus-1, minus, multi, divide, c+1, res + a[c]);
		if(minus > 0) calc(plus, minus-1, multi, divide, c+1, res - a[c]);
		if(multi > 0) calc(plus, minus, multi-1, divide, c+1, res * a[c]);
		if(divide > 0) calc(plus, minus, multi, divide-1, c+1, res / a[c]);
	}
}
