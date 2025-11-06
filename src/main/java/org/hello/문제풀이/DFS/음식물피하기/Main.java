package org.hello.문제풀이.DFS.음식물피하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 떨어진 음식물 중 제일 큰 음식물은 피해 간다.
    private static int N;
    private static int M;
    private static int K;

    private static char[][] map;
    private static boolean[][] visited;

    // 상하좌우
    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            Arrays.fill(map[i],'.');
        }

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[y - 1][x - 1] = '#';
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == '#' && !visited[i][j]) {
                    int value = 1;
                    visited[i][j] = true;
                    answer = Math.max(answer, value + dfs(i,j));
                }
            }
        }

        System.out.println(answer);
    }

    private static int dfs(int y, int x) {
        int total = 0;

        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx] || map[ny][nx] == '.') continue;

            visited[ny][nx] = true;
            if(map[ny][nx] == '#') {
                total++;
            }

            total += dfs(ny, nx);
        }

        return total;
    }
}
