package Code17779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//	단순 구현, 시뮬레이션

public class Code17779 {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int [][]a = new int[N+1][N+1];
		for(int i=1; i<=N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; ++j) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int [][]owner = new int[N+1][N+1];
		int []total = new int[6];
		int res = Integer.MAX_VALUE;
		
		for(int x=1; x<=N; ++x) {
			for(int y=1; y<=N; ++y) {
				for(int d1=1; d1<=N; ++d1) {
					for(int d2=1; d2<=N; ++d2) {
						//	기본 제한
						if(1 <= x && x + d1 + d2 <= N) {
							if(1 <= y-d1 && y+d2 <= N) {
								for(int r=1; r<=N; ++r) {
									for(int c=1; c<=N; ++c) {
										if(r >= x && c <= y && r <= x+d1 && c >= y-d1 && y <= -x + r + c) {
											owner[r][c] = 5;
										}
										if(r >= x && c >= y && r <= x+d2 && c <= y+d2 && y >= x + c - r) {			
											owner[r][c] = 5;
										}
										if(r >= x+d1 && c >= y-d1 && r <= x+d1+d2 && c <= y-d1+d2 && y <= x + c - r + 2 * d1) {
											owner[r][c] = 5;
										}
										if(r >= x+d2 && c <= y+d2 && r <= x+d2+d1 && c >= y+d2-d1 && c <= y-r+x+2* d2) {
											owner[r][c] = 5;
										}
										
										if(owner[r][c] == 0) {
											if(1 <= r && r < x + d1 && 1 <= c && c <= y) {
												owner[r][c] = 1;
											}
											if(1 <= r && r <= x + d2 && y < c && c <= N) {
												owner[r][c] = 2;
											}
											if(x+d1 <= r && r <= N && 1 <= c && c < y-d1+d2) {
												owner[r][c] = 3;
											}
											if(x+d2 < r && r <= N && y-d1+d2 <= c && c <= N) {
												owner[r][c] = 4;
											}
										}
									}
								}
								
								for(int r=1; r<=N; ++r) {
									for(int c=1; c<=N; ++c) {
										total[owner[r][c]] += a[r][c];
									}
								}
								
								int max = Integer.MIN_VALUE;
								int min = Integer.MAX_VALUE;
								for(int t=1; t<=5; ++t) {
									max = Math.max(max, total[t]);
									min = Math.min(min, total[t]);
								}
								res = Math.min(res, max-min);
								
								//	초기화
								for(int r=1; r<=N; ++r) {
									for(int c=1; c<=N; ++c) {
										owner[r][c] = 0;
									}
								}
								
								for(int t=1; t<=5; ++t) {
									total[t] = 0;
								}
							}
						}
					}
				}
			}
		}
		System.out.print(res);
	}
}
