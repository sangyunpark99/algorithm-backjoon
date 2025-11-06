package org.hello.문제풀이.BFS.뱀과사다리게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int M;
    private static int answer = Integer.MAX_VALUE;
    private static HashMap<Integer, Integer> ladder = new HashMap<>();
    private static HashMap<Integer, Integer> snake = new HashMap<>();
    private static int[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder.put(a, b);
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            snake.put(a, b);
        }

        board = new int[101];
        Arrays.fill(board, 1000);

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{1,0});
        board[1] = 0;

        while(!queue.isEmpty()) {

            int[] cur = queue.poll();
            int nextStart = cur[0]; // 다음 시작점
            int cnt = cur[1];

            if(nextStart == 100) {
                answer = Math.min(answer, cnt);
                continue;
            }

            if(board[nextStart] < cnt) {
                continue;
            }

            // 그냥 주사위로 이동하는 경우
            for(int i = 1; i <= 6; i++) {

                int next = nextStart + i; // 다음 칸
                if(next > 100) continue;

                if(ladder.containsKey(next)) {
                    next = ladder.get(next);
                }else if(snake.containsKey(next)) {
                    next = snake.get(next);
                }

                if(board[next] > cnt + 1) {
                    board[next] = cnt + 1;
                    queue.offer(new int[]{next, cnt + 1});
                }
            }
        }

        System.out.println(board[100]);
    }
}
