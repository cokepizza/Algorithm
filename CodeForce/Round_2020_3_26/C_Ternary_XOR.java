package Round_2020_3_26;

import java.io.*;

public class C_Ternary_XOR {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<t; ++i) {
			int n = Integer.parseInt(br.readLine());
			String numStr = br.readLine();
			int [][]arr = new int[n][2];
			boolean maxPass = false;
			for(int j=0; j<numStr.length(); ++j) {
				int next = numStr.charAt(j) - '0';
				if(next == 2) {
					if(!maxPass) {
						arr[j][0] = 1;
						arr[j][1] = 1;
						continue;
					}
					arr[j][0] = 0;
					arr[j][1] = 2;
					
				} else if(next == 1) {
					if(!maxPass) {
						maxPass = true;
						arr[j][0] = 1;
						continue;
					}
					arr[j][1] = 1;
				}
			}
			for(int j=0; j<numStr.length(); ++j) {
				sb.append(arr[j][0]);
			}
			sb.append("\n");
			for(int j=0; j<numStr.length(); ++j) {
				sb.append(arr[j][1]);
			}
			sb.append("\n");
		}
		System.out.print(sb.substring(0, sb.length() - 1));
	}
}
