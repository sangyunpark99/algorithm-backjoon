package org.hello.문제풀이.최소스패닝트리.별자리만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static double[][] spot;
    private static List<double[]> edges = new ArrayList<>();
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        spot = new double[n][2];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            spot[i][0] = y;
            spot[i][1] = x;
        }

        for(int i = 0; i < n; i++) { // O(n^2)
            for(int j = i + 1; j < n; j++) {
                double distance = getDistance(i, j);
                edges.add(new double[]{i, j, distance});
            }
        }

        parent = new int[n];

        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        Collections.sort(edges, Comparator.comparingDouble(o -> o[2]));

        int totalEdge = n - 1;
        double answer = 0;

        for(int i = 0; i < edges.size(); i++) {
            double[] edge = edges.get(i);
            int a = (int) edge[0];
            int b = (int) edge[1];

            int parentA = find(a);
            int parentB = find(b);
            if(totalEdge == 0) break;
            if(parentA != parentB) {
                union(parentA,parentB);
                totalEdge--;
                answer += Math.sqrt(edge[2]);
            }
        }

        System.out.printf("%.2f",answer);
    }

    private static int find(int cur) {
        if(parent[cur] == cur) {
            return cur;
        }

        return parent[cur] = find(parent[cur]);
    }

    private static void union(int parentA, int parentB) {

        if(parentA == parentB) return;

        if(parentA < parentB) {
            parent[parentB] = parentA;
        }else {
            parent[parentA] = parentB;
        }
    }

    private static double getDistance(int a, int b) {
        double gapY = spot[a][0] - spot[b][0];
        double gapX = spot[a][1] - spot[b][1];
        return gapY * gapY + gapX * gapX;
    }
}
