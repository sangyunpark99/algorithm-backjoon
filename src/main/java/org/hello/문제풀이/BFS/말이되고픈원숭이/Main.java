package org.hello.문제풀이.BFS.말이되고픈원숭이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int K;
    private static int W;
    private static int H;
    private static int[][] map;
    private static int[] dy = {-1,0,1,0};
    private static int[] dx = {0,1,0,-1};

    // 말이 이동하는 방향
    private static int[] horsedy = {-2,-1,1,2,2,1,-1,-2};
    private static int[] horsedx = {1,2,2,1,-1,-2,-2,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken()); // 200
        H = Integer.parseInt(st.nextToken()); // 200

        map = new int[H][W];

        for(int i = 0; i < H; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        boolean[][][] visited = new boolean[H][W][K+1];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0,0,K,0}); // y,x,k,cnt

        int answer = -1;

        while(!queue.isEmpty()) { //
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            int k = cur[2];
            int cnt = cur[3];

            if(y == H - 1 && x == W - 1) {
                answer = cnt;
                break;
            }

            if(k > 0) { // k를 사용한 경우
                for(int i = 0; i < 8; i++) {
                    int ny = y + horsedy[i];
                    int nx = x + horsedx[i];

                    if(ny < 0 || ny >= H || nx < 0 || nx >= W || visited[ny][nx][k-1]) continue;
                    if(map[ny][nx] == 0) { // 도착한 곳이 벽이면 안된다.
                        visited[ny][nx][k-1] = true;
                        queue.offer(new int[]{ny,nx,k-1,cnt+1});
                    }
                }
            }

            for(int i = 0; i < 4; i++) { // 상하좌우 이동
                int ny = y + dy[i];
                int nx = x + dx[i];

                if(ny < 0 || ny >= H || nx < 0 || nx >= W || visited[ny][nx][k] || map[ny][nx] == 1) continue;
                if(map[ny][nx] == 0) {
                    visited[ny][nx][k] = true;
                    queue.offer(new int[]{ny,nx,k,cnt+1});
                }
            }
        }

        System.out.println(answer);
    }
}