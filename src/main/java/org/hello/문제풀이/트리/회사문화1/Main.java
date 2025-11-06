package org.hello.문제풀이.트리.회사문화1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int[] praise;
    private static List<List<Integer>> tree = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        // 직원 번호는 1번 부터
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i = 2; i <= N; i++) { // 2번 직원 번호 부터
            int parent = arr[i - 1]; // 0-based idx
            if(parent != -1) { // 부모가 -1이 아닌경우 - 루트가 아닌 경우
                tree.get(parent).add(i); // 트리 자식 노드 추가
            }
        }

        praise = new int[N + 1];

        for(int i = 0; i < M; i++) { // O(N) = 100,000
            st =  new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            praise[number] += w; // 한명의 상사가 여러번 칭찬받을 수 있다.
        }

        dfs(1); // O(N) = 100,000

        // 최종 시간 복잡도 : 200,000

        for(int i = 1; i <= N; i++) {
            sb.append(praise[i]).append(" ");
        }

        System.out.println(sb);
    }

    private static void dfs(int cur) {
        for(int next : tree.get(cur)) {
            praise[next] += praise[cur];
            dfs(next);
        }
    }
}
