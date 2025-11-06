package org.hello.문제풀이.이분탐색.중량제한.다익스트라풀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int M;

    private static List<List<int[]>> graph = new ArrayList<>();
    private static int[] dist;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[] {b, weight});
            graph.get(b).add(new int[] {a, weight});
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        visited = new boolean[N + 1];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return b[1] - a[1];
        });

        pq.offer(new int[]{start, Integer.MAX_VALUE});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int number = cur[0];
            int weight = cur[1];

            if(visited[number]) continue;
            visited[number] = true; // 한번 방문 했더라도 해당 지점에 더 좋은 가중치가 올 수도 있으므로 visited는 이 부분에 진행

            if(end == number) {
                break;
            }

            for(int[] next : graph.get(number)) {
                int nextNumber = next[0];
                int nextWeight = next[1];
                if(!visited[nextNumber] && dist[nextNumber] < Math.min(weight, nextWeight)) {
                    dist[nextNumber] = Math.min(weight, nextWeight);
                    pq.offer(new int[]{nextNumber, dist[nextNumber]});
                }
            }
        }

        System.out.println(dist[end]);
    }
}
