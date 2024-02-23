import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int G;
	static int P;
	static BufferedReader br;
	static int[] dest;
	static int[] parent;
	
	static void union(int a, int b) { // pa의 부모는 pb
		int pa = parent[a];
		int pb = parent[b];
		
		int now = a;
		if (pa == pb) return;
		parent[pa] = pb;
	}
	
	static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		dest = new int[P];
		parent = new int[G+1];
		
		for (int i = 0; i <= G; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < P; i++) {
			dest[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(solution());
	}
	
	public static int solution() {
		
		int cnt = 0;
		
		for (int i = 0; i < P; i++) {
			
			int k = find(dest[i]);
			if (find(k) != 0) {
				union(k, k-1);
				cnt++;
			} else {
				break;
			}
			
		}
		return cnt;
	}
	
}
