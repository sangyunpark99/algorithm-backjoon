package org.hello.문제풀이.비트마스킹.집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int bitmask = 0;
    private static int M;
    private static StringBuilder sb = new StringBuilder();

    // 각 숫자를 비트 인덱스로 생각한다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++) {
            String[] value =  br.readLine().split(" ");
            String order = value[0];

            if("add".equals(order)) { // 추가
                int number = Integer.parseInt(value[1]);
                add(number);
            }else if("remove".equals(order)) {
                int number = Integer.parseInt(value[1]);
                remove(number);
            }else if("check".equals(order)) {
                int number = Integer.parseInt(value[1]);
                check(number);
            }else if("toggle".equals(order)) {
                int number = Integer.parseInt(value[1]);
                toggle(number);
            }else if("all".equals(order)) {
                all();
            }else if("empty".equals(order)) {
                empty();
            }
        }

        System.out.println(sb.toString());
    }

    private static void empty() {
        bitmask = 0;
    }

    private static void all() {
        bitmask = (1 << 21) - 1;
    }

    private static void add(int number) {
        bitmask |= (1 << number);
    }

    private static void remove(int number) {
        bitmask &= ~(1 << number);
    }

    private static void check(int number) {
        int value = (1 << number);
        if((bitmask & value) == value) {
            sb.append(1).append("\n");
        }else {
            sb.append(0).append("\n");
        }
    }

    private static void toggle(int number) {
        bitmask ^= (1 << number);
    }
}
