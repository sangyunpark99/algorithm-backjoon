package org.hello.문제풀이.BFS.불;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int T;
    private static int w;
    private static int h;

    private static int humanY = 0;
    private static int humanX = 0;
    private static List<int[]> fires = new ArrayList<>();

    private static int[][] fireMove;
    private static char[][] map;

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        StringTokenizer st;

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            fires.clear();

            map = new char[h][w];

            for(int i = 0; i < h; i++) {
                char[] value = br.readLine().toCharArray();
                for(int j = 0; j < value.length; j++) {
                    if(value[j] == '@') {
                        humanY = i;
                        humanX = j;
                    }else if(value[j] == '*') {
                        fires.add(new int[]{i,j});
                    }
                    map[i][j] = value[j];
                }
            }

            // 불
            fireMove = new int[h][w];
            fireMove();

            int answer = humanMove();

            if(answer == -1) {
                sb.append("IMPOSSIBLE").append("\n");
            }else {
                sb.append(answer).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static void fireMove() {

        Queue<int[]> queue = new ArrayDeque<>();
        for(int i = 0; i < fires.size(); i++) {
            int[] fire = fires.get(i);
            queue.offer(new int[]{fire[0], fire[1], 1});
        }

        while(!queue.isEmpty()) {
            int[] fire = queue.poll();
            int y = fire[0];
            int x = fire[1];
            int cnt = fire[2];

            for(int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if(ny < 0 || ny >= h || nx < 0 || nx >= w) continue;
                if(fireMove[ny][nx] > 0 || map[ny][nx] == '#') continue;
                if(map[ny][nx] == '.' || map[ny][nx] == '@') {
                    fireMove[ny][nx] = cnt + 1;
                    queue.offer(new int[]{ny,nx,fireMove[ny][nx]});
                }
            }
        }
    }

    private static int humanMove() {

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{humanY, humanX, 1});

        boolean[][] visited = new boolean[h][w];
        visited[humanY][humanX] = true;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            int cnt = cur[2];

            if(y == 0 || y == h - 1 || x == 0 || x == w - 1) { // 경계에 있는 경우
                return cnt;
            }

            for(int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if(ny < 0 || ny >= h || nx < 0 || nx >= w) continue;
                if(map[ny][nx] == '#' || map[ny][nx] == '*') continue;
                if (!visited[ny][nx] && (fireMove[ny][nx] == 0 || fireMove[ny][nx] > cnt + 1)) {
                    visited[ny][nx] = true;
                    queue.offer(new int[]{ny, nx, cnt + 1});
                }
            }
        }

        return -1;
    }
}