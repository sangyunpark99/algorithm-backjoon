package org.hello.문제풀이.두포인터.CD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;

    private static int[] a;
    private static int[] b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0) break;

            a = new int[N];
            b = new int[M];

            for(int i = 0; i < N; i++) {
                a[i] = Integer.parseInt(br.readLine());
            }

            for(int i = 0; i < M; i++) {
                b[i] = Integer.parseInt(br.readLine());
            }

            int aIdx = 0;
            int bIdx = 0;
            int answer = 0;

            while(aIdx < N && bIdx < M) { // 정렬
                int aValue = a[aIdx];
                int bValue = b[bIdx];

                if(aValue == bValue) {
                    aIdx++;
                    bIdx++;
                    answer++;
                } else if(aValue < bValue){
                    aIdx++;
                }else {
                    bIdx++;
                }
            }

            System.out.println(answer);
        }
    }
}