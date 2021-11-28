package KaKao_Blind_Recruitment_2019_07;

import java.util.Arrays;
import java.util.HashSet;

//	블록게임
//	https://programmers.co.kr/learn/courses/30/lessons/42894

public class KaKao_Blind_Recruitment_2019_07 {
	public static void main(String args[]) {
		int res = solution(new int[][] {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,4,0,0,0},{0,0,0,0,0,4,4,0,0,0},{0,0,0,0,3,0,4,0,0,0},{0,0,0,2,3,0,0,0,5,5},{1,2,2,2,3,3,0,0,0,5},{1,1,1,0,0,0,0,0,0,5}});
		System.out.println(res);
	}
	
	private static int []cursor;
	private static int tc;
	private static int [][][]dir = {{{0, 0, 0, 1, 1, 1}, {0, -1, -2, 0, -1, -2}}, {{0, 0, 1, 1, 2, 2}, {0, 1, 0, 1, 0, 1}}, {{0, 0, 1, 1, 2, 2}, {0, -1, 0, -1, 0, -1}}, {{0, 0, 0, 1, 1, 1}, {0, -1, 1, 0, -1, 1}}, {{0, 0, 0, 1, 1, 1}, {0, 1, 2, 0, 1, 2}}};
	private static int dirNum = 5;
    public static int solution(int[][] board) {
        cursor = new int[board[0].length];
		tc = 0;
		initCursor(board);
		pushBlock(board);
        
        return tc;
    }

		private static void pushBlock(int[][] board) {
		int column = board[0].length;
		while(true) {
			int count = 0;
			for(int i=0; i<column; ++i) {
				if(cursor[i] < 0) {
					++count;
					continue;
				}
				board[cursor[i]][i] = -1;
				--cursor[i];
				if(!checkBlock(board, cursor[i]+1, i)) {
					boolean loopCheck = true;
					for(int j=0; j<column; ++j) {
						if(board[cursor[i]+1][j] != -1) {
							loopCheck = false;
							break;
						}
					}
					if(loopCheck) return;
				};
			}
			if(count == column) return;
			
		}
	}

	private static boolean checkBlock(int[][] board, int y, int x) {
		int row = board.length;
		int column = board[0].length;
		for(int i=0; i<dirNum; ++i) {
			boolean pass = true;
			HashSet <Integer> blockNum = new HashSet <>();
			int starCtn = 0;
			for(int j=0; j<6; ++j) {
				int dy = y + dir[i][0][j];
				int dx = x + dir[i][1][j];
				if(dy < 0 || dy >= row || dx < 0 || dx >= column) {
					pass = false;
					break;
				}
				if(board[dy][dx] == -1) ++starCtn;
				blockNum.add(board[dy][dx]);
			}
			
			if(pass) {
				if(blockNum.contains(-1) && !blockNum.contains(0) && starCtn == 2 && blockNum.size() == 2) {
					for(int j=0; j<6; ++j) {
						int dy = y + dir[i][0][j];
						int dx = x + dir[i][1][j];
						board[dy][dx] = 0;
					}
					for(int j=row-2; j>=0; --j) {
						for(int k=0; k<column; ++k) {
							if(board[j][k] == -1) {
								boolean down = true;
								for(int p=j+1; p < row; ++p) {
									if(board[p][k] != 0) {
										board[j][k] = 0;
										board[p-1][k] = -1;
										down = false;
										break;
									}
								}
								if(down) {
									board[row-1][k] = -1;
									board[j][k] = 0;
								}
							}
						}
					}
					initCursor(board);
					
					++tc;
					return true;
				}
			}
		}
		return false;
	}

	private static void initCursor(int [][]board) {
		int row = board.length;
		int column = board[0].length;
		Arrays.fill(cursor, row-1);
		
		for(int i=0; i<column; ++i) {
			for(int j=0; j<row; ++j) {
				if(board[j][i] != 0) {
					cursor[i] = j-1;
					break;
				}
			}
		}
	}
}
