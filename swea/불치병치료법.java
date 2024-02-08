
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Swea불치병치료법 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int result = (1 << 10) - 1;
		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			int val = 0;
			int num = Integer.parseInt(br.readLine());
			int tmp = num;
			for(int i = 0;; i++) {
				System.out.println(tmp);
				for (char c : String.valueOf(tmp).toCharArray()) {
					val |= (1 << (c - '0'));
					
				}
				if (val == result) {
					sb.append(tmp).append('\n');
					break;
				}
				tmp = num * (i+1);
			}

			
		}
		System.out.println(sb);
	}
}
