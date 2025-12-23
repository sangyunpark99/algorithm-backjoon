package org.hello.문제풀이.트리DP.트리의독립집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int[] weights;
    private static List<List<Integer>> graph;
    private static int[][] dp;
    private static boolean[] pick;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        weights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        graph = new ArrayList<>(N + 1);

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;
        String line;
        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) break;
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        dp = new int[N + 1][2];
        pick = new boolean[N + 1];

        boolean[] visited = new boolean[N + 1];
        visited[1] = true;
        dfs(1, visited);

        int answer = Math.max(dp[1][1], dp[1][0]);

        track(1,0, false);

        List<Integer> value = new ArrayList<>();
        for(int i = 1; i < pick.length; i++) {
            if(pick[i]) value.add(i);
        }

        Collections.sort(value);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < value.size(); i++) {
            sb.append(value.get(i) + " ");
        }

        System.out.println(answer);
        System.out.println(sb);
    }

    private static void track(int cur, int parent, boolean parentTaken) {
        boolean take;

        if(parentTaken) {
            take = false;
        }else {
            take = dp[cur][1] >= dp[cur][0]; // 더 큰 값을 선택해야 하므로
        }

        pick[cur] = take;

        for(int next: graph.get(cur)) {
            if(next == parent) continue;
            track(next, cur, take);
        }
    }

    private static void dfs(int cur, boolean[] visited) {

        dp[cur][0] = 0;
        dp[cur][1] = weights[cur - 1];

        for(int next : graph.get(cur)) {
            if(!visited[next]) {
                visited[next] = true;
                dfs(next, visited);
                dp[cur][1] += dp[next][0];
                dp[cur][0] += Math.max(dp[next][0], dp[next][1]);
            }
        }
    }
}