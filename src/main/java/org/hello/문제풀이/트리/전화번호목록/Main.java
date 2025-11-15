package org.hello.문제풀이.트리.전화번호목록;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        while(t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] arr = new String[n];
            for(int i = 0; i < n; i++) {
                arr[i] = br.readLine();
            }

            Arrays.sort(arr);

            String answer = "YES";

            for(int i = 1; i < n; i++) {
                if(arr[i].startsWith(arr[i-1])) {
                    answer = "NO";
                    break;
                }
            }

            System.out.println(answer);
        }
    }
}
