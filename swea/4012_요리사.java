
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int n; static int[][] s;
	static int min;
	public static void combination(int now, int picka, int pickb, int suma, int sumb, int flag) {
		int tmpa = suma;
		int tmpb = sumb;
		if (picka == (int)(n/2) && pickb == (int)(n/2)) {
			if (min == -1 || Math.abs(sumb - suma) < min) {
				min = Math.abs(sumb - suma);
			}
			return;
		}
		
		if (now == n) return;
		
		if ((flag & (1 << now)) == 0) { // not checked
			for (int j = 0; j <= now; j++) {
				if ((flag & (1 << j)) != 0) {
					tmpa += s[now][j];
					tmpa += s[j][now];
				} 
				
				if ((flag & (1 << j)) == 0) {
					tmpb += s[now][j];
					tmpb += s[j][now];
				} 
				
			}
			combination(now+1, picka+1, pickb, tmpa, sumb, flag | (1 << now));
			combination(now+1, picka, pickb+1, suma, tmpb, flag);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			s = new int[n][n];
			min = -1;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					s[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			combination(0, 0, 0, 0, 0, 0);
			sb.append('#').append(tc).append(' ').append(min).append('\n');
		}
		
		System.out.println(sb);
	}
}
