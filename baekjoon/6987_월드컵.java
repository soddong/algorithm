
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class 6987_월드컵 {
	static int TOTAL_COUNTRY = 6;
	static int POSSIBLE_MATCH_TYPE = 3;
	static int WIN = 0;
	static int DRAW = 1;
	static int LOSE = 2;
	static boolean isPossible = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 4;
	
		for (int tc = 1; tc <= T; tc++) {
			int[][] result_board = new int[TOTAL_COUNTRY][POSSIBLE_MATCH_TYPE];
			String input = br.readLine();
			
			int c = 0;
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					result_board[i][j] = input.charAt(c) - '0';
					c+=2;
				}
			}
			
			play(result_board);
		}
	}

	private static void play(int[][] result_board) {
		// TODO Auto-generated method stub
		isPossible = false;
		
		backtracking(0, 1, WIN, result_board);
		backtracking(0, 1, DRAW, result_board);
		backtracking(0, 1, LOSE, result_board);
		
		if (isPossible == true) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

	private static void backtracking(int i, int j, int match, int[][] result_board) {
		// TODO Auto-generated method stub
		if (i < TOTAL_COUNTRY-2 && j == TOTAL_COUNTRY) {
			++i; j = i+1;
		} else if (i == TOTAL_COUNTRY-2 && j == TOTAL_COUNTRY) {
			boolean flag = true;
			for (int k = 0; k < result_board.length; k++) {
				for (int k2 = 0; k2 < result_board[0].length; k2++) {
					if (result_board[k][k2] != 0)
						flag = false;
				}
			}
			
			if (flag) isPossible = true;
			return;
		}
		
		if (result_board[i][match] == 0 || result_board[j][(5-match)%3] == 0)
			return;
		
		for (int k = 0; k < POSSIBLE_MATCH_TYPE; k++) {
			result_board[i][match]--; result_board[j][(5-match)%3]--;
			backtracking(i, j+1, k, result_board);
			result_board[i][match]++; result_board[j][(5-match)%3]++;
		}
	}


}
