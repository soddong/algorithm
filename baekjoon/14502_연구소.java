package homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Pro14502 {
	private static List<int[]> generateCombinations(int n, int r) {
		List<int[]> combinations = new ArrayList<>();
		generateCombinationsRecursively(combinations, new int[r], 0, n, 0);
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

	private static int[] findIndex(int beforeIndex, int n, int m) { // n : 3
		return new int[] { beforeIndex / m, beforeIndex % m };
	}

	private static int dfs(int[][] vals, int n, int m) {
		Stack<int[]> stack = new Stack<>();

		int[][] checked = new int[n][m];

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (vals[i][j] == 2) {
					stack.add(new int[] { i, j });
					checked[i][j] = 1;

					while (!stack.isEmpty()) {
						int[] val = stack.pop();

						for (int d = 0; d < 4; d++) {
							int nx = val[0] + dx[d];
							int ny = val[1] + dy[d];

							if (nx < 0 || nx >= n || ny < 0 || ny >= m)
								continue;

							if (checked[nx][ny] == 1)
								continue;

							if (vals[nx][ny] == 0) {
								checked[nx][ny] = 1;
								vals[nx][ny] = 2;

								stack.add(new int[] { nx, ny });
							}
						}

					}
				}
			}
		}

		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (vals[i][j] == 0) {
					cnt += 1;
				}
			}
		}
		return cnt;
	}

	private static int[][] deepcopy(int[][] origin, int n, int m) {
		int[][] copy = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				copy[i][j] = origin[i][j];
		}
		return copy;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();

		int[][] virusMap = new int[n][m];

		int answer = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				virusMap[i][j] = sc.nextInt();
			}
		}

		List<int[]> combinations = generateCombinations(n * m - 1, 3);
		for (int[] combination : combinations) {
			int[][] tmpVirusMap = deepcopy(virusMap, n, m);
			boolean isEmptySpace = true;
			for (int indexToChange : combination) {
				int[] indexsChanged = findIndex(indexToChange, n, m);
				if (virusMap[indexsChanged[0]][indexsChanged[1]] != 0) {
					isEmptySpace = false;
				}
				tmpVirusMap[indexsChanged[0]][indexsChanged[1]] = 1;
			}
			if (!isEmptySpace)
				continue;

			answer = Math.max(answer, dfs(tmpVirusMap, n, m));
		}

		System.out.println(answer);
	}
}
