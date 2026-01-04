package org.hello.문제풀이.다익스트라.지름길;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int D;
    private static List<List<int[]>> graph = new ArrayList<>();
    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= D; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < D; i++) {
            graph.get(i).add(new int[]{i + 1, 1});
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(b <= D) {
                graph.get(a).add(new int[]{b, c});
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            return a[1] - b[1];
        });

        pq.offer(new int[]{0, 0});

        dist = new int[D + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNumber = cur[0];
            int weight = cur[1];

            if(weight != dist[curNumber]) continue;

            for(int[] next: graph.get(curNumber)) {
                int nextNumber = next[0];
                int nextWeight = next[1];
                int nextDist = weight + nextWeight;
                if(dist[nextNumber] > nextDist) {
                    dist[nextNumber] = nextDist;
                    pq.offer(new int[]{nextNumber, dist[nextNumber]});
                }
            }
        }

        System.out.println(dist[D]);
    }
}
