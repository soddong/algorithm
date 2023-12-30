import java.util.Scanner;
import java.io.*;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
 
        for(int tc = 1; tc <= T; tc++) {
            int answer = 0;
 
            int n = sc.nextInt();
            int m = sc.nextInt();
 
            int[] A = new int[n];
            int[] B = new int[m];
 
            for (int i = 0; i < n; i++) A[i] = sc.nextInt();
            for (int i = 0; i < m; i++) B[i] = sc.nextInt();
 
            if (n == m) {
                for (int i = 0; i < n; i++) {
                    answer += A[i] * B[i];
                }
            }
 
            int j_s;
            if (n < m) {
                for (int i = 0; i < m-n+1; i++) {
                    int sum = 0;
                    j_s = 0;
                    for (int j_b = i; j_b < i+n; j_b++) {
                        sum += A[j_s] * B[j_b];
                        j_s += 1;
                    }
                    if (answer < sum)   answer = sum;
                }
            }
 
            if (n > m) {
                for (int i = 0; i < n-m+1; i++) {
                    int sum = 0;
                    j_s = 0;
                    for (int j_b = i; j_b < i+m; j_b++) {
                        sum += B[j_s] * A[j_b];
                        j_s += 1;
                    }
                    if (answer < sum)   answer = sum;
                }
            }
 
            System.out.printf("#%d %d\n", tc, answer);
        }
    }
}