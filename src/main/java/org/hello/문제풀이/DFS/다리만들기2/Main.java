package org.hello.문제풀이.DFS.다리만들기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 1. 지역 나누기
    // 2. 최단 경로 다리 만들기(한 방향) + 갯수 2개 이상
    // 3. 각 다리 저장 (a,b,크기)
    // 4. 각 다리를 돌면서 (제일 짧은 길이 부터) 더하고, 집합 묶기 -> 짧은 다리 먼저 진행한다.
    // 5. 최소 다리 길이 출력

    private static int N;
    private static int M;
    private static int[][] map;

    private static int[] dy = {-1,0,1,0};
    private static int[] dx = {0,1,0,-1};

    private static int[] parent;
    private static int[] rank;
    private static List<int[]> bridges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        parent = new int[N + 1];

        for (int i = 2; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 1. 지역 나누기
        boolean[][] visited = new boolean[N][M];
        int area = 2;
        for (int i = 0; i < N; i++) { // O(100)
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    visited[i][j] = true;
                    checkArea(area++, i, j, visited);
                }
            }
        }

        // 2. 최단경로 다리 만들기
        for (int i = 2; i < area; i++) {
            makeBridge(i);
        }

        parent = new int[area];
        rank = new int[area];

        for(int i = 2; i < area; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        // 3. 다리를 돌면서 집합 만들기 하나로 묶을 수 있는가?
        Collections.sort(bridges,(a,b) -> a[2] - b[2]); // 제일 다리가 짧은 것부터
        int answer = 0;

        for(int i = 0; i < bridges.size(); i++) {
            int[] bridge = bridges.get(i);
            int a = bridge[0];
            int b = bridge[1];
            int weight = bridge[2];
            if(find(a) != find(b)) { // 연결 안됨?
                answer += weight;
                union(a,b);
            }
        }

        // 정말로 다 연결이 됨?
        int root = find(2);
        boolean check = true;
        for(int i = 3; i < area; i++) {
            if(find(i) != root) {
                check = false;
                break;
            }
        }

        if(check) {
            System.out.println(answer);
        }else {
            System.out.println(-1);
        }
    }

    private static int find(int a) {
        if(parent[a] == a) {
            return a;
        }

        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if(parentA == parentB) return;

        if(rank[parentA] < rank[parentB]) {
            parent[parentA] = parentB;
        }else if(rank[parentB] < rank[parentA]){
            parent[parentB] = parentA;
        }else {
            rank[parentB]++;
            parent[parentA] = parentB;
        }
    }

    private static void makeBridge(int a) { // a로 부터 모든 섬의 경로
        Queue<int[]> queue = new ArrayDeque<>();

        boolean[][] visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == a) {
                    visited[i][j] = true;
                    for(int d = 0; d < 4; d++) { // 각자의 위치, 지역 번호, 이동 횟수, 방향
                        queue.offer(new int[]{i, j, 0, d});
                    }
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            int cnt = cur[2];
            int d = cur[3];

            int ny = y + dy[d];
            int nx = x + dx[d];
            if(ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx]) continue;
            if(map[ny][nx] != 0 && map[ny][nx] != a && cnt >= 2) { // 다른 지역을 만난다
                visited[ny][nx] = true;
                bridges.add(new int[]{a, map[ny][nx], cnt});
            }else if(map[ny][nx] == 0){ // 다른지역을 만나지 않은 경우
                visited[ny][nx] = true;
                queue.offer(new int[]{ny,nx,cnt + 1, d});
            }
        }
    }

    private static void checkArea(int area, int y, int x, boolean[][] visited) {

        visited[y][x] = true;
        map[y][x] = area;

        for(int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
            if(map[ny][nx] == 0) continue;

            if(!visited[ny][nx]) {
                checkArea(area, ny, nx, visited);
            }
        }
    }
}
