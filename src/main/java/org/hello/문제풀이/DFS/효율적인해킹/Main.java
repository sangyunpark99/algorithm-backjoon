package org.hello.문제풀이.DFS.효율적인해킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int M;
    private static int N;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static List<int[]> result;
    private static int maxCnt = Integer.MIN_VALUE;
    private static int cnt = 0;
    private static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++) { // O(n)
            graph.add(new ArrayList<>());
        }

        result = new ArrayList<>();

        for(int i = 0; i < M; i++) { // O(M)
            st = new StringTokenizer(br.readLine());
            int a =  Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(b).add(a);
        }

        for(int i = 1; i <= N; i++) { // O(V + E)
            boolean[] visited = new boolean[N + 1];
            visited[i] = true;
            dfs(i, visited);
            result.add(new int[]{i, cnt});
            cnt = 0;
        }

        Collections.sort(result,(a,b) -> { // O(nlogn)
            return b[1] - a[1];
        });

        int maxCnt = result.get(0)[1];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < result.size(); i++) {
            int[] res = result.get(i);
            if(res[1] == maxCnt) {
                sb.append(res[0]).append(" ");
            }
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int cur, boolean[] visited) {
        for(int next : graph.get(cur)) { // 신뢰하는 관계
            if(visited[next]) continue;
            visited[next] = true;
            cnt++;
            dfs(next, visited);
        }
    }
}
