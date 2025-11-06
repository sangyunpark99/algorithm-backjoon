package org.hello.문제풀이.다익스트라.파티;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int M;
    private static int X;

    private static int[] distance;
    private static List<List<int[]>> graph = new ArrayList<>();
    private static int[] goXdistance;
    private static int[] goStartDistance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        distance = new int[N + 1];
        goXdistance = new int[N + 1]; // 시작점 -> X
        goStartDistance = new int[N + 1]; // X -> 시작점

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(a).add(new int[]{b, weight});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        for(int i = 1; i <= N; i++) { // 1,000 x 11,000 -> 11,000,000

            // 초기화
            Arrays.fill(distance, 100_000_000); // 1,000
            pq.clear();
            pq.offer(new int[]{i, 0});

            while(!pq.isEmpty()) { // E + VlogV -> 10,000
                int[] cur = pq.poll();
                int a = cur[0]; // 현재 이동 지점
                int weight = cur[1]; // 지금까지 든 비용

                for(int j = 0; j < graph.get(a).size(); j++) {
                    int next = graph.get(a).get(j)[0]; // 다음 지점
                    int nextWeight = graph.get(a).get(j)[1]; // 다음으로 가는데 드는 비용
                    if(distance[next] > weight + nextWeight) { // 이동 경로가 더 작은 경우
                        distance[next] = weight + nextWeight; // 최단 거리 갱신
                        pq.offer(new int[]{next, distance[next]});
                    }
                }
            }

            if(i == X) {
                goStartDistance = distance.clone();
            }else {
                goXdistance[i] = distance[X];
            }
        }

        // 가장 오래 걸리는 학생
        int answer = Integer.MIN_VALUE;
        for(int i = 1; i <= N; i++) {
            answer = Math.max(answer, goStartDistance[i] + goXdistance[i]);
        }

        System.out.println(answer);
    }
}