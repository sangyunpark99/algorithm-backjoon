package org.hello.문제풀이.시뮬레이션.마법사상어와파이어스톰;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int Q;
    private static int[][] A;
    private static int[] levels;

    private static int[] dy = {-1,0,1,0};
    private static int[] dx = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        int n = (int) Math.pow(2, N);

        A = new int[n][n];

        for(int i = 0; i < n; i++) {
            A[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        levels = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int level : levels) {
            int block = 1 << level;
            rotate(block);
            melt();
        }

        System.out.println(sum());
        System.out.println(getLump());

    }

    private static void rotate(int size) {
        int n = A.length;
        int[][] tmp = new int[n][n];

        for(int sy = 0; sy < n; sy += size) {
            for(int sx = 0; sx < n; sx += size) {
                for(int i = 0; i < size; i++) {
                    for(int j = 0; j < size; j++) {
                        tmp[sy + j][sx + (size - 1 - i)] = A[sy + i][sx + j];
                    }
                }
            }
        }

        A = tmp;
    }

    private static void melt() {
        int n = A.length;

        boolean[][] check = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {

                int iceCnt = 0;
                for(int d = 0; d < 4; d++) {
                    int ny = i + dy[d];
                    int nx = j + dx[d];

                    if(ny < 0 || ny >= n || nx < 0 || nx >= n) continue;
                    if(A[ny][nx] >= 1) iceCnt++;
                }

                if(iceCnt < 3) check[i][j] = true;
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(check[i][j] && A[i][j] > 0) A[i][j]--;
            }
        }
    }

    private static int sum() {
        int n = A.length;
        int sum = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                sum += A[i][j];
            }
        }

        return sum;
    }

    private static int getLump() { // 가장 큰 덩어리 찾기
        int n = A.length;
        boolean[][] visited = new boolean[n][n];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0,0});
        int size = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j] && A[i][j] > 0) {
                    visited[i][j] = true;
                    size = Math.max(size,bfs(i,j, visited));
                }
            }
        }

        return size;

    }

    private static int bfs(int y, int x, boolean[][] visited) {

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{y,x});
        int size = 1;

        int n = A.length;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cury = cur[0];
            int curx = cur[1];

            for(int d = 0; d < 4; d++) {
                int ny = cury + dy[d];
                int nx = curx + dx[d];

                if(ny < 0 || ny >= n || nx < 0 || nx >= n || visited[ny][nx]) continue;
                if(A[ny][nx] < 1) continue;

                visited[ny][nx] = true;
                size++;
                queue.offer(new int[]{ny, nx});
            }
        }

        return size;
    }
}