package org.hello.문제풀이.LCS.LCS4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int[] A;
    private static int[] B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] pos = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int v = Integer.parseInt(st.nextToken());
            pos[v] = i;
        }

        int[] tails = new int[N];
        int len = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int v = Integer.parseInt(st.nextToken());
            int x = pos[v];

            int idx = Arrays.binarySearch(tails, 0, len, x);
            if(idx < 0) idx = -(idx + 1);

            tails[idx] = x;
            if(idx == len) len++;
        }

        System.out.println(len);
    }
}
