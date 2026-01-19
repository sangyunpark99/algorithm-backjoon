package org.hello.문제풀이.큐.앵무새;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static String L;
    private static List<Queue> queues = new ArrayList<>();
    private static HashMap<String, Integer> queueIdx = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            Queue queue = new ArrayDeque<>();
            String[] values = br.readLine().split(" ");

            for(String value : values) {
                queueIdx.put(value, i);
                queue.offer(value);
            }

            queues.add(queue);
        }

        L = br.readLine();
        String[] lValues = L.split(" ");

        for(String value: lValues) { // O(N) = 10,000
            if(queueIdx.containsKey(value)) {
                int idx = queueIdx.get(value);
                Queue<String> queue = queues.get(idx);
                if(!queue.peek().equals(value)) {
                    System.out.println("Impossible");
                    return;
                }
                queue.poll();
            }else {
                System.out.println("Impossible");
                return;
            }
        }

        for(Queue queue : queues) {
            if(!queue.isEmpty()) {
                System.out.println("Impossible");
                return;
            }
        }

        System.out.println("Possible");
    }
}
