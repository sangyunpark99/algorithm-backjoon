package org.hello.문제풀이.우선순위큐.이중우선순위큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;
        T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int Q = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> minQueue = new PriorityQueue<>();
            PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
            HashMap<Integer, Integer> count = new HashMap<>();
            for(int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());
                String operation = st.nextToken();
                int number = Integer.parseInt(st.nextToken());
                if("I".equals(operation)) {
                    minQueue.offer(number);
                    maxQueue.offer(number);
                    count.put(number, count.getOrDefault(number,0) + 1);
                }else { // D인 경우
                    if(number == -1) {
                        clean(minQueue, count);
                        if(!minQueue.isEmpty()) {
                            int value = minQueue.poll();
                            count.put(value, count.get(value) - 1);
                        }
                    }else {
                        clean(maxQueue, count);
                        if(!maxQueue.isEmpty()) {
                            int value = maxQueue.poll(); // O(1)
                            count.put(value, count.get(value) - 1);
                        }
                    }
                }
            }

            clean(maxQueue, count);
            clean(minQueue, count);

            sb = new StringBuilder();

            if(minQueue.isEmpty() || maxQueue.isEmpty()) {
                sb.append("EMPTY").append("\n");
            }else {
                sb = new StringBuilder();
                sb.append(maxQueue.poll()).append(" ").append(minQueue.poll()).append("\n");
            }

            System.out.print(sb);
        }
    }

    private static void clean(PriorityQueue<Integer> queue, HashMap<Integer, Integer> count) { // 이미 지워졌던 부분 지우기
        while(!queue.isEmpty()) {
            int v = queue.peek();
            int c = count.getOrDefault(v, 0);
            if(c <= 0) {
                queue.poll();
            }else{
                break;
            }
        }
    }
}