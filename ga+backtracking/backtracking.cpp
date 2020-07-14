#include <iostream>
#include <chrono> //시간측정을 위한 라이브러리
using namespace std;

#define N 100
int pos[N] = {0};
int pos_j[N] = {0};
int print[N] = { 0 };
int n, checking;
int checking2;


int Promising(int i) {
	int j = 1;
	int checking2 = 1;
	if (i > j) {
		for (j; j < i; j++) {
			// 다른 퀸이 같은 열에 있거나
			// 대각선 상에 있으면 checking2 = 0
			if (pos[i] == pos[j] || abs(pos[i] - pos[j]) == abs(i - j)) { 
				checking2 = 0;
				if (checking2 == 0) break;
			}	
		}
	}
	return checking2;
}

void Backtracking(int i) {
	if (checking == 0) {
		if (Promising(i) == 1) { // 퀸의 공격 범위가 아니라면
			if (i == n) { // 모든 퀸이 적절한 자리를 찾았다면
				cout << endl;
				for (i = 1; i <= n; i++) { // 체스판과 퀸의 위치 출력
					for (int k = 1; k <= n; k++) {
						if (k == pos[i]) cout << "Q ";
						else cout << "- ";
					}
					cout << endl;
				}
				checking = 1; // 답을 찾았음을 표시
			}
			else {
					for (int j = 1; j <= n; j++) {
						pos[i + 1] = j;  
						Backtracking(i + 1); // i+1번째 행에 있는 퀸을 j열에 놓을 수 있는지 체크
						if (checking == 1) break; // checking 이 1이면 즉, 답을 찾았으면 break
										 
										 
					}
				
			}
		}
	}
}

int main() {
	
	time_t startTime = 0, endTime = 0; //time구조체

	cout << "################# 12171213 최소현 ###################";
	cout << endl << "Enter n: ";
	cin >> n;  //크기 입력

	chrono::system_clock::time_point start = chrono::system_clock::now();

	Backtracking(0);  //뿌리 노드(0)부터 탐색

	chrono::system_clock::time_point end = chrono::system_clock::now();
	chrono::duration<double, std::milli> d = end - start;
	cout << endl << "Backtracking Algorithm 의 실행시간: " << d.count() << " milli seconds" << endl;
				
	return 0;
}