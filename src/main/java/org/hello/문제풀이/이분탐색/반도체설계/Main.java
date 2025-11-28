package org.hello.문제풀이.이분탐색.반도체설계;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static int N;
    private static int[] ports;
    private static List<Integer> lis = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        ports = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i = 0; i < ports.length; i++) {
            int port = ports[i];
            if(lis.isEmpty()) {
                lis.add(port);
            }else {
                if(port > lis.get(lis.size() - 1)) {
                    lis.add(port);
                }else {
                    int idx = findIdx(port); // 위치를 찾고
                    lis.set(idx, port);
                }
            }
        }

        System.out.println(lis.size());
    }

    private static int findIdx(int value) {
        int left = 0;
        int right = lis.size() - 1;
        int idx = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
            int num = lis.get(mid);

            if(value <= num) { // 배열의 수보다 작거나 같아
                idx = mid; // 위치 갱신
                right = mid - 1; // 더 줄여봐
            }else {
                left = mid + 1;
            }
        }

        return idx;
    }
}
