package KaKao_Blind_Recruitment_2020_03;

//	자물쇠와 열쇠
//	https://programmers.co.kr/learn/courses/30/lessons/60059

public class KaKao_Blind_Recruitment_2020_03_02 {
	public static void main(String args[]) {
//		boolean ans = solution(new int[][] {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}, new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}});
//		boolean ans = solution(new int[][] {{0, 1, 0}, {1, 0, 0}, {1, 0, 0}}, new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}});
		boolean ans = solution(new int[][] {{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, new int[][] {{0, 0, 0}, {1, 0, 1}, {1, 1, 1}});
		System.out.println(ans);
	}
	
	public static boolean solution(int[][] key, int[][] lock) {
		int m = key.length;
		int n = lock.length;

		int margin = 0;
		for(int i=0; i<n; ++i) {
			for(int j=0; j<n; ++j) {
				if(lock[i][j] == 0) ++margin;
			}
		}
		
		int [][][]dKey = new int[4][m][m];
		for(int i=0; i<m; ++i) {
			for(int j=0; j<m; ++j) {
				dKey[0][i][j] = key[i][j];
			}
		}
		
		for(int d=1; d<4; ++d) {
			for(int i=0; i<m; ++i) {
				for(int j=0; j<m; ++j) {
					dKey[d][i][j] = dKey[d-1][m-j-1][i];
				}
			}	
		}
		
		
		for(int d=0; d<4; ++d) {	// direction
			for(int i=-n+1; i<n; ++i) {	// move Y
				loop: for(int j=-n+1; j<n; ++j) {	// move X 
					int resolve = 0;
					for(int k=0; k<m; ++k) {	//	key loc Y
						for(int p=0; p<m; ++p) {	//	key loc X
							int dy=i+k, dx=j+p;
							if(dy < 0 || dy >= n || dx < 0 || dx >= n) continue;
							if((lock[dy][dx] == 1 && dKey[d][k][p] == 1) || (lock[dy][dx] == 0 && dKey[d][k][p] == 0)) {
								continue loop;
							}
							if(lock[dy][dx] == 0 && dKey[d][k][p] == 1) ++resolve;
						}
					}
					
					if(margin == resolve) {
						return true;
					}
				}
			}	
		}
		
        return false;
    }
}
