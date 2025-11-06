package org.hello.문제풀이.DFS.치즈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int[][] map;
    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 1. 외부 공간 마킹
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int answer = 0;

        while(!checkCheese()) {
            checkOuterSpace(0, 0, new boolean[N][M]); // 외부 공간 마크

            List<int[]> melt = new ArrayList<>();

            // 왜곡되지 않게 따로 모아두었다가 한번에 녹이기
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(map[i][j] == 1) {
                        int cnt = 0;
                        for(int d = 0; d < 4; d++) {
                            int ny = i + dy[d];
                            int nx = j + dx[d];

                            if(ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                            if(map[ny][nx] == -1) { // 외부랑 맞닿아 있는 경우
                                cnt++;
                            }

                            if(cnt >= 2) {
                                melt.add(new int[]{i, j});
                                break;
                            }
                        }
                    }
                }
            }

            for(int i = 0; i < melt.size(); i++) {
                int[] arr =  melt.get(i);
                map[arr[0]][arr[1]] = 0; // 녹이기
            }

            answer++;
        }

        System.out.println(answer);
    }

    private static boolean checkCheese() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 1) { // 치즈가 하나라도 있는 경우
                    return false;
                }
            }
        }

        return true;
    }

    private static void checkOuterSpace(int startY, int startX, boolean[][] visited) {
        visited[startY][startX] = true;
        map[startY][startX] = -1;

        for(int i = 0; i < 4; i++) {
            int ny = startY + dy[i];
            int nx = startX + dx[i];
            if(ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx] || map[ny][nx] == 1) continue;
            checkOuterSpace(ny, nx, visited);
        }
    }
}
