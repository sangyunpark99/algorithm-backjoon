package org.hello.문제풀이.큐.queuestack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int M;
    private static int[] arr;
    private static int[] type;
    private static int[] data;
    private static Queue<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        type = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        M = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        StringBuilder sb = new StringBuilder();
        for(int i = data.length - 1; i >= 0; i--) {
            if(type[i] == 0) {
                queue.offer(data[i]);
            }
        }

        if(queue.isEmpty()) {
            for(int i = 0; i < arr.length; i++) {
                sb.append(arr[i] + " ");
            }
        }else {
            for(int i = 0; i < arr.length; i++) {
                int value = arr[i];
                sb.append(queue.poll() + " ");
                queue.offer(value);
            }
        }

        System.out.println(sb);
    }
}
