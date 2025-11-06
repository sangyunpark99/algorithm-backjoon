package org.hello.문제풀이.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int K;
    private static int[] dist;
    private static final int MAX = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[MAX + 1];
        Arrays.fill(dist, -1);

        System.out.println(bfs(N, K));
    }

    private static int bfs(int start, int K) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(start);
        dist[start] = 0;

        while(!dq.isEmpty()) {
            int cur = dq.poll();
            if(cur == K) break;

            int nx = cur * 2;
            if(inRange(nx) && dist[nx] == -1) {
                dist[nx] = dist[cur];
                dq.addFirst(nx);
            }

            nx = cur - 1;
            if(inRange(nx) && dist[nx] == -1) {
                dist[nx] = dist[cur] + 1;
                dq.addLast(nx);
            }

            nx = cur + 1;
            if(inRange(nx) && dist[nx] == -1) {
                dist[nx] = dist[cur] + 1;
                dq.addLast(nx);
            }
        }

        return dist[K];
    }

    private static boolean inRange(int x) {
        return 0 <= x && x <= MAX;
    }
}
