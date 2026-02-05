package org.hello.문제풀이.다익스트라.미로만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    private static int n;
    private static int[][] map;
    private static int[][] room; // 방을 깬 횟수

    private static int[] dy = {-1,0,1,0};
    private static int[] dx = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        for(int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            return a[2] - b[2];
        });

        pq.offer(new int[]{0,0,0}); // y, x, 방을 깬 갯수


        room = new int[n][n];

        for(int i = 0; i < room.length; i++) {
            Arrays.fill(room[i], 2501);
        }

        room[0][0] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int y = cur[0];
            int x = cur[1];
            int value = cur[2];

            if(value > room[y][x]) continue;

            for(int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if(ny >= n || nx >= n || ny < 0 || nx < 0) continue;

                int nextCnt = value + (map[ny][nx] == 0 ? 1 : 0);

                if(room[ny][nx] > nextCnt) {
                    room[ny][nx] = nextCnt;
                    pq.offer(new int[]{ny, nx, nextCnt});
                }
            }
        }

        System.out.println(room[n - 1][n - 1]);
    }
}