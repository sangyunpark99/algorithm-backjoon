package org.hello.문제풀이.DFS.빵집;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int R;
    private static int C;
    private static char[][] map;
    private static boolean[][] pipe;

    private static int[] dy = {-1,0,1};
    private static int[] dx = {1,1,1};
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for(int i = 0; i < R; i++) {
            char[] arr = br.readLine().toCharArray();
            map[i] = arr;
        }

        pipe = new boolean[R][C];

        for(int i = 0; i < R; i++) {
            int startY = i;
            int startX = 0;
            if(map[startY][startX] == '.') {
                pipe[startY][startX] = true;
                dfs(startY, startX);
            }
        }

        System.out.println(answer);
    }

    private static boolean dfs(int y, int x) {

        if(x == C - 1) {
            answer++;
            return true;
        }

        for(int i = 0; i < 3; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
            if(map[ny][nx] == 'x' || pipe[ny][nx]) continue;
            pipe[ny][nx] = true;
            if(dfs(ny, nx)) return true;
        }

        return false;
    }
}