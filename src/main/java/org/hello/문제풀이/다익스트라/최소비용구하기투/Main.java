package org.hello.문제풀이.다익스트라.최소비용구하기투;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int M;
    private static List<List<int[]>> graph = new ArrayList<>();
    private static int[] dist;
    private static List<Integer> path = new ArrayList<>();
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[]{b, w});
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        findShortestPath(start);

        int cost = dist[end];
        System.out.println(cost);
        findPath(end);
        path.add(start);
        // 경로 찾기
        System.out.println(path.size());
        for(int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
    }

    private static void findPath(int value) {
        if(value == parent[value]) {
            return;
        }

        path.add(value);
        findPath (parent[value]);
    }

    private static void findShortestPath(int start) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });

        dist[start] = 0; // 시작 지점은 최솟값으로
        queue.offer(new int[]{start, 0});

        // 경로 추적

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curNode = cur[0];
            int curWeight = cur[1];

            if(dist[curNode] < curWeight) continue;

            for(int[] next : graph.get(curNode)) {
                if (dist[next[0]] > curWeight + next[1]) { // 최단 경로 갱신
                    dist[next[0]] = curWeight + next[1];
                    parent[next[0]] = curNode;
                    queue.offer(new int[]{next[0], dist[next[0]]});
                }
            }
        }
    }
}

