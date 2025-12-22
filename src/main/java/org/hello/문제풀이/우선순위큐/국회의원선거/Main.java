package org.hello.문제풀이.우선순위큐.국회의원선거;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int value = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 1; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int person = 0;

        while(!pq.isEmpty() && pq.peek() >= value) {
            int number = pq.poll();
            value += 1;
            person++;
            pq.offer(number - 1);
        }

        System.out.println(person);
    }
}