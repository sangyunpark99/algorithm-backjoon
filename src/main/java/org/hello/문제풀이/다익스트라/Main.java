package org.hello.문제풀이.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 최소한의 소들만

    private static int N;
    private static int M;
    private static List<List<int[]>> graph = new ArrayList<>();
    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N+1];

        Arrays.fill(dist,  Integer.MAX_VALUE);

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[]{b, weight});
            graph.get(b).add(new int[]{a, weight});
        }

        int start = 1;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // 소 가중치가 더 작은 경우
        dist[start] = 0; // 시작 지점은 0
        pq.offer(new int[]{start, 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNumber = cur[0];

            for(int i = 0; i < graph.get(curNumber).size(); i++) {
                int[] next = graph.get(curNumber).get(i);
                int nextNumber = next[0];
                int nextWeight = next[1];

                if(dist[nextNumber] > dist[curNumber] + nextWeight) { // 더 작은 경로인 경우
                    dist[nextNumber] = dist[curNumber] + nextWeight;
                    pq.offer(new int[]{nextNumber, dist[nextNumber]});
                }
            }
        }

        System.out.println(dist[N]);
    }
}
