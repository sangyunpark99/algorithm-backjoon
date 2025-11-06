package org.hello.문제풀이.구현.스도쿠;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[][] map;
    private static final int N = 9;
    private static List<int[]> zero = new ArrayList<>();
    private static boolean isAnswer = false;
    private static List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9);

    // 가로줄, 세로줄, 3 x 3
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 0) zero.add(new int[]{i, j}); // 칸이 0인 지점
            }
        }

        dfs(0);
    }

    // 백트래킹
    private static void dfs(int idx) {

        if(isAnswer) return; // 막아도 map[i][j]를 0으로 만들 수 있다.

        if(idx == zero.size()) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            isAnswer = true;
            return;
        }

        int[] nextZero = zero.get(idx);
        int y = nextZero[0];
        int x = nextZero[1];
        List<Integer> useNumber = findCanNumber(y, x);

        for(int j = 0; j < useNumber.size(); j++) {
            map[y][x] = useNumber.get(j); // 수를 선택하고
            dfs(idx + 1);
            map[y][x] = 0; // 원상 복구
        }
    }

    private static List<Integer> findCanNumber(int y, int x) {
        // 가로 줄
        List<Integer> width = findWithNumbers(y, x);
        // 세로 줄
        List<Integer> high = findHighNumbers(y, x);
        // 3 x 3
        List<Integer> threeAndThree = findWithThreeAndThree(y, x);

        // 되는 숫자의 목록을 찾아라?
        width.retainAll(high); // retainAll : 교집합을 구할 때 사용
        width.retainAll(threeAndThree);

        return new ArrayList<>(width);
    }

    private static List<Integer> findWithNumbers(int y, int x) {

        Set<Integer> set = new HashSet<>(numbers);

        for(int j = 0; j < N; j++) {
            if(set.contains(map[y][j])) {
                set.remove(map[y][j]);
            }
        }

        return new ArrayList<>(set);
    }

    private static List<Integer> findHighNumbers(int y, int x) {

        Set<Integer> set = new HashSet<>(numbers);

        for(int i = 0; i < N; i++) {
            if(set.contains(map[i][x])) {
                set.remove(map[i][x]);
            }
        }

        return new ArrayList<>(set);
    }

    private static List<Integer> findWithThreeAndThree(int y, int x) {

        Set<Integer> set = new HashSet<>(numbers);

        int startY = y / 3;
        int startX = x / 3;

        for(int i = startY * 3; i < startY * 3 + 3; i++) {
            for(int j = startX * 3; j < startX * 3 + 3; j++) {
                if(set.contains(map[i][j])) {
                    set.remove(map[i][j]);
                }
            }
        }

        return new  ArrayList<>(set);
    }
}
