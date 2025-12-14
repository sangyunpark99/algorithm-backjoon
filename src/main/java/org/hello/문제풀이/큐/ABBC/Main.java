package org.hello.문제풀이.큐.ABBC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    private static String S;
    private static final Queue<Integer> aQueue = new ArrayDeque<>();
    private static final Queue<Integer> bQueue = new ArrayDeque<>();
    private static final Queue<Integer> cQueue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();

        int idx = 0;
        for(char value : S.toCharArray()) { // O(n)
            if(value == 'A') {
                aQueue.offer(idx++);
            }else if(value == 'B') { // 앞에서 부터
                bQueue.offer(idx++);
            }else {
                cQueue.offer(idx++);
            }
        }

        int answer = 0;

        while(!cQueue.isEmpty() && !bQueue.isEmpty()) { // B,C 먼저 짝 맞추기
            if(bQueue.peek() < cQueue.peek()) { // 인덱스가 앞에 위치한 것부터
                bQueue.poll();
                cQueue.poll();
                answer++;
            }else {
                cQueue.poll();
            }
        }

        while(!bQueue.isEmpty() && !aQueue.isEmpty()) { // A,B 짝 맞추기
            if(aQueue.peek() < bQueue.peek()) {
                bQueue.poll();
                aQueue.poll();
                answer++;
            } else {
                bQueue.poll();
            }
        }

        System.out.println(answer);
    }
}