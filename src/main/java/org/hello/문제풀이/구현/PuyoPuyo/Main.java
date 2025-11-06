package org.hello.문제풀이.구현.PuyoPuyo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static String[][] map;
    private static final int N = 12;
    private static final int M = 6;
    private static int[] dy = {-1,0,1,0};
    private static int[] dx = {0,1,0,-1};
    private static boolean[][] visited;
    private static int colorCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new String[N][M];
        int answer = 0; // 연쇄 카운트

        for(int i = 0; i < N; i++) {
            map[i] = br.readLine().split("");
        }

        // 각 색별로 탐색을 해야 한다.
        while(true) {
            boolean go = false;
            visited = new boolean[N][M];
            // 폭팔시킬 좌표 탐지하기
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(!map[i][j].equals(".") && !visited[i][j]) {
                        List<int[]> group = new ArrayList<>();
                        dfs(i, j, map[i][j], group);
                        if(group.size() >= 4) {
                            go = true;
                            for(int[] p : group) {
                                map[p[0]][p[1]] = ".";
                            }
                        }
                    }
                }
            }

            if(!go) break;
            answer++; // 연쇄 작용 갯수 증가
            move();
        }

        System.out.println(Math.max(0, answer));
    }

    private static void move() { // 이동 시키는 부분의 로직을 수정해야 한다.

        // 움직이는 부분을 어떻게 구현할까?
        for(int  j = 0; j < M; j++) {
            StringBuilder sb = new StringBuilder();
            for(int i = N - 1; i >= 0; i--) {
                if(!map[i][j].equals(".")) {
                    sb.append(map[i][j]);
                }
            }

            String value = sb.toString();
            value += ".".repeat( N - value.length());

            int idx = 0;
            for(int i = N - 1; i >=0; i--) {
                map[i][j] = String.valueOf(value.charAt(idx++));
            }
        }
    }

    private static void dfs(int y, int x, String color, List<int[]> group) {

        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx] || map[ny][nx].equals(".")) continue;
            if(map[ny][nx].equals(color)) {
                colorCnt++;
                visited[ny][nx] = true;
                group.add(new int[]{ny,nx});
                dfs(ny, nx, color, group);
            }
        }
    }
}
