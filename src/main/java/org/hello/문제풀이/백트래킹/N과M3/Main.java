package org.hello.문제풀이.백트래킹.N과M3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        permutation(0, new int[M]);

        System.out.println(sb);
    }

    private static void permutation(int depth, int[] arr) {

        if(depth == M) {
            for(int i = 0; i < M; i++) {
                sb.append(arr[i] + " ");
            }

            sb.append("\n");
            return;
        }

        for(int i = 1; i <= N; i++) {
            arr[depth] = i;
            permutation( depth + 1, arr);
        }
    }
}