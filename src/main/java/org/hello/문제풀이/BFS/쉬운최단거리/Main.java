package org.hello.문제풀이.BFS.쉬운최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int[][] map;
    private static int[] dy = {-1, 0, 1, 0}; // 아래, 오른쪽
    private static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int startY = 0;
        int startX = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 2) {
                    startY = i;
                    startX = j;
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        int[][] visited = new int[N][M];
        visited[startY][startX] = 1;
        queue.offer(new int[]{startY, startX});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];

            for(int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if(ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx] != 0 || map[ny][nx] == 0) continue;
                visited[ny][nx] = visited[y][x] + 1;
                queue.offer(new int[]{ny, nx});
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0) { // 원래 갈 수 없는 땅인 경우
                    System.out.print("0" + " ");
                }else {
                    System.out.print(visited[i][j] - 1 + " ");
                }
            }
            System.out.println();
        }
    }
}
