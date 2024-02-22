package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Swea3124최소스패닝트리 {
	
	static int[] parent;
	static Edge[] edgeList;
	static int V; static int E;
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			edgeList = new Edge[E];
			parent = new int[V+1];
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				edgeList[i] = new Edge(u, v, w);
			}
			
			// 1. 정렬
			Arrays.sort(edgeList);
			
			// 2. make set
			make();
			
			// 3. 신장트리 생성 
			int cnt = 0; long weight = 0;
			for (Edge edge : edgeList) {
				if (union(edge.from, edge.to)) {
					weight += edge.weight;
					if (++cnt == V-1) break;
				}
			}
			
			sb.append('#').append(tc).append(' ').append(weight).append('\n');
		}
		System.out.println(sb);
		
		
	}
	
	public static void make() {
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}
	}
	
	public static int find(int node) {
		if (node == parent[node]) return node;
		return parent[node] = find(parent[node]);
	}
	
	public static boolean union(int a, int b) {
		int aroot = find(a);
		int broot = find(b);
		if (aroot == broot) return false;
		int now = b;
		while (true) {
			int next = parent[now];
			if (next == now) {
				parent[now] = aroot;
				break;
			}
			parent[now] = aroot;
			now = next;
		} 
		return true;
	}
	
}
