import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Pro2252줄세우기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] edge = new int[n+1];
		
		ArrayList<Integer>[] graph = new ArrayList[n+1];
		for (int i = 0; i < n+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			edge[v]++;
		}
		
		Deque<Integer> start = new ArrayDeque<>();
		for (int i = 1; i <= n; i++) {
			if (edge[i] == 0) {
				start.add(i);
			}
		}
		
		while (!start.isEmpty()) {
			int node = start.pollFirst();
			
			for (int next : graph[node]) {
				if (--edge[next] == 0) {
					start.add(next);
				}
			}
			
			sb.append(node).append(' ');
		}
		System.out.println(sb);
	}
}
