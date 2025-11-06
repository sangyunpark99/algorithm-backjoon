package org.hello.기본기.트리순회;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<List<Integer>> tree = new ArrayList<>();

    public static void main(String[] args) {

        for(int i = 0; i <= 5; i++) {
            tree.add(new ArrayList<>());
        }

        tree.get(1).add(2);
        tree.get(1).add(3);
        tree.get(2).add(4);
        tree.get(2).add(5);

        postOrder(1);
        System.out.println();
        preOrder(1);
        System.out.println();
        inOrder(1);
    }

    //이진 트리 후위 순회 - postOrder
    private static void postOrder(int cur) {
        if(tree.get(cur).isEmpty()) {
            System.out.print(cur);
            return;
        }

        if(tree.get(cur).size() >= 1) {
            int left = tree.get(cur).get(0);
            postOrder(left);
        }

        if(tree.get(cur).size() >= 2) {
            int right = tree.get(cur).get(1);
            postOrder(right);
        }

        System.out.print(cur);
    }

    //이진 트리 전위 순회 - preOrder
    private static void preOrder(int cur) {

        if(tree.get(cur).isEmpty()) {
            System.out.print(cur);
            return;
        }

        System.out.print(cur);

        if(tree.get(cur).size() >= 1) {
            int left = tree.get(cur).get(0);
            preOrder(left);
        }

        if(tree.get(cur).size() >= 2) {
            int right = tree.get(cur).get(1);
            preOrder(right);
        }
    }

    // 이진 트리 중위 순회
    private static void inOrder(int cur) {
        if(tree.get(cur).isEmpty()) {
            System.out.print(cur);
            return;
        }

        if(tree.get(cur).size() >= 1) {
            int left = tree.get(cur).get(0);
            inOrder(left);
        }

        System.out.print(cur);

        if(tree.get(cur).size() >= 2) {
            int right = tree.get(cur).get(1);
            inOrder(right);
        }
    }
}
