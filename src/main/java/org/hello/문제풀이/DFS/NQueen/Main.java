package org.hello.문제풀이.DFS.NQueen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int N;
    private static int[][] block;
    private static int answer;

    // 대각
    private static int[] queendy = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] queendx = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        block = new int[N][N];

        dfs(0);
        System.out.println(answer);
    }

    private static void dfs(int y) {
        if(y == N) {
            answer++;
            return;
        }

        for(int x = 0; x < N; x++) {
            if(block[y][x] > 0) continue;
            place(y, x, 1);
            dfs(y + 1); // 다음 행
            place(y, x, -1);
        }
    }

    private static void place(int y, int x, int v) {
        block[y][x] += v;
        for(int i = 0; i < 8; i++) {
            int ny = y;
            int nx = x;
            while(true) { // 맵이 끝날때까지 반복
                ny += queendy[i];
                nx += queendx[i];
                if(ny < 0 || ny >= N || nx < 0 || nx >= N) break;
                block[ny][nx] += v;
            }
        }
    }
}
