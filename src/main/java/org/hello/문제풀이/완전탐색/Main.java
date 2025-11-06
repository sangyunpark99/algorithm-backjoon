package org.hello.문제풀이.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int[] dy = {-1,-1,0,1,1,1,0,-1};
    private static int[] dx = {0,1,1,1,0,-1,-1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0) break;

            int[][] map = new int[h][w];
            boolean[][] visited = new boolean[h][w];

            for(int i = 0; i < h; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            int cnt = 0;

            for(int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    if(!visited[i][j] && map[i][j] == 1) { // 방문 하지 않은 경우 vs 땅인 경우
                        visited[i][j] = true; // 방문 처리
                        dfs(i, j, visited, map, h, w);
                        cnt++;
                    }
                }
            }

            System.out.println(cnt);
        }
    }

    private static void dfs(int curY, int curX, boolean[][] visited, int[][] map, int h, int w) {

        for(int i = 0; i < 8; i++) {
            int ny = curY + dy[i];
            int nx = curX + dx[i];

            if(ny < 0 || ny >= h || nx < 0 || nx >= w || visited[ny][nx]) continue;
            if(map[ny][nx] == 1) {
                visited[ny][nx] = true;
                dfs(ny, nx, visited, map, h, w);
            }
        }
    }
}
