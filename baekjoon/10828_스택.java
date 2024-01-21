import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 10828. 스택
 * 소요시간 (백준 내장 컴파일러 기준) : 448ms
 */
public class SH10828 {
	private static BufferedReader br;
	private static StringTokenizer st;
	private static Stack<Integer> store = new Stack<>();

	public static int operate(String command) {
		int result = 0;
		switch (command) {
			case "pop":
				result = store.isEmpty() ? -1 : store.pop();
				break;
			case "size":
				result = store.size();
				break;
			case "empty":
				result = store.isEmpty() ? 1 : 0;
				break;
			case "top" :
				result = store.isEmpty() ? -1 : store.peek();
				break;
			default:
				break;
		}
		return result;
	}

	public static void operate(String command, int value) {
		if (command.equals("push"))
			store.push(value);
	}

	public static boolean hasNumber(String command) {
		if (command.equals("push"))
			return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			if (hasNumber(command)) {
				operate(command, Integer.parseInt(st.nextToken()); // void type (not print)
			} else {
				System.out.println(operate(command)); // int type (print)
			}
		}
	}
}
