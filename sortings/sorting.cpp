#include <iostream>
#include <chrono> //시간측정을 위한 라이브러리
#include <random> //랜덤한 수를 출력하기 위한 라이브러리
using namespace std;

#define N 1024 //2^n 의 크기로 N값을 변경
#define INT_RANGE 2147483637

void swap(int* d, int a, int b) { //d[a] 와 d[b]를 바꾸기 위한 함수
    int tmp = d[a];
    d[a] = d[b];
    d[b] = tmp;
}

//선택정렬
//입력으로 배열의 개수와 정렬되지 않은 배열을 받는다
void selection_sort(int* d, int n) {
    int index = 0;
    for (int i = 0; i < n - 1; i++) {
        int small = d[i];
        //정렬되지않은 수들 중 가장 작은 값 저장
        for (int j = i + 1; j < n; j++) {
            if (d[j] < small) {
                small = d[j];
                index = j;
            }
        }
        //swap (대입연산 3회)
        swap(d, i, index);
    }

    //for (int i = 0; i < n; i++) {
    //   cout << d[i] << " ";
    //}
    //cout << endl;
}

//error1) 배열 함수 전달 방식
// ㄴ int arr[n] 선언, selection_sort(arr) -> selection_sort(int *arr) 또는 selection_sort(int arr[n]) 전달 방식
//
//error2) 배열 접근
// ㄴ 배열의 크기가 N이라고 할때 arr[N]을 접근하려고 함 -> 이중 for문에서 index 신경쓰기

//퀵정렬
//입력으로 배열의 개수와 정렬되지 않은 배열을 받는다
void quick(int* d, int l, int r) {
    if (r - l > 1) {
        int m = (l + r) / 2;
        int tmp = 0;

        // median-of-three 과정 -> 세개의 값 정렬
        if (d[l] > d[m]) {
            swap(d, l, m);
        }
        if (d[m] > d[r]) {
            swap(d, m, r);
        }
        if (d[l] > d[m]) {
            swap(d, l, m);
        }
        int i = l + 1;  int j = r - 2;  int p = r - 1;  int pivot = d[p];
        if (r - l > 2) {
            swap(d, m, p);
            for (;;) {
                while (d[i] <= pivot && i <= j) i++;
                while (d[j] >= pivot && i <= j) j--;
                if (i > j) break;
                swap(d, i, j);
            }
        }
        swap(d, i, r - 1);
        quick(d, l, i - 1);
        quick(d, i + 1, r);

    }
     //크기가 한 개 인경우 정렬할 필요가 없으므로 끝.
}

void quick_sort(int* d, int n) {
    quick(d, 0, n - 1);

//    for (int i = 0; i < n; i++) {
//        cout << d[i] << " ";
//     }
//     cout << endl;
}

//쉘 정렬
//입력으로 배열의 개수와 정렬되지 않은 배열을 받는다
void shell_sort(int* d, int n) {
    int gap = 0;

    while (gap < n) {
        gap = 3 * gap + 1; // n보다 작은 최대의 3*gap+1 값 저장
    }
    while (gap > 1) {
        gap = gap / 3 + 1; // gap값을 줄여가면서 정렬반복
        for (int i = 0; i < gap; i++) {
            for (int j = i; j < n; j += gap) {
                int v = d[j];
                int index = j - gap;
                while ((index >= gap) && d[j] > v) {
                    d[index + gap] = d[index];
                    index -= gap;
                }
                d[index + gap] = v;

            }
        }
    }

    /*for (int i = 0; i < n; i++) {
       cout << d[i] << " ";
    }
    cout << endl;*/

    //int* data = d;

    //int k = 1;
    //while (k < n)      // n 보다 작은 k의 설정
    //    k = 3 * k + 1;

    //while (0 < k) {      // k가 0이 될 때 까지
    //    k /= 3;         // k를 3씩 나눠준다.
    //    for (int i = 0; i < k; i++) {            // i를 0부터 k-1 까지 증가
    //        for (int j = i + k; j < n; j += k) {   // i인덱스를 k번째마다 삽입정렬
    //            int key = data[j];               // key는 가장 오른쪽 값
    //            int l = j - k;                  // l은 가장 오른쪽 값의 왼쪽 값
    //            for (; 0 <= l; l -= k) {         // k씩 앞으로가면서 큰 값 찾기
    //                if (key > data[l]) {         // key값이 l번째 값보다 크면
    //                    data[l + k] = data[l];      // l번째 값은 그 뒤로 옮기고
    //                    data[l] = key;            // l번째 값은 key의 값이 된다.
    //                }
    //                else         // key 값이 더 큰 경우, 자리를 찾았으므로 break
    //                    break;
    //            }
    //            data[l + k] = key;      // 자리를 찾아서 break 해서 나왔으므로 key값을 대입
    //        }
    //    }
    //}


}

