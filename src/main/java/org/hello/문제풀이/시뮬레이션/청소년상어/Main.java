package org.hello.문제풀이.시뮬레이션.청소년상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] dy = {0,-1,-1,0,1,1,1,0,-1};
    private static int[] dx = {0,0,-1,-1,-1,0,1,1,1};

    private static class Fish {
        int y, x, dir;
        boolean alive;

        Fish(int y, int x, int dir, boolean alive) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.alive = alive;
        }

        Fish(Fish other) {
            this.y = other.y;
            this.x = other.x;
            this.dir = other.dir;
            this.alive = other.alive;
        }
    }

    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] board = new int[4][4];
        Fish[] fish = new Fish[17];

        for(int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                board[i][j] = num;
                fish[num] = new Fish(i, j, dir, true);
            }
        }

        int firstFish = board[0][0];
        Fish f = fish[firstFish];
        int sharkDir = f.dir;
        f.alive = false;
        int eat = firstFish;
        board[0][0] = -1; // 상어 위치 -1
        dfs(board,fish,0,0,sharkDir,eat);

        System.out.println(answer);
    }

    private static void dfs(int[][] board, Fish[] fish, int sy, int sx, int sdir, int eatCnt) {
        answer = Math.max(answer, eatCnt);

        int[][] b = copyBoard(board);
        Fish[] f = copyFish(fish);

        moveFish(b, f, sy, sx); // 물고기 움직이기

        for(int step = 1; step <= 3; step++) { // 한칸, 두칸, 세칸 - 지나치는 경우도 포함
            int ny = sy + dy[sdir] * step;
            int nx = sx + dx[sdir] * step;

            if(ny < 0 || ny >= 4 || nx < 0 || nx >= 4) break;
            if(b[ny][nx] <= 0) continue;

            int[][] nextBoard = copyBoard(b);
            Fish[] nextFish = copyFish(f);

            int eatNumber = nextBoard[ny][nx];
            int nextDir = nextFish[eatNumber].dir;
            nextFish[eatNumber].alive = false;

            nextBoard[sy][sx] = 0;
            nextBoard[ny][nx] = -1;
            dfs(nextBoard, nextFish, ny, nx, nextDir, eatCnt + eatNumber);
        }
    }

    private static void moveFish(int[][] board, Fish[] fish, int sy, int sx) {
        for(int number = 1; number <= 16; number++) {
            Fish f = fish[number];
            if(f == null || !f.alive) continue;

            int y = f.y;
            int x = f.x;
            int dir = f.dir;

            for(int d = 0; d < 8; d++) { // 반시계 방향 회전
                int ndir = dir + d;
                if(ndir > 8) ndir -= 8;

                int ny = y + dy[ndir];
                int nx = x + dx[ndir];

                if(ny < 0 || ny >= 4 || nx < 0 || nx >= 4) continue;
                if(ny == sy && nx == sx) continue;

                int targetNum = board[ny][nx];

                if(targetNum == 0) {
                    board[y][x] = 0;
                    board[ny][nx] = number;
                    f.y = ny;
                    f.x = nx;
                    f.dir = ndir;
                }else if(targetNum > 0) {
                    Fish tf = fish[targetNum];

                    board[y][x] = targetNum;
                    board[ny][nx] = number;

                    tf.y = y;
                    tf.x = x;

                    f.y = ny;
                    f.x = nx;
                    f.dir = ndir;
                }
                break; // 다음 물고기 이동
            }
        }
    }

    private static Fish[] copyFish(Fish[] fish) {
        Fish[] copy = fish.clone();
        for(int i = 1; i <= 16; i++) {
            if(fish[i] != null) {
                copy[i] = new Fish(fish[i]);
            }
        }

        return copy;
    }

    private static int[][] copyBoard(int[][] board) {
        int[][] copy = new int[4][4];
        for(int i = 0; i < 4; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, 4);
        }
        return copy;
    }
}