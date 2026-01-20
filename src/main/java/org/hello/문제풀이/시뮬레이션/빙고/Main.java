package org.hello.문제풀이.시뮬레이션.빙고;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    private static int[][] board = new int[5][5];
    private static int cnt = 0;
    private static HashMap<Integer, String> idxs = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++) {
                int number = Integer.parseInt(st.nextToken());
                board[i][j] = number;
                idxs.put(number, i + "," + j);
            }
        }

        int cnt = 0;

        for(int i = 0; i < 5; i++) {
            int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < numbers.length; j++) {
                cnt++;
                int number = numbers[j];
                String[] idx = idxs.get(number).split(",");
                int col = Integer.parseInt(idx[0]);
                int row = Integer.parseInt(idx[1]);
                board[col][row] = 0;

                if(check()) {
                    System.out.println(cnt);
                    return;
                }
            }
        }

        System.out.println(cnt);
    }

    public static boolean check() {

        int bingo = 0;

        for(int i = 0; i < 5; i++) {
            int check = 0;
            for(int j = 0; j < 5; j++) {
                if(board[i][j] == 0) {
                    check++;
                }
            }

            if(check == 5) {
                bingo++;
            }
        }

        for(int i = 0; i < 5; i++) {
            int check = 0;
            for(int j = 0; j < 5; j++) {
                if(board[j][i] == 0) {
                    check++;
                }
            }

            if(check == 5) {
                bingo++;
            }
        }

        int check = 0;
        for(int i = 0; i < 5; i++) {
            if(board[i][i] == 0) {
                check++;
            }
        }

        if(check == 5) bingo++;

        check = 0;
        for(int i = 0; i < 5; i++) {
            if(board[i][4-i] == 0) {
                check++;
            }
        }

        if(check == 5) bingo++;

        if(bingo >= 3) {
            return true;
        }

        return false;
    }
}