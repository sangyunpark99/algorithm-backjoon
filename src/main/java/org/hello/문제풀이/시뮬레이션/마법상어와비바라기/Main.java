package org.hello.문제풀이.시뮬레이션.마법상어와비바라기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int[][] map;
    private static boolean[][] disappearCloud;

    private static int[] movedy = {0,-1,-1,-1,0,1,1,1};
    private static int[] movedx = {-1,-1,0,1,1,1,0,-1};

    private static int[] waterCopyDy = {-1,-1,1,1};
    private static int[] waterCopyDx = {-1,1,1,-1};

    private static Queue<int[]> cloud = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        cloud.offer(new int[] {N - 2, 0});
        cloud.offer(new int[] {N - 2, 1});
        cloud.offer(new int[] {N - 1, 0});
        cloud.offer(new int[] {N - 1, 1});

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int move = Integer.parseInt(st.nextToken());
            disappearCloud = new boolean[N][N];

            int size = cloud.size();
            for(int cnt = 0; cnt < size; cnt++) { // 구름 이동
                int[] cur = cloud.poll();
                int ny = cur[0];
                int nx = cur[1];
                for(int m = 0; m < move; m++) {
                    ny = (ny + movedy[d] + N) % N;
                    nx = (nx + movedx[d] + N) % N;
                }
                cloud.offer(new int[]{ny, nx});
            }

            // 비 내리기
            Queue<int[]> waterUp = new ArrayDeque<>(); // 물이 증가한 칸
            while(!cloud.isEmpty()) {
                int[] cur = cloud.poll();
                int ny = cur[0];
                int nx = cur[1];
                map[ny][nx]++;
                disappearCloud[ny][nx] = true; // 구름이 사라진 칸
                waterUp.offer(new int[]{ny,nx}); // 물이 증가한 칸 추가
            }

            // 물이 증가한 칸을 기주능로 물복사 진행
            while(!waterUp.isEmpty()) {
                int[] cur = waterUp.poll();
                int y = cur[0];
                int x = cur[1];

                // 대각선 방향 전부 확인
                int cnt = 0;
                for(int j = 0; j < 4; j++) {
                    int ny = y + waterCopyDy[j];
                    int nx = x + waterCopyDx[j];
                    if(ny < 0 || ny >= N || nx <0 || nx >= N) continue;
                    if(map[ny][nx] >= 1) cnt++;
                }

                map[y][x] += cnt;
            }

            // 구름 만들기
            for(int a = 0; a < N; a++) {
                for(int b = 0; b < N; b++) {
                    if(map[a][b] >= 2 && !disappearCloud[a][b]) {
                        cloud.offer(new int[]{a,b});
                        map[a][b] -= 2;
                    }
                }
            }
        }

        int answer = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                answer += map[i][j];
            }
        }

        System.out.println(answer);
    }
}
