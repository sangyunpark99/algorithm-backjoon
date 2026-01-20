package org.hello.문제풀이.스택.창고다각형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    private static int N;
    private static ArrayList<int[]> values = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            int[] value = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            values.add(value);
        }

        Collections.sort(values,(a,b) -> {
            return a[0] - b[0];
        });

        int maxHeightIdx = 0;
        int maxHeight = 0;

        for(int i = 0; i < N; i++) {
            int[] value = values.get(i);
            if(maxHeight < value[1]) {
                maxHeightIdx = i;
                maxHeight = value[1];
            }
        }

        int leftMaxHeightIdx = 0;
        for(int i = 0; i < N; i++) {
            int[] value = values.get(i);
            if(value[1] == maxHeight) {
                leftMaxHeightIdx = value[0];
                break;
            }
        }

        int rightMaxHeightIdx = 0;
        for(int i = N - 1; i >= 0; i--) {
            int[] value = values.get(i);
            if(value[1] == maxHeight) {
                rightMaxHeightIdx = value[0];
                break;
            }
        }


        int[] start = values.get(0);
        int height = start[1];
        int startIdx = start[0];
        int answer = 0;

        for(int i = 1; i <= maxHeightIdx; i++) {
            int[] value = values.get(i);

            if(height < value[1]) {
                answer += (value[0] - startIdx) * height;
                height = value[1];
                startIdx = value[0];
            }
        }

        start = values.get(values.size() - 1);
        height = start[1];
        startIdx = start[0];

        for(int i = values.size() - 2; i >= maxHeightIdx; i--) {
            int[] value = values.get(i);

            if(height < value[1]) {
                answer += (startIdx - value[0]) * height;
                height = value[1];
                startIdx = value[0];
            }
        }

        answer += maxHeight * (rightMaxHeightIdx - leftMaxHeightIdx + 1);

        System.out.println(answer);
    }
}