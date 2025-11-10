package org.hello.문제풀이.DFS.숫자고르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static int N;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static boolean[] isCycle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 1; i <= N; i++) {
            int value = Integer.parseInt(br.readLine());
            graph.get(i).add(value);
        }

        int answer = 0;

        isCycle = new boolean[N + 1];

        for(int i = 1; i <= N; i++) { // 100
            boolean[] visited = new boolean[N + 1];
            visited[i] = true;
            if(dfs(i, visited, i)) {
                isCycle[i] = true; // 정렬되어 있다
                answer++;
            }
        }

        System.out.println(answer);
        for(int i = 1; i < isCycle.length; i++) {
            if(isCycle[i]){
                System.out.println(i);
            }
        }
    }

    private static boolean dfs(int start, boolean[] visited, int first) {

        boolean check = false;

        for(int next: graph.get(start)) {
            if(next == first) {
                return true;
            }
            if(!visited[next]) {
                visited[next] = true;
                check = dfs(next, visited, first);
            }
        }

        return check;
    }
}