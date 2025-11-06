package org.hello.문제풀이.DFS.빙산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;

    private static int[][] map;

    private static int[] dy = {-1,0,1,0};
    private static int[] dx = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        while(!isMeltingAll()) {
            answer++; // 1년
            melting();

            if(checkConnectedComponent()) { // 덩어리가 2개 이상인 경우
                System.out.println(answer);
                return;
            }
        }

        System.out.println(0);
    }

    private static void melting() {
        int[][] newMap = new int[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                int value  = map[i][j];
                if(value != 0) { // 물이 아니라면
                    int waterCnt = 0;
                    for(int k = 0; k < 4; k++) {
                        int ny = i + dy[k];
                        int nx = j + dx[k];
                        if(ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
                        if(map[ny][nx] == 0) waterCnt++;
                    }
                    newMap[i][j] = Math.max(0, map[i][j] - waterCnt);
                }else { // 물이라면
                    newMap[i][j] = 0; // 0으로 초기화
                }
            }
        }

        map = newMap;
    }

    private static boolean checkConnectedComponent() {

        int cnt = 0;
        boolean[][] visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j] && map[i][j] != 0) {
                    cnt++;
                    visited[i][j] = true;
                    dfs(visited, i, j);
                }
            }
        }

        return cnt >= 2;
    }

    private static void dfs(boolean[][] visited, int curY, int curX) {
        for(int i = 0; i < 4; i++) {
            int ny = curY + dy[i];
            int nx = curX + dx[i];

            if(ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx] || map[ny][nx] == 0) continue;
            visited[ny][nx] = true;
            dfs(visited, ny, nx);
        }
    }

    private static boolean isMeltingAll() { // 전부 녹은 경우
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != 0) { // 하나라도 녹아있지 않은 경우
                    return false;
                }
            }
        }

        return true;
    }
}
