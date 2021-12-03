package Code19236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Code19236 {
	private static int [][][] inform;
	public static int [][]dir = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		inform = new int[4][4][2];
		
		for(int i=0; i<4; ++i) {
			String line = br.readLine();
			StringTokenizer st = new StringTokenizer(line);
			int x = 0;
			while(st.hasMoreTokens()) {
				String token = st.nextToken();
				int num = Integer.parseInt(token);
				token = st.nextToken();
				int direction = Integer.parseInt(token);
				inform[i][x][0] = num;
				inform[i][x][1] = direction - 1;
				++x;
			}
		}
		
		int res = getMaxSum(0, 0);
		System.out.println(res);
	}
	
	private static int getMaxSum(int y, int x) {
		int [][][]informCopy = new int[4][4][2];
		
		for(int i=0; i<4; ++i) {
			for(int j=0; j<4; ++j) {
				for(int k=0; k<2; ++k) {
					informCopy[i][j][k] = inform[i][j][k];	
				}
			}
		}
		
		for(int fish = 1; fish <= 16; ++fish) {
			int minFishY = -1;
			int minFishX = -1;
			
			for(int i=0; i<4; ++i) {
				for(int j=0; j<4; ++j) {
					if(i == y && j == x) continue;
					if(fish == inform[i][j][0]) {
						minFishY = i;
						minFishX = j;
						break;
					}
				}
			}
			
			if(minFishY != -1 && minFishX != -1) {
				for(int k=0; k<8; ++k) {
					int nextDir = (inform[minFishY][minFishX][1] + k) % 8;
					int dy = minFishY + dir[nextDir][0];
					int dx = minFishX + dir[nextDir][1];
					if(dy < 0 || dx < 0 || dy >= 4 || dx >= 4 || (dy == y && dx == x)) continue;
					
					int cacheMinFishCount = inform[minFishY][minFishX][0];
					inform[minFishY][minFishX][0] = inform[dy][dx][0];
					inform[minFishY][minFishX][1] = inform[dy][dx][1];
					inform[dy][dx][0] = cacheMinFishCount;
					inform[dy][dx][1] = nextDir;
					break;
				}
			}
		}
		
		int eat = inform[y][x][0];
		int maxSum = eat;
		inform[y][x][0] = 0;
		for(int i=1; i<4; ++i) {
			int sharkDir = inform[y][x][1];
			int dy = y + dir[sharkDir][0] * i;
			int dx = x + dir[sharkDir][1] * i;
			if(dy < 0 || dx < 0 || dy >= 4 || dx >= 4 || inform[dy][dx][0] == 0) continue;			
			maxSum = Math.max(maxSum, getMaxSum(dy, dx) + eat);
		}
		
		inform = informCopy;
		inform[y][x][0] = eat;
		return maxSum;
	}
}