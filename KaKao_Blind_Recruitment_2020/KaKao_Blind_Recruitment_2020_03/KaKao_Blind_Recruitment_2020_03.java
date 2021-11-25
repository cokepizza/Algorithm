package KaKao_Blind_Recruitment_2020_03;

//	자물쇠와 열쇠
//	https://programmers.co.kr/learn/courses/30/lessons/60059

public class KaKao_Blind_Recruitment_2020_03 {
	public static void main(String args[]) {
//		boolean res = solution(new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}, new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}});
//		boolean res = solution(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}});
//		boolean res = solution(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}}, new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 0}});
//		boolean res = solution(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}, new int[][] {{0, 1, 0}, {1, 1, 1}, {0, 1, 0}});
//		boolean res = solution(new int[][]{{1, 1}, {1, 1}}, new int[][] {{1, 1, 1, 1}, {1, 0, 0, 1}, {1, 0, 0, 1}, {1, 1, 1, 1}});
		boolean res = solution(new int[][]{{1, 1}, {1, 1}}, new int[][] {{0, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}});
		
		System.out.println(res);
	}
	
	//	占쎈�占쎌�� 占쎌��占쎄맒占쎌�� 占쎌�띰옙�억옙��嚥∽옙 ��袁れ��占쎌��占쎌�삼옙�� ��遺얜굡 KaKao_Blind_Recruitment_2020_03_02甕곤옙 ��遺얜굡 筌〓㈇�э옙釉� 野�占�
	public static boolean solution(int[][] key, int[][] lock) {
		int m = key.length;
		int n = lock.length;
		int hole = 0;
		for(int i=0; i<n; ++i) {
			for(int j=0; j<n; ++j) {
				if(lock[i][j] == 0) ++hole;
			}
		}
		
		for(int t=0; t<4; ++t) {
			int [][]rotateLockArr = new int[n][n];
			
			for(int k=0; k<n; ++k) {
				for(int p=0; p<n; ++p) {
					rotateLockArr[p][n - 1 - k] = lock[k][p]; 
				}
			}
			
			lock = rotateLockArr;
			
			for(int i=0; i<n; ++i) {
				for(int j=0; j<n; ++j) {
					int [][]keyArr = new int[m][m];
					for(int k=0; k<m; ++k) {
						for(int p=0; p<m; ++p) {
							keyArr[k][p] = key[k][p];
						}
					}
					
					for(int d=0; d<4; ++d) {
						int [][]rotateArr = new int[m][m];
						
						for(int k=0; k<m; ++k) {
							for(int p=0; p<m; ++p) {
								rotateArr[p][m - 1 - k] = keyArr[k][p]; 
							}
						}
						
						keyArr = rotateArr;
						
						int checked = 0;
						boolean confirm = true;
						
						fragment: for(int k=0; k<m && i+k<n; ++k) {
							for(int p=0; p<m && j+p<n; ++p) {
								if(lock[i+k][j+p] + keyArr[k][p] == 1) {
									if(lock[i+k][j+p] == 0) {
										++checked;
									}
								} else {
									confirm = false;
									break fragment;
								}
							}
						}
						
						if(confirm && checked == hole) return true;
					}
				}
			}
		}
		
		return false;
	}
}
