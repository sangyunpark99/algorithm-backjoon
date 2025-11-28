package org.hello.문제풀이.다익스트라.서강그라운드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int r;
    private static int[] items;
    private static int answer = 0;
    private static List<List<int[]>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        // 0번부터 지역 번호 시작
        items = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); // 0-based

        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken());
            graph.get(a).add(new int[]{b, l});
            graph.get(b).add(new int[]{a, l});
        }

        for(int start = 0; start < n; start++) { // 시작점을 기준으로
            answer = Math.max(answer, getTotalItemCnt(start));
        }

        System.out.println(answer);
    }

    private static int getTotalItemCnt(int start) {
        int[] dist = new int[n];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        pq.offer(new int[]{start, 0});

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while(!pq.isEmpty()) {
            int[] arr = pq.poll();
            int cur = arr[0];
            int distance = arr[1];
            if(dist[cur] < distance) continue;

            for(int[] nextArr : graph.get(cur)) {
                int next = nextArr[0];
                int weight = nextArr[1];
                if(dist[next] > dist[cur] + weight) {
                    dist[next] = dist[cur] + weight;
                    pq.offer(new int[]{next, dist[next]});
                }
            }
        }

        int totalCnt = 0;

        for(int i = 0; i < dist.length; i++) {
            if(dist[i] <= m) {
                totalCnt += items[i];
            }
        }

        return totalCnt;
    }
}
