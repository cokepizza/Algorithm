package PC110101;

import java.io.IOException;
import java.util.Scanner;

public class PC110101 {
	private static int []dp;
	public static void main(String args[]) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int sp, ep;
		
		while(sc.hasNextLine()) {
			sp = sc.nextInt();
			ep = sc.nextInt();
			
			dp = new int[3000001];
			int max = -1;
			for(int i=sp; i<=ep; ++i) {
				max = Math.max(max, getLength(i));
			}
			
			System.out.println(sp + " " + ep + " " + max);
		}
		sc.close();

	}

	private static int getLength(int i) {
		if(dp[i] != 0) return dp[i];
		if(i == 1) return 1; 
		
		if(i % 2 == 0) {
			return dp[i] = getLength(i/2) +1;
		} else {
			return dp[i] = getLength(i*3+1) +1;
		}
	}
	
}
