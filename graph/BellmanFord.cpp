#include <iostream>
#include <queue>
#include <stack>

using namespace std;
#define N 50
#define INF 500

int dist[N] = { 0 };
int n;
int weight[N][N] = { 0 };

void Bellman() {
	// 시작정점을 입력받기
	int start;
	cout << "Input start node" << endl;
	cin >> start;
	
	// dist 2 ~ dist n-2 까지 구하기 위해 반복
	cout << "dist1 : ";
	for (int i = 1; i <= n; i++) {
		dist[i] = weight[start][i];
		cout << dist[i] << " ";
	}
	cout << endl;

	// dist 업데이트
	for (int cnt = 1; cnt < n - 2; cnt++) {
		for (int u = 1; u <= n; u++) {
			for (int v = 1; v <= n; v++) {
				if (weight[u][v] != INF) {
					// 정점u가 v와 연결되어있고 최단 거리 + 가중치가 도착점의 가중치
					// 보다 작을 때 업데이트
					dist[v] = min(dist[v], dist[u] + weight[u][v]);
					if (u == n) cout << "negative cycle!" << endl; // 음의 싸이클 감지
				}
			}
		}
		cout << "dist" << cnt + 1 << " : ";
		for (int i = 1; i <= n; i++) {
			cout << dist[i] << " ";
		}
		cout << endl;
	}
	// 최종 dist 값 출력
	cout << "dist" << n - 1 << " : ";
	for (int i = 1; i <= n; i++)
		cout << dist[i] << " ";
	cout << endl;

}

int main() {
	cout << "################# 12171213 최소현 ################ " << endl;

	cout << "--------------Bellman Ford Start--------------" << endl;
	// 개수, matrix 입력받고 초기화
	cout << "Input N" << endl;
	cin >> n;
	cout << "Input weight adjacency matrix" << endl;

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++)
			cin >> weight[i][j];
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (i == j)	weight[i][j] = 0;
			else
			{
				if (weight[i][j] == 0) weight[i][j] = INF;
			}
		}
	}

	Bellman();

	return 0;
}