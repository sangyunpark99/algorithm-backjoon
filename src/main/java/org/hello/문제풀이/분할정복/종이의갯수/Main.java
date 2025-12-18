package org.hello.문제풀이.분할정복.종이의갯수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    // 9덩이
    // 1. 같은지 확인하는 함수
    // 2. 분배하는 부분 (세로 3 x 가로 3)

    private static int N;
    private static int[][] map;
    private static HashMap<Integer, Integer> cnts = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map  = new int[N][N];

        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 갯수
        cnts.put(-1,0);
        cnts.put(0,0);
        cnts.put(1,0);

        dfs(0, N - 1, 0, N - 1);

        StringBuilder sb = new StringBuilder();

        sb.append(cnts.get(-1)).append("\n");
        sb.append(cnts.get(0)).append("\n");
        sb.append(cnts.get(1)).append("\n");

        System.out.println(sb);
    }

    private static void dfs(int sy, int ey, int sx, int ex) {
        if(check(sy, ey, sx, ex)) {
            cnts.put(map[sy][sx], cnts.get(map[sy][sx]) + 1);
            return;
        }

        int len = ey - sy + 1;
        int step = len / 3;

        for(int dy = 0; dy < 3; dy++) { // 3칸
            for(int dx = 0; dx < 3; dx++) { // 3칸
                int nsy = sy + dy * step;
                int ney = nsy + step - 1;
                int nsx = sx + dx * step;
                int nex = nsx + step - 1;
                dfs(nsy, ney, nsx, nex);
            }
        }
    }

    private static boolean check(int startY, int endY, int startX, int endX) {
        int value = map[startY][startX];

        for(int i = startY; i <= endY; i++) {
            for(int j = startX; j <= endX; j++) {
                if(value != map[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
