import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 1316. 그룹 단어 체커
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		int answer = 0;

		for (int i = 0; i < T; i++) {
			char[] charArray = sc.next().toCharArray();
			Set<Character> store = new HashSet<>();

			char prev = ' ';
			boolean isGroupWord = true;

			for (char c : charArray) {
				if (c == prev)
					continue;

				if (store.contains(c)) {
					isGroupWord = false;
					break;
				}

				store.add(c);
				prev = c;
			}

			if (isGroupWord)
				answer += 1;
		}

		System.out.println(answer);
	}
}
