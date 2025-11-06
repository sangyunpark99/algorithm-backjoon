package org.hello.문제풀이.DFS.키순서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 단방향
    private static int N;
    private static int M;
    private static HashMap<Integer, Node> map = new HashMap<>();
    private static int answer = 0;

    public static class Node {
        int number;
        List<Node> bfNode = new  ArrayList<>();
        List<Node> afNode = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++) {
            map.put(i, new Node(i));
        }

        for(int i = 0; i < M; i++) {
            st =  new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Node node1 = map.get(a);
            node1.afNode.add(map.get(b));
            Node node2 = map.get(b);
            node2.bfNode.add(map.get(a));
        }

        for(int i = 1; i <= N; i++) { // O(500)
            Node cur = map.get(i);
            int bfCnt = bfDfs(cur, new boolean[N + 1]); // O(500)
            int afCnt = afDfs(cur, new boolean[N + 1]); // O(500)

            if(bfCnt + afCnt == N - 1) {
                answer++;
            }
        }

        // 500 x 1000 -> 500,000

        System.out.println(answer);
    }

    private static int afDfs(Node cur, boolean[] visited) {
        int total = 0;

        for(Node next : cur.afNode) {
            if(!visited[next.number]) {
                visited[next.number] = true;
                total += 1 + afDfs(next, visited);
            }
        }

        return total;
    }

    private static int bfDfs(Node cur, boolean[] visited) { // O(500)
        int total = 0;

        for(Node next: cur.bfNode) {
            if(!visited[next.number]) {
                visited[next.number] = true;
                total += 1 + bfDfs(next, visited);
            }
        }

        return total;
    }
}