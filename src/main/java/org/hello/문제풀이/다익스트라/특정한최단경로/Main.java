package org.hello.문제풀이.다익스트라.특정한최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int E;
    private static List<List<int[]>> graph = new ArrayList<>();
    private static int[] goNode;
    private static final int INF = Integer.MAX_VALUE / 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(a).add(new int[]{b,w});
            graph.get(b).add(new int[]{a,w});
        }

        goNode = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dist1 = findShortestPath(1);
        int[] dist2 = findShortestPath(goNode[0]);
        int[] dist3 = findShortestPath(goNode[1]);

        int path1 = dist1[goNode[0]] + dist2[goNode[1]] + dist3[N];
        int path2 = dist1[goNode[1]] + dist3[goNode[0]] + dist2[N];
        int ans = Math.min(path1, path2);
        if(ans >= INF) {
            System.out.println(-1);
            return;
        }

        System.out.println(ans);
    }

    private static int[] findShortestPath(int start) { // 해당 지점부터 각 노드까지의 거리

        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(o -> o[1]));
        pq.offer(new int[]{start, 0});

        while(!pq.isEmpty()) {

            int[] cur = pq.poll();
            int curNode = cur[0];
            int curWeight = cur[1];

            if(curWeight > dist[curNode]) continue;

            for(int[] next : graph.get((curNode))) {
                int nextNode = next[0];
                int nextWeight = next[1];

                if(dist[nextNode] > curWeight + nextWeight) {
                    dist[nextNode] = curWeight + nextWeight;
                    pq.offer(new int[]{nextNode, dist[nextNode]});
                }
            }
        }

        return dist;
    }
}