package org.hello.문제풀이.유니온파인드.네트워크연결;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int T;
    private static int[] parent;
    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            dist = new int[N + 1]; // 루트 노드에서 현재 노드까지의 거리
            parent = new int[N + 1];

            for(int i = 0; i <= N; i++) {
                parent[i] = i;
            }

            while(true) {
                String[] arr = br.readLine().split(" ");
                if (arr[0].equals("O")) break;

                if (arr[0].equals("I")) {
                    int a = Integer.parseInt(arr[1]);
                    int b = Integer.parseInt(arr[2]);
                    union(a, b);
                } else if (arr[0].equals("E")) {
                    int value = Integer.parseInt(arr[1]);
                    findParent(value);
                    System.out.println(dist[value]);
                }
            }
        }
    }

    private static int findParent(int value) {
        if(parent[value] == value) {
            return value;
        }

        int p = parent[value];
        int root = findParent(p);

        dist[value] += dist[p]; // 루트에서 현재 노드 까지의 거리
        parent[value] = root;

        return root;
    }

    private static void union(int a, int b) {
        int parentA = findParent(a);

        parent[parentA] = b;
        dist[parentA] = Math.abs(b - parentA) % 1000;
    }
}