package org.hello.기본기.원상복구;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static int[][] map;
    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};
    private static int path = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[3][3];

        for(int i = 0; i < 3; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        go(0,0, new boolean[3][3], new ArrayList<>(List.of(map[0][0])));

        System.out.println(path);
    }

    private static void go(int curY, int curX, boolean[][] visited, List<Integer> paths) {

        if(curY == 2 && curX == 2) {
            path++;
            System.out.println(paths);
            return;
        }

        for(int i = 0; i < 4; i++) {
            int ny = curY + dy[i];
            int nx = curX + dx[i];

            if(ny < 0 || ny >= 3 || nx < 0 || nx >= 3 || visited[ny][nx]) continue;
            visited[ny][nx] = true;
            paths.add(map[ny][nx]);
            go(ny, nx, visited, paths);
            paths.remove(paths.size() - 1);
            visited[ny][nx] = false; // 해당 배열은 전역으로 공유가 된다. 왜? 배열은 주솟값을 넘기므로
        }
    }
}
