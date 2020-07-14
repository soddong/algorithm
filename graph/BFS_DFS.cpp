#include <iostream>
#include <queue>
#include <stack>

using namespace std;
#define N 50

int visited1[N] = { 0 };
int visited2[N] = { 0 };
int n;
int graph[N][N] = { 0 };

struct node {
	int u[N] = {0};
	int v[N] = {0};
	int w[N] = {0};
	int cnt = 0;
};

void BPS() {
	node B_node;

	cout << "--------------BPS Start--------------" << endl;
	// 시작정점 입력
	int start;
	cout << "Input start node" << endl;
	cin >> start;

	// 큐 생성 후, 시작정점 큐에 넣기
	queue<int> Q;
	Q.push(start);
	visited1[start] = 1;

	cout << "visit node : ";
	/////////////// Repeat ///////////////
	while (!Q.empty()) {
		/////////// Step1: dequeue ///////////
		int reg = Q.front();
		cout << reg << " ";
		Q.pop();
		for (int i = 1; i <= n; i++) {
			if (graph[reg][i] == 1 && visited1[i] == 0) {
				/////////// Step2: enqueue ///////////
				Q.push(i);
				visited1[i] = 1;
				B_node.u[B_node.cnt] = reg;
				B_node.v[B_node.cnt++] = i;
			}
		}
	}

	cout << endl << "line : ";
	for (int i = 0; i < B_node.cnt; i++) {
		cout << B_node.u[i] << " -> " << B_node.v[i] << ", ";
	}
	cout << endl;
}

void aDPS(int s, node &D_node) {
	// 재귀호출
	for (int i = n; i >= 1; i--) {
		if (graph[s][i] == 1 && visited2[i] == 0) {
			visited2[i] = 1;
			cout << i << " ";
			D_node.u[D_node.cnt] = s;
			D_node.v[D_node.cnt++] = i;
			aDPS(i, D_node);
		}
	}
}

void DPS() {
	node D_node;
	cout << "--------------DPS Start--------------" << endl;
	// 시작정점 입력
	int start;
	cout << "Input start node" << endl;
	cin >> start;
	visited2[start] = 1;

	cout << "visit node : ";
	
	for (int i = n; i >= 1; i--) {
		if (graph[start][i] == 1 && visited2[i] == 0) {
			visited2[i] = 1;
			cout << start << " ";
			cout << i << " ";
			D_node.u[D_node.cnt] = start;
			D_node.v[D_node.cnt++] = i;
			aDPS(i, D_node);
		}
	}
	cout << endl << "line : ";
	for (int i = 0; i < D_node.cnt; i++) {
		cout << D_node.u[i] << " -> " << D_node.v[i] << ", ";
	}
	cout << endl;
}



int main() {
	cout << "################# 12171213 최소현 ################ "<< endl;
	
	// 개수, matrix 입력받고 초기화
	cout << "Input N" << endl;
	cin >> n;
	cout << "Input adjacency matrix" << endl;

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++)
			cin >> graph[i][j];
	}

	BPS();
	DPS();

	return 0;
}