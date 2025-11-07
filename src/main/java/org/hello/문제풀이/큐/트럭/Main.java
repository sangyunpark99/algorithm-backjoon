package org.hello.문제풀이.큐.트럭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int W;
    private static int L;
    private static int[] trucks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        trucks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Queue<Integer> bridge = new ArrayDeque<>(W); // 다리 크기
        int bridgeWeight = 0;
        int time = 0;
        for(int i = 0; i < N; i++) {
            int truck = trucks[i];
            time++;
            if(bridge.isEmpty()) { // 다리가 비어 있는 경우
                bridge.offer(truck);
                bridgeWeight += truck;
            }else { // 다리가 비어 있지 않은 경우
                if(bridge.size() == W) { // 이미 가득찬 경우
                    bridgeWeight -= bridge.poll(); // 트럭 제거하기
                }

                if(bridge.size() < W && bridgeWeight + truck <= L) { // 현재 트럭을 다리에 실을 수 있는 경우
                    bridge.offer(truck);
                    bridgeWeight += truck;
                }else { // 그렇지 않은 경우
                    bridge.offer(0);
                    i--;
                }
            }
        }

        if(!bridge.isEmpty()) { // 다리가 비어있지 않은 경우
            time += W;
        }

        System.out.println(time);
    }
}