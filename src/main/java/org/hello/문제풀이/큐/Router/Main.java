package org.hello.문제풀이.큐.Router;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    private static int N;
    private static Queue<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        while(true) {
            int value = Integer.parseInt(br.readLine());
            if(value == -1) break;
            if(value != 0 && queue.size() < N) {
                queue.offer(value);
                continue;
            }

            if(value == 0) {
                queue.poll();
            }
        }

        if(queue.isEmpty()) {
            System.out.println("empty");
            return;
        }

        StringBuilder sb = new StringBuilder();

        while(!queue.isEmpty()) {
            sb.append(queue.poll()).append(" ");
        }

        System.out.println(sb);
    }
}
