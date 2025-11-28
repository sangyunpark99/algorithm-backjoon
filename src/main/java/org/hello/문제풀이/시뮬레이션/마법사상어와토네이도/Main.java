package org.hello.문제풀이.시뮬레이션.마법사상어와토네이도;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int N;
    private static int[][] sand;
    private static int[] dy = {0,1,0,-1};
    private static int[] dx = {-1,0,1,0};
    private static int answer = 0;

    private static int[][] basePattern = {
            {-1,1,1},
            {1,1,1},
            {-1,0,7},
            {1,0,7},
            {-2,0,2},
            {2,0,2},
            {-1,-1,10},
            {1,-1,10},
            {0,-2,5}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        sand = new int[N][N];

        for(int i = 0; i < N; i++) {
            sand[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        moveTornado();
        System.out.println(answer);
    }

    private static void moveTornado() {
        int y = N / 2;
        int x = N / 2;
        int dir = 0;
        int len = 1;

        outer:
        while(true) {
            for(int t = 0; t < 2; t++) {
                for(int k = 0; k < len; k++) {
                    y += dy[dir];
                    x += dx[dir];
                    spread(y, x, dir);
                    if(y == 0 && x == 0) break outer;
                }
                dir = (dir + 1) % 4;
            }
            len++;
        }
    }

    private static void spread(int y, int x, int dir) {
        int amount = sand[y][x];
        if(amount == 0) return;

        int remain = amount;
        sand[y][x] = 0;

        for(int[] p: basePattern) {
            int by = p[0];
            int bx = p[1];
            int percent = p[2];

            int ty = 0;
            int tx = 0;
            if(dir == 0) { // 왼
                ty = y + by;
                tx = x + bx;
            } else if (dir == 1) { // 아
                ty = y - bx;
                tx = x + by;
            } else if (dir == 2) { // 오
                ty = y - by;
                tx = x - bx;
            }else { // 위
                ty = y + bx;
                tx = x - by;
            }

            int spreadAmount = amount * percent / 100;
            remain -= spreadAmount;

            if(ty < 0 || ty >= N || tx < 0 || tx >= N) {
                answer += spreadAmount;
            }else {
                sand[ty][tx] += spreadAmount;
            }
        }

        int ay = 0;
        int ax = 0;
        if(dir == 0) {
            ay = y;
            ax = x - 1;
        }else if(dir == 1) {
            ay = y + 1;
            ax = x;
        }else if(dir == 2) {
            ay = y;
            ax = x + 1;
        }else {
            ay = y - 1;
            ax = x;
        }

        if(ay < 0 || ay >= N || ax < 0 || ax >= N) {
            answer += remain;
        }else {
            sand[ay][ax] += remain;
        }
    }
}