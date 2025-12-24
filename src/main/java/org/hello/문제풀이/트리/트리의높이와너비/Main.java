package org.hello.문제풀이.트리.트리의높이와너비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static HashMap<Integer, Node> nodes = new HashMap<>();
    private static List<int[]> subTreeSize = new ArrayList<>();
    private static List<int[]> position = new ArrayList<>();
    private static boolean[] isChild;

    public static class Node {
        int number;
        Node left;
        Node right;

        public Node(int number) {
            this.number = number;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for(int i = 1; i <= N; i++) { // 10,000
            nodes.put(i, new Node(i));
        }

        nodes.put(-1, new Node(-1));

        for(int i = 0; i <= N; i++) { // 10,000
            subTreeSize.add(new int[2]); // 왼쪽 서브 트리 크기, 오른쪽 서브 트리 크기
            position.add(new int[3]);
        }

        isChild = new boolean[N + 1];

        for(int i = 0; i < N; i++) { // 10,000
            st = new StringTokenizer(br.readLine());

            int number = Integer.parseInt(st.nextToken());
            int leftNumber = Integer.parseInt(st.nextToken());
            int rightNumber = Integer.parseInt(st.nextToken());

            Node node = nodes.get(number);
            node.left = nodes.get(leftNumber);
            node.right = nodes.get(rightNumber);

            if(leftNumber != -1) isChild[leftNumber] = true;
            if(rightNumber != -1) isChild[rightNumber] = true;
        }

        int rootNumber = -1;

        for(int i = 1; i <= N; i++) { // 루트 노드 판별
            if(!isChild[i]) {
                rootNumber = i;
                break;
            }
        }

        Node root = nodes.get(rootNumber);

        dfs(root); // O(10,000 + 9,999)
        findPosition(1, N, root, 1);

        // 계층별 최솟값, 최댓값 두기
        int[] minX = new int[N + 1];
        int[] maxX = new int[N + 1];
        Arrays.fill(minX, Integer.MAX_VALUE);

        int maxDepth = 0;

        for(int i = 1; i <= N; i++) {
            int[] pos = position.get(i);
            int level = pos[1];
            int x = pos[2];
            if(level == 0) continue;

            minX[level] = Math.min(minX[level],x);
            maxX[level] = Math.max(maxX[level],x);
            maxDepth = Math.max(maxDepth, level);
        }

        int bestLevel = 1;
        int bestWidth = 1;

        for(int level = 1; level <= maxDepth; level++) {
            if(minX[level] == Integer.MAX_VALUE) continue;
            int width = maxX[level] - minX[level] + 1;
            if(width > bestWidth) {
                bestWidth = width;
                bestLevel = level;
            }
        }

        System.out.println(bestLevel + " " + bestWidth);
    }

    private static void findPosition(int start, int end, Node cur, int depth) {

        if(cur == null || cur.number == -1) return;
        int rightSubTreeSize = subTreeSize.get(cur.number)[1];

        int[] curPosition = position.get(cur.number);
        curPosition[0] = cur.number;
        curPosition[1] = depth;
        curPosition[2] = end - rightSubTreeSize;

        findPosition(start, curPosition[2] - 1, cur.left, depth + 1);
        findPosition(curPosition[2] + 1, end, cur.right, depth + 1);
    }

    private static int dfs(Node cur) { // 왼쪽, 오른쪽 서브 트리 갯수

        if(cur == null || cur.number == -1) return 0;

        int leftSize = dfs(cur.left);
        int rightSize = dfs(cur.right);

        subTreeSize.get(cur.number)[0] = leftSize;
        subTreeSize.get(cur.number)[1] = rightSize;

        return leftSize + rightSize + 1;
    }
}