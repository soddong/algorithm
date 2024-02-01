package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 10 30
public class Swea12712 {
	
	static int n;
	static int m;
	static int[][] board;
	
	public static int xMaxSum() {
		int maxValue = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int tmpValue = 0;
				for (int k = -m+1; k <= m-1; k++) {
					if (i-k >= 0 && i-k < n && j+k >= 0 && j+k < n)
						tmpValue += board[i-k][j+k];
					if (i+k >= 0 && i+k < n && j+k >= 0 && j+k < n)
						tmpValue += board[i+k][j+k];
				}
				if (tmpValue - board[i][j] > maxValue) maxValue = tmpValue - board[i][j];
			}
		}
		;
		return maxValue;
	}
	
	public static int plusMaxSum() {
		int maxValue = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int tmpValue = 0;
				for (int k = -m+1; k <= m-1; k++) {
					if (i+k >= 0 && i+k < n)
						tmpValue += board[i+k][j];
					if (j+k >= 0 && j+k < n)
						tmpValue += board[i][j+k];
				}
				if (tmpValue - board[i][j] > maxValue) maxValue = tmpValue - board[i][j];
			}
		}
		
		return maxValue;
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			board = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			System.out.printf("#%d %d\n", tc, Integer.max(plusMaxSum(), xMaxSum()));
		}
		
		
		
	}
	
}
