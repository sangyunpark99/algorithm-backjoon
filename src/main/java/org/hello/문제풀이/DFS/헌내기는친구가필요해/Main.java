package org.hello.문제풀이.DFS.헌내기는친구가필요해;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static char[][] map;

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for(int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        visited = new boolean[N][M];

        int startY = 0;
        int startX = 0;
        boolean finish = false;
        for(int i = 0; i < N; i++) {
            if(finish) break;
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 'I') { // 도연이 위치 찾기
                    startY = i;
                    startX = j;
                    map[i][j] = 'I';
                    finish = true;
                    break;
                }
            }
        }

        visited[startY][startX] = true;
        int meetPerson = dfs(startY, startX); // 어디서든 출발해도 상관 없다.

        System.out.println(meetPerson == 0 ? "TT" : meetPerson);
    }

    private static int dfs(int y, int x) {

        int total = 0;

        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx] || map[ny][nx] == 'X') continue;
            visited[ny][nx] = true;
            if(map[ny][nx] == 'P') { // 친구를 만난 경우
                total++;
            }

            total += dfs(ny, nx);
        }

        return total;
    }
}