/////////////////////////////////////////////////////

void bitonic_merge(int* d, int a, int n, bool isAscending) {
    if (n == 1) return;
    int m = n / 2;
    for (int i = a; i < a + m; i++) {
        if (isAscending == (d[i] > d[i + m]))
            swap(d, i, i + m);
    }
    bitonic_merge(d, a, m, isAscending);
    bitonic_merge(d, a + m, m, isAscending);

}

void bitonic_sort(int* d, int a, int n, bool isAscending) {
    // n이 1이면 정렬할 필요가 없기 때문에 return
    if (n == 1) return;
    int m = n / 2;

    bitonic_sort(d, a, m, true);
    bitonic_sort(d, a + m, m, false);

    bitonic_merge(d, a, n, isAscending);

    //for (int i = n - 1; i >= 0; i--) {
    //   cout << d[i] << " ";
    //}
    //cout << endl;
}


void oddeven_merge(int* d, int a, int n, int r) {
    int m = r * 2;

    if (m < n) {
        oddeven_merge(d, a, n, m);
        oddeven_merge(d, a + r, n, m);

        for (int i = a + r; i + r < a + n; i += m) {
            if (d[i] > d[i + r]) swap(d, i, i + r);
        }
    }

    else {
        if (d[a] > d[a + r]) swap(d, a, a + r);
    }

}
void oddeven_sort(int* d, int a, int n) {
    if (n > 1) {
        int m = n / 2;
        oddeven_sort(d, a, m);
        oddeven_sort(d, a + m, m);
        oddeven_merge(d, a, n, 1);
    }

    //for (int i = 0; i < n; i++) {
    //   cout << d[i] << " ";
    //}
    //cout << endl;

}

int main() {

    //정렬할 원소들을 담을 배열 선언 및 초기화
    int arr0[N];
    int arr1[N];
    int arr2[N];
    int arr3[N];
    int arr4[N];

    random_device rd;
    mt19937_64 rng(rd());
    uniform_int_distribution<__int64> dist(1, INT_RANGE);

    ////0~999의 값을 가지는 랜덤한 값을 배열에 넣기
    for (int i = 0; i < N; i++) {
        int temp = dist(rng); // 난수 생성
        arr0[i] = temp;
        arr1[i] = temp;
        arr2[i] = temp;
        arr3[i] = temp;
        arr4[i] = temp;
    }

    cout << "N의 값: " << N << endl;

    //-----------------------------------------------------------------------------------------
    chrono::system_clock::time_point start1 = chrono::system_clock::now();
    selection_sort(arr0, N);
    chrono::system_clock::time_point end1 = chrono::system_clock::now();
    chrono::duration<double, std::milli> d1 = end1 - start1;
    cout << "selection sort의 실행시간: " << d1.count() << " milli seconds" << endl;
    //cout << "selection sort의 실행시간: ... milli seconds" << endl;
    //-----------------------------------------------------------------------------------------
    chrono::system_clock::time_point start2 = chrono::system_clock::now();
    quick_sort(arr1, N);
    chrono::system_clock::time_point end2 = chrono::system_clock::now();
    chrono::duration<double, std::milli> d2 = end2 - start2;
    cout << "median-of-three quick sort의 실행시간: " << d2.count() << " milli seconds" << endl;
    //cout << "median-of-three quick sort의 실행시간: ... milli seconds" << endl;
    //-----------------------------------------------------------------------------------------
    chrono::system_clock::time_point start3 = chrono::system_clock::now();
    shell_sort(arr2, N);
    chrono::system_clock::time_point end3 = chrono::system_clock::now();
    chrono::duration<double, std::milli> d3 = end3 - start3;
    cout << "shell sort의 실행시간: " << d3.count() << " milli seconds" << endl;
    //cout << "shell sort의 실행시간: ... milli seconds" << endl;
    //-----------------------------------------------------------------------------------------
    chrono::system_clock::time_point start4 = chrono::system_clock::now();
    bitonic_sort(arr3, 0, N, true);
    chrono::system_clock::time_point end4 = chrono::system_clock::now();
    chrono::duration<double, std::milli> d4 = end4 - start4;
    cout << "bitonic sort의 실행시간: " << d4.count() << " milli seconds" << endl;
    //cout << "bitonic sort의 실행시간: ... milli seconds" << endl;
    //-----------------------------------------------------------------------------------------
    chrono::system_clock::time_point start5 = chrono::system_clock::now();
    oddeven_sort(arr4, 0, N);
    chrono::system_clock::time_point end5 = chrono::system_clock::now();
    chrono::duration<double, std::milli> d5 = end5 - start5;
    cout << "odd-even merge sort의 실행시간: " << d5.count() << " milli seconds" << endl;
    //cout << "odd-even merge sort의 실행시간: ... milli seconds" << endl;

    return 0;
}