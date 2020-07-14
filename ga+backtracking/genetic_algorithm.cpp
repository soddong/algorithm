#include <iostream> 
#include <chrono> //시간측정을 위한 라이브러리
#include <random> //랜덤한 수를 출력하기 위한 라이브러리
#include<iomanip>
#include <algorithm>
using namespace std;

#define n_pop 100 //population size
#define INT_RANGE 2147483637

random_device rd;
mt19937_64 rng(rd());
uniform_int_distribution<__int64> dist(1, INT_RANGE);

int n;  //체스판 크기
int num_pop = 0; // population 크기
int** parent; // 부모세대
int** child; // 자녀세대

void swap(int* s, int a, int b);
void swap(int a, int b);
bool testnum(int j, int** vec, const int& number);
void popSort(int n);
void checkFitness(int n);
int roulletSelection();
void crossOver(int a, int b, int** c_after);
void mutation(int n, int** m_child);
void createPopulation();

// 유전자 struct
struct gen {
    int chrom[255]; // 염색체
    int fit = 0;    // 적응도
};

struct gen my_gen[n_pop + 1];

int main() {
    cout << "################# 12171213 최소현 ###################" << endl;

    //필요한 변수들 선언
    int generation = 1;
    int loop = 0;
    double elit = 0.05;


    cout << "Enter n: ";
    cin >> n;  // 퀸의 개수 입력
    cout << endl;

    double start_time;
    int num_pop = 0;


    chrono::system_clock::time_point start = chrono::system_clock::now();
   

    createPopulation(); //세대 생성 및 초기화

    while (generation != 1000000) {
        checkFitness(n);  //적응도 체크
        for (int row = 0; row < n_pop; row++) {
            if (my_gen[row].fit == n * (n - 1)) { //한 세트를 찾으면 출력
                for (int i = 0; i < n; i++) {
                    for (int k = 1; k <= n; k++) {
                        if (k == my_gen[row].chrom[i]) cout << "Q ";
                        else cout << "- ";
                    }
                    cout << endl;// << "i = " << i << endl;
                }
                chrono::system_clock::time_point end = chrono::system_clock::now();
                chrono::duration<double, std::milli> d = end - start;
                cout << endl<< "Genetic Algorithm 의 실행시간: " << d.count() << " milli seconds" << endl;
                exit(0);
            }
        }
        while (loop != n_pop / 2) { // 두 개체를 임의로 선택
            int parent1 = roulletSelection();  
            int parent2 = roulletSelection();
            loop++;
            crossOver(parent1, parent2, child); //선택한 두 부모를 교차
        }

        int elite = n_pop * elit;
        for (int i = 0; i < elite; i++) {
            for (int j = 0; j < n; j++) {
                child[i][j] = my_gen[i].chrom[j];
            }
        }
        for (int i = 0; i < n_pop; i++) {
            for (int j = 0; j < n; j++) {
                my_gen[i].chrom[j] = child[i][j];
            }
        }
        generation++;
    }
    if (generation == 1000000) { 
        cout << "failed" << endl;
    }
    return 0;
}

void createPopulation() {

    parent = new int* [n_pop]; // 부모 유전자
    child = new int* [n_pop]; // 자식 유전자
    for (int i = 0; i < n_pop; i++) {
        parent[i] = new int[n + 1];
        child[i] = new int[n + 1];
    }

    //배열 초기화
    for (int i = 0; i < n_pop; i++) {
        for (int j = 0; j < n; j++) {
            parent[i][j] = 0; //부모는 0
            child[i][j] = 1; //자식은 1
        }
    }
    for (int i = 0; i < n_pop; i++) {
        for (int j = 0; j < n; j++) {
            num_pop = dist(rng)% n + 1;
            while (testnum(i, parent, num_pop)) { // false가 될때까지 랜덤값 생성
                num_pop = dist(rng) % n + 1;
            }
            parent[i][j] = num_pop;
            my_gen[i].chrom[j] = parent[i][j];  //유전자 정보 저장
        }
    }
}

void swap(int* s, int a, int b) {
    int temp = s[a];
    s[a] = s[b];
    s[b] = temp;
}
void swap(int a, int b) { //a와 b를 바꾸는 함수
    gen temp = my_gen[a];
    my_gen[a] = my_gen[b];
    my_gen[b] = temp;
}

bool testnum(int j, int** vec, const int& number) {
    for (int i = 0; i < n; i++) {
        if (vec[j][i] == number) {
            return true;
        }
    }
    return false;
}

void insertSort(int n) { //내림차순 정렬
    int max;
    for (int i = 0; i < n - 1; i++) {
        max = i;
        for (int j = i + 1; j < n; j++) {
            if (my_gen[j].fit > my_gen[max].fit) {
                max = j;
            }
        }
        swap(i, max);
    }
}

// 적응도를 계산하는 함수
void checkFitness(int n) { 
    // 퀸이 모두 적절하게 위치되면 적응도를 증가시킨다.
    for (int row = 0; row < n_pop; row++) { //populatinsize만큼 반복
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (my_gen[row].chrom[i] != my_gen[row].chrom[j] //같은 열에 있지 않고
                    && abs(my_gen[row].chrom[i] - my_gen[row].chrom[j]) != abs(i - j)) { //대각선 상에 있지 않으면
                    my_gen[row].fit++; //적응도 증가
                }
            }
        }
    }
    insertSort(n_pop); // 적응도 정렬
}

int roulletSelection() { //적응도에 비례하여 선택 => 룰렛을 이용한 방법
    int total_fit = 1;
    int* roullet = new int[n_pop + 1]; 
    for (int i = 0; i < n_pop; i++) {
        roullet[i] = my_gen[i].fit;
        if (i > 0) {
            roullet[i] = roullet[i] + roullet[i - 1];
        }
    }
   
    int r = dist(rng) % roullet[n_pop - 1]; + 1;
    int num = 0;
    for (int i = 0; i < n_pop; i++) {
        if (r < roullet[i]) {
            num = i;
            break;
        }
    }
    return num;
}

// Single point crossover
void crossOver(int a, int b, int** c_child) { 
    for (int i = 0; i < n * (0.7); i++) {  // 실험적인 결과인 0.7 의 확률
        c_child[a][i] = my_gen[a].chrom[i];
        c_child[b][i] = my_gen[b].chrom[i];
    }

    for (int j = n * (7 / 10); j < n; j++) {
        c_child[(n_pop - 1) - b][j] = my_gen[a].chrom[j];
        c_child[(n_pop - 1) - a][j] = my_gen[b].chrom[j];
    }

    for (int k = 0; k < n_pop; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (c_child[k][i] == c_child[k][j]) {
                    num_pop = rand() % n + 1; //0에서 n까지 랜덤하게 저장
                    while (testnum(k, c_child, num_pop)) {
                        num_pop = dist(rng) % n + 1; //0에서 n까지 랜덤하게 저장 
                    }
                    c_child[k][j] = num_pop;
                }
            }
        }
    }
}
