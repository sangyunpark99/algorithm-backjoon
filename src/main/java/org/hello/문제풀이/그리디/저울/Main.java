package org.hello.문제풀이.그리디.저울;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);

        int sum = 0;

        for(int w : arr) {
            if(w > sum + 1) {
                System.out.println(sum + 1);
                return;
            }
            sum += w;
        }

        // 마지막
        System.out.println(sum + 1);
    }
}
