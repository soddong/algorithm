#pragma warning (disable : 4996)
#include <iostream>
#include <string>
#include <cstring>
using namespace std;

// next 배열 global로 선언
int next_[50];

// next배열을 만들어 주는 함수
void InitNext(char* p)
{	// 패턴의 길이
	int i, j = 0, M = strlen(p);
	// 개선된 next 알고리즘
	// 첫번째 index에는 -1을 저장
	next_[0] = -1; 
	for (i = 0, j = -1; i < M; i++, j++) { 
		// i인덱스와 j인덱스의 문자가 같으면 해당하는곳의 next값, 
		// 다른경우 j를 대입
		next_[i] = (p[i] == p[j]) ? next_[j] : j; 
		// j 가 0이상이고 다른경우 j에 next값 대입
		while ((j >= 0) && (p[i] != p[j]))
			j = next_[j];
	}
}

int KMP(char* p, char* t)
{
	// 패턴과 텍스트 길이 저장
	int i, j, M = strlen(p), N = strlen(t);
	// 패턴에 대한 next배열 저장
	InitNext(p);
	// 패턴이 모두 동일하면 패턴 발생지점 리턴
	for (i = 0, j = 0; j < M && i < N; i++, j++)
		while ((j >= 0) && (t[i] != p[j])) j = next_[j];
	if (j == M) return i - M;
	else return i;
}

void main()
{
	cout << "++++++ Algorithm H/W #2 ++++++" << endl;
	cout << "정보통신공학과 12171213 최소현" << endl;
	cout << "++++++++++++++++++++++++++++++" << endl << endl;

	//text와 pattern 동적으로 선언
	char* text = new char[100000];
	char* pattern = new char[100];
	// 변수들 선언
	int M, N, pos, previous = 0;
	int j = 0;
	int sum = 0;

	// 사용자에게 검색할 패턴 입력 받기
	cout << "Enter pattern to search" << endl;
	cin >> pattern;
	// 패턴의 길이 저장
	M = strlen(pattern);

	// 텍스트 파일을 열고 file type으로 저장
	FILE* input = fopen("RFC2616_modified.txt", "r");

	// 만약 파일을 열지 못했다면 에러 메세지 출력
	if (!input) {
		cout << "ERROR(not exist)" << endl;
		exit(1);
	}
	else {
		int i = 0;
		char buffer[1];
		// 1바이트씩(char) 읽어서 text에 저장
		while (fread(buffer, sizeof(buffer), 1, input)) {
			text[i] = buffer[0];
			i++;
		}

		// i의 마지막 값: 텍스트 크기
		N = i;
		// 문자열의 끝에 NULL 삽입
		text[N] = NULL;

		pos = 0;

		cout << endl << "Index of Positions : ";
		while (1) {
			// pos에 패턴이 발생한 위치 저장
			pos = KMP(pattern, text + j);
			// 이전에 탐색한 위치만큼 더해줌
			pos += previous;
			// 1을 더한 값 저장 
			j = pos + 1;
			// 문자열의 끝에 도달하지 않았을 경우
			if (j <= N) {
				// 패턴이 발생한 위치 출력
				cout << pos << ", ";
				// sum을 1씩 증가
				sum += 1;
			}
			// 문자열의 끝에 도달하면 탐색종료
			else break;
			previous = j;
		}
		// 패턴 횟수 출력
		cout << endl << endl << "Sum : " << sum << endl;
	}
	delete[] pattern;
	delete[] text;
}


