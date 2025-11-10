package org.hello.문제풀이.BFS.다리만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int[][] map;
    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

       map = new int[N][N];

       for(int i = 0; i < N; i++) {
           map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
       }

       boolean[][] visited = new boolean[N][N];
       int country = 1;
       for(int i = 0; i < N; i++) { // 10000
           for(int j = 0; j < N; j++) {
               if(map[i][j] == 1 && !visited[i][j]) {
                   visited[i][j] = true;
                   map[i][j] = country;
                   bfs(i, j, country++, visited); //
               }
           }
       }

       System.out.println(shortestPath());
    }

    private static int shortestPath() {

        Queue<int[]> queue = new ArrayDeque<>();
        int[][] owner = new int[N][N];
        int[][] dist = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] > 0) { //
                    owner[i][j] = map[i][j];
                    dist[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int ans = Integer.MAX_VALUE;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curY = cur[0];
            int curX = cur[1];
            for(int i = 0; i < 4; i++) {
                int ny = curY + dy[i];
                int nx = curX + dx[i];

                if(ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                if(map[ny][nx] == 0 && owner[ny][nx] == 0) { // 주인이 없는 바다를 만난 경우
                    owner[ny][nx] = owner[curY][curX];
                    dist[ny][nx] = dist[curY][curX] + 1; // 이동 거리
                    queue.offer(new int[]{ny, nx});
                }else if(owner[ny][nx] != owner[curY][curX]) { // 다른 육지를 만난 경우
                    ans = Math.min(ans, dist[ny][nx] + dist[curY][curX]);
                    if(ans == 1) return 1;
                }
            }
        }

        return ans;
    }


    private static void bfs(int y, int x, int country, boolean[][] visited) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{y,x});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cury = cur[0];
            int curx = cur[1];

            for(int i = 0; i < 4; i++) {
                int ny = cury + dy[i];
                int nx = curx + dx[i];
                if(ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx] || map[ny][nx] == 0) continue;
                visited[ny][nx] = true;
                map[ny][nx] = country;
                queue.offer(new int[]{ny,nx});
             }
        }
    }
}
