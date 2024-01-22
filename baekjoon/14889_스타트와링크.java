package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Pro14889 {

	private static List<int[]> generateCombinations(int n, int r) {
		List<int[]> combinations = new ArrayList<>();
		generateCombinationsRecursively(combinations, new int[r], 0, n - 1, 0);
		return combinations;
	}

	private static void generateCombinationsRecursively(List<int[]> combinations, int[] elements, int current, int end,
			int index) {
		if (index == elements.length) {
			combinations.add(elements.clone());
			return;
		}
		if (current > end) {
			return;
		}
		elements[index] = current;
		generateCombinationsRecursively(combinations, elements, current + 1, end, index + 1);
		generateCombinationsRecursively(combinations, elements, current + 1, end, index);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];
		int answer = Integer.MAX_VALUE;
		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				board[i][j] = Integer.parseInt(st.nextToken());
		}

		List<int[]> combinations = generateCombinations(N, (int) N / 2);

		for (int[] combination : combinations) {
			Set<Integer> otherCombi = new HashSet<>();
			for (int i = 0; i < N; i++) {
				otherCombi.add(i);
			}
			List<int[]> couples = generateCombinations((int) N / 2, 2);

			// teamA
			int sum1 = 0;
			for (int[] couple : couples) {
				int idx1 = combination[couple[0]];
				int idx2 = combination[couple[1]];
				sum1 += board[idx1][idx2];
				sum1 += board[idx2][idx1];
			}

			// teamB
			for (int c : combination) {
				otherCombi.remove(c);
			}
			int sum2 = 0;
			Integer[] other = otherCombi.toArray(new Integer[otherCombi.size()]);
			for (int[] couple : couples) {
				int idx1 = other[couple[0]];
				int idx2 = other[couple[1]];
				sum2 += board[idx1][idx2];
				sum2 += board[idx2][idx1];
			}
			answer = Math.min(answer, Math.abs(sum1 - sum2));
		}
		System.out.println(answer);
	}
}
