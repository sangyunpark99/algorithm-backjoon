package org.hello.문제풀이.BFS.스타트링크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int F;
    private static int S;
    private static int G;
    private static int U;
    private static int D;

    private static int[] button;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        // S -> G

        button = new int[]{U,-1 * D};
        boolean[] visited = new boolean[F + 1];

        // 1. UP 버튼 누르기
        // 2. DOWN 버튼 누르기
        Queue<int[]> queue = new ArrayDeque<>();
        visited[S] = true;
        queue.offer(new int[]{S, 0}); // 현재 시점 층, 누른 횟수
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int floor = cur[0];
            int pushCnt = cur[1];

            if(floor == G) { // 가장 먼저 만난 경우가 결국 최솟값
                System.out.println(pushCnt);
                return;
            }

            for(int i = 0; i < 2; i++) {
                int next = floor + button[i];
                if(next <= 0 || next > F || visited[next]) { // 0층은 존재하지 않는다.
                    continue;
                }

                visited[next] = true;
                queue.offer(new int[]{next, pushCnt + 1});
            }
        }

        System.out.println("use the stairs");
    }
}
