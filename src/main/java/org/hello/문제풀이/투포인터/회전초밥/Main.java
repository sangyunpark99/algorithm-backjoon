package org.hello.문제풀이.투포인터.회전초밥;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int d;
    private static int k;
    private static int c;
    private static int[] arr;
    private static HashMap<Integer, Integer> cnts = new HashMap<>();
    private static HashSet<Integer> kind = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int[2 * N];

        for(int i = 0; i < N; i++) {
            int value = Integer.parseInt(br.readLine());
            arr[i] = value;
            arr[N + i] = value;
        }

        int left = 0;
        int right = k - 1;
        for(int i = 0; i <= right; i++) {
            cnts.put(arr[i], cnts.getOrDefault(arr[i], 0) + 1);
            kind.add(arr[i]);
        }

        kind.add(c);
        cnts.put(c, cnts.getOrDefault(c, 0) + 1);

        int answer = kind.size();

        while(right < N + k) {

            int leftValue = arr[left++];
            int rightValue = arr[++right];
            int leftCnt = cnts.get(leftValue);

            if(leftCnt - 1 == 0) {
                cnts.remove(leftValue);
                kind.remove(leftValue);
            }else {
                cnts.put(leftValue, cnts.get(leftValue) - 1);
            }

            cnts.put(rightValue, cnts.getOrDefault(rightValue, 0) + 1);
            kind.add(rightValue);

            answer = Math.max(answer, kind.size());
        }

        System.out.println(answer);
    }
}
