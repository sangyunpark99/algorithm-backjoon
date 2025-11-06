package org.hello.기본기.순열조합;

import java.util.Arrays;

class Main {

    private static int[] arr = new int[]{1,2,3,4};

    public static void main(String[] args) {
        int r = 3;
        System.out.println("===== 조합 =====");
        combination(0, new int[r], 0, r);
        System.out.println();
        System.out.println("===== 중복 조합 =====");
        duplicateCombination(new int[r], 0, r);
        System.out.println();
        System.out.println("===== 순열(모든 원소) =====");
        permutationAll(0); // 원소 전부
        System.out.println();
        System.out.println("===== 순열(부분 원소) =====");
        permutationPart(0, r, new int[r], new boolean[arr.length]);
        System.out.println();
        System.out.println("===== 중복 순열(부분 원소) =====");
        duplicatedPermutation(0, new int[r], r);
    }

    private static void combination(int start, int[] res, int depth, int r) {
        if(depth == r) {
            System.out.println(Arrays.toString(res));
            return;
        }

        for(int i = start; i < arr.length; i++) {
            res[depth] = arr[i];
            combination(i + 1, res, depth + 1, r);
        }
    }

    private static void duplicateCombination(int[] res, int depth, int r) {
        if(depth == r) {
            System.out.println(Arrays.toString(res));
            return;
        }

        for(int i = 0; i < arr.length; i++) {
            res[depth] = arr[i];
            duplicateCombination(res, depth + 1, r);
        }
    }

    private static void permutationAll(int depth) { // 전체 처리
        if(depth == arr.length) {
            System.out.println(Arrays.toString(arr));
        }

        for(int i = depth; i < arr.length; i++) {
            swap(arr, i, depth);
            permutationAll(depth + 1);
            swap(arr, i, depth);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void permutationPart(int depth, int r, int[] res, boolean[] visited) {
        if(depth == r) {
            System.out.println(Arrays.toString(res));
            return;
        }

        for(int i = 0; i < arr.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                res[depth] = arr[i];
                permutationPart(depth + 1, r, res, visited);
                visited[i] = false;
            }
        }
    }

    private static void duplicatedPermutation(int depth, int[] res, int r) {
        if(depth == r) {
            System.out.println(Arrays.toString(res));
            return;
        }

        for(int i = 0; i < arr.length; i++) {
            res[depth] = arr[i];
            duplicatedPermutation(depth + 1, res, r);
        }
    }
}