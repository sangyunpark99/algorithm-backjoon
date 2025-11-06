package org.hello.문제풀이.BFS.외판원순회2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    // 가장 적은 비용이 드는 여행 계획
    // 한 외판원이 어느 한 도시에서 출발해 N개의 도시를 모두 거쳐 다시 원래의 도시로 돌아오는 순회 여행 경로
    private static int N;
    private static int[][] map;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        bfs();

        System.out.println(answer);
    }

    public static class State {
        int number;
        int weight; // 누적
        boolean[] visited;

        public State(int number , int weight, boolean[] visited) {
            this.number = number;
            this.weight = weight;
            this.visited = visited;
        }
    }

    private static void bfs() {
        Queue<State> queue = new LinkedList<>();

        boolean[] visited = new boolean[N];
        visited[0] = true;
        queue.offer(new State(0,0, visited));

        while(!queue.isEmpty()) {
            State cur = queue.poll();
            int curNumber = cur.number;
            int curWeight = cur.weight;
            boolean[] curVisited = cur.visited;

            boolean allVisited = true;

            for(int i = 0; i < N; i++) {
                if(!curVisited[i]) {
                    allVisited = false;
                    break;
                }
            }

            if(curWeight >= answer) continue;

            if(allVisited && map[curNumber][0] > 0) {
                answer = Math.min(answer, curWeight + map[curNumber][0]);
            }

            for(int i = 0; i < N; i++) {
                if(map[curNumber][i] > 0) { // 연결되어 있는 경우
                    if(!curVisited[i]) { // 방문하지 않는 경우
                        boolean[] cloneVisited = curVisited.clone(); // visited 복사
                        cloneVisited[i] = true; // 방문 처리
                        queue.offer(new State(i, curWeight + map[curNumber][i], cloneVisited)); // 새로운 상태값 queue에 추가
                    }
                }
            }
        }
    }
}