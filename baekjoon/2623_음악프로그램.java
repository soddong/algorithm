import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int val;
		int level;
		List<Integer> next;
		
		public Node(int val, int level) {
			this.val = val;
			this.level = level;
			this.next = new ArrayList<>();
		}

		@Override
		public String toString() {
			return "val : " + val + ", level : " + level + ", next : " + next;
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		
		Deque<Integer> q = new ArrayDeque<>();
		
		int[][] info = new int[n+1][n+1];
		int[] degree = new int[n+1];
		
		int degree_cnt = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			
			int prev = -1;
			int now;
			for (int l = 0; l < k; l++) {
				now = Integer.parseInt(st.nextToken());
				if (prev != -1) {
					if (info[prev][now] != 1) {
						info[prev][now] = 1;
						degree[now]++;
						degree_cnt++;
					}
				}
				prev = now;
			}
		}
		
		for (int j = 1; j <= n; j++) {
			if (degree[j] == 0) q.add(j);
		}
		
		int cnt = 0;
		while (!q.isEmpty()) {
			int now = q.pop();
			sb.append(now).append('\n');
			for (int i = 1; i <= n; i++) {
				if (info[now][i] == 1) {
					degree[i]--;
					cnt++;
					if (degree[i] == 0) {
						q.add(i);
					}
				}
			}
		}

		if (cnt == degree_cnt) System.out.println(sb);
		else System.out.println(0);
	}
}
