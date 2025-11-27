package org.hello.문제풀이.두포인터.로봇프로젝트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;

        String input;
        while((input = br.readLine())!= null) {
            long x = Long.parseLong(input);
            long nmX = convertNM(x);
            int n = Integer.parseInt(br.readLine());

            int[] legos = new int[n];

            for(int i = 0; i < n; i++) {
                legos[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(legos);
            sb = new StringBuilder();
            find(legos, sb, nmX);
            System.out.println(sb);
        }
    }

    private static long convertNM(long cm) {
        return cm * 10_000_000;
    }

    private static void find(int[] legos, StringBuilder sb, long target) {
        int l = 0;
        int r = legos.length - 1;

        long gap = -1;
        long ansLvalue = 0;
        long ansRvalue = 0;

        while(l < r) { // O(logN)
            long lvalue = legos[l];
            long rvalue = legos[r];
            if(lvalue + rvalue == target && gap < Math.abs(lvalue - rvalue)) {
                ansLvalue = lvalue;
                ansRvalue = rvalue;
                gap = Math.abs(lvalue - rvalue);
                r--; // 다른 경우를 찾기 위해
            }else if(lvalue + rvalue < target) {
                l++;
            }else {
                r--;
            }
        }

        if(gap != -1) {
            sb.append("yes ").append(ansLvalue).append(" ").append(ansRvalue).append(" ");
        }else {
            sb.append("danger");
        }
    }
}