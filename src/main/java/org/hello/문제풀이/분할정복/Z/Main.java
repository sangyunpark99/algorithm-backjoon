package org.hello.문제풀이.분할정복.Z;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int r;
    private static int c;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int answer = go(N,r,c);

        System.out.println(answer);
    }

    private static int go(int n, int y, int x) {
        if(n == 0) return 0;

        int half = 1 << (n - 1);
        int size = half * half;

        if(y < half && x < half) {
            return go(n - 1, y , x);
        }else if(y < half && x >= half) {
            return size + go(n - 1, y, x - half);
        }else if(y >= half && x < half) {
            return 2 * size + go(n - 1, y - half, x);
        }else {
            return 3 * size + go(n - 1, y - half, x - half);
        }
    }
}
