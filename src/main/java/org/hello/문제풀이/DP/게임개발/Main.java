package org.hello.문제풀이.DP.게임개발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int[] time;
    private static List<List<Integer>> build = new ArrayList<>(); // 해당 idx가 선행인 모든 건물들
    private static int[] buildCnt;
    private static int[] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        time = new int[N + 1];
        buildCnt = new int[N + 1];
        cost = new int[N + 1];

        for(int i = 0; i <= N; i++) {
            build.add(new ArrayList<>());
        }

        for(int i = 0; i < N; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int cost = arr[0];
            int number = i + 1;
            time[number] = cost;
            int[] bfBuild = Arrays.copyOfRange(arr, 1, arr.length - 1); // 이전에 지어야할 건물
            buildCnt[number] = bfBuild.length;
            for(int j = 0; j < bfBuild.length; j++) {
                build.get(bfBuild[j]).add(number);
            }
        }

        Queue<Integer> queue = new ArrayDeque<>(); // 지어진 건물

        for(int i = 1; i <= N; i++) {
            if(buildCnt[i] == 0) {
                cost[i] = time[i];
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int next : build.get(cur)) {
                cost[next] = Math.max(cost[next], cost[cur] + time[next]); // 매번 비용 갱신
                if(--buildCnt[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            System.out.println(cost[i]);
        }
    }
}
