
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// M의 이진수 표현의 마지막 N비트가 모두 1로 켜져이는지
public class Swea이진수표현 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int tc = 1;  tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
		
			int val = (1 << n) -1;
			if ((val & m) == val) sb.append('#').append(tc).append(' ').append("ON").append('\n');
			else sb.append('#').append(tc).append(' ').append("OFF").append('\n');
		}
		System.out.println(sb);
	}
}
