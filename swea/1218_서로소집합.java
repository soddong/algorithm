package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Pro13459구슬탈출 {
	
	public static class UnionFind {
		int[] parent;	
		int n;
		
		public UnionFind(int n) {
			this.parent = new int[n+1];
			this.n = n;
		}

		public void make() {
			for (int i = 1; i <= n; i++) {
				parent[i] = i;
			}
		}
		
		public void union(int a, int b) {
			int root = find(a);
			
			int now = b;
			while (true) {
				int next = parent[now];
				if (next == now) {
					parent[now] = root;
					break;
				}
				parent[now] = root;
				now = next;
			}
		}
		
		public int find(int a) {
		    if (parent[a] == a) return a;
		    return parent[a] = find(parent[a]);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 집합 개수
			int m = Integer.parseInt(st.nextToken()); // 연산의 개수
			
			UnionFind unionFind = new UnionFind(n);
			unionFind.make();
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int comm = Integer.parseInt(st.nextToken()); 
				int a = Integer.parseInt(st.nextToken()); 
				int b = Integer.parseInt(st.nextToken()); 
				if (comm == 0) {
					unionFind.union(a, b);
				} else if (comm == 1) {
					if (unionFind.find(a) == unionFind.find(b)) {
						sb.append('1');
					} else {
						sb.append('0');
					}
				}
			}
			sb.append('\n');
		}
		System.out.println(sb);
		
	}
}
