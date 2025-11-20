package org.hello.문제풀이.백트래킹.암호만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // 최소 한개의 모음, 최소 2개의 자음
    // 완전 탐색을 진행하면서, 한개의 모음 혹은 2개의 자음을 갖는지 확인
    // 만약 남아 있는 암호가 조건 충족을 못하면 return

    private static int L;
    private static int C;
    private static String[] arr;

    // 조합
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = br.readLine().split(" ");

        Arrays.sort(arr);
        // 배열 탐색 하면서

        System.out.println(Arrays.toString(arr));

        dfs(0, 0, new char[L]);
    }

    private static void dfs(int depth, int start, char[] combi) {
        if(depth == L) { // L개인 경우
            if(isValid(combi)) {
                System.out.println(String.valueOf(combi));
            }
            return;
        }

        for(int i = start; i < C; i++) {
            combi[depth] = arr[i].charAt(0);
            dfs(depth + 1, i + 1, combi);
        }
    }

    private static boolean isValid(char[] arr) {
        int aCnt = 0;
        int bCnt = 0;
        for(int i = 0; i < arr.length; i++) {
            char value = arr[i];
            if("aeiou".indexOf(value) >= 0) {
                aCnt++;
            }else {
                bCnt++;
            }
        }
        return aCnt >= 1 && bCnt >= 2;
    }
}