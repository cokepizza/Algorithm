package MeasureTime;

//	시간초과로직
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class MeasureTime_BST {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int c=0; c < C; ++c) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			TreeMap <Integer, Integer> map = new TreeMap<>();
			long res = 0;
			for(int j=0; j<N; ++j) {
				int pop = Integer.parseInt(st.nextToken());

				if(map.containsKey(pop)) {
					map.put(pop, map.get(pop) + 1);
				} else {
					map.put(pop, 1);
				}
				
				for(int value : map.tailMap(pop, false).values()) {
					res += value;
				};
			}
			sb.append(res + "\n");
		}
		System.out.print(sb.substring(0, sb.length() - 1));
	}
}
