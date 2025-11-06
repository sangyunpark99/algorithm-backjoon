package org.hello.문제풀이.BFS.알고스팟;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int M;
    private static int[][] map;
    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0 ,1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        int[][] dist = new int[N][M];
        for(int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);

        Deque<int[]> dq = new ArrayDeque<>();
        dist[0][0] = 0; // 처음 지점 초기화
        dq.addFirst(new int[]{0,0});

        while(!dq.isEmpty()) {
            int[] cur = dq.poll();
            int y = cur[0];
            int x = cur[1];

            for(int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if(ny < 0 || ny >= N || nx < 0 || nx >= M) continue;

                int weight = map[ny][nx] == 1 ? 1 : 0;
                int nd = dist[y][x] + weight;
                if(nd < dist[ny][nx]) { // 가중치가 더 작은 경우만
                    dist[ny][nx] = nd;
                    if(weight == 0) dq.addFirst(new int[]{ny,nx}); // 빈 공간인 부분 먼저
                    else dq.addLast(new int[]{ny,nx}); // 그다음 벽인 부분
                }
            }
        }

        return dist[N - 1][M - 1];
    }
}
