package com.tikeon.ndrwum.assignment1;

import java.util.Arrays;
import java.util.HashMap;

class Board {

    private int[] assignments;
    private int moveCount;
    private String Sequence;
    private final HashMap<String, int[]> switchMap = new HashMap<>();

    private final int[] switchA = {-1, -1, -1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1};
    private final int[] switchB = {1, 1, 1, -1,
            1, 1, 1, -1,
            1, -1, 1, -1,
            1, 1, 1, 1};
    private final int[] switchC = {1, 1, 1, 1,
            -1, 1, 1, 1,
            1, 1, -1, 1,
            1, 1, -1, -1};
    private final int[] switchD = {-1, 1, 1, 1,
            -1, -1, -1, -1,
            1, 1, 1, 1,
            1, 1, 1, 1};
    private final int[] switchE = {1, 1, 1, 1,
            1, 1, -1, -1,
            -1, 1, -1, 1,
            -1, 1, 1, 1};
    private final int[] switchF = {-1, 1, -1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1,
            1, 1, -1, -1};
    private final int[] switchG = {1, 1, 1, -1,
            1, 1, 1, 1,
            1, 1, 1, 1,
            1, 1, -1, -1};
    private final int[] switchH = {1, 1, 1, 1,
            -1, -1, 1, -1,
            1, 1, 1, 1,
            1, 1, -1, -1};
    private final int[] switchI = {1, -1, -1, -1,
            -1, -1, 1, 1,
            1, 1, 1, 1,
            1, 1, 1, 1};
    private final int[] switchJ = {1, 1, 1, -1,
            -1, -1, 1, 1,
            1, -1, 1, 1,
            1, -1, 1, 1};
    private final int[] switchTest = {1, -1, 1, 1, -1, 1, 1, 1, -1, 1, 1, 1, -1, -1, -1, -1};

    private final int[][] switches = {switchA, switchB, switchC, switchD, switchE, switchF, switchG,
            switchH, switchI, switchJ, switchTest};

    private final String[] switchNames = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "Test"};

    //set random board
    Board() {
        int[] sqr = new int[16];
        moveCount = 0;
        Sequence = "";
        // -1 means b;ack, 1 means white
        for (int i = 0; i < 16; i++) {
            if (Math.random() < 0.5)
                sqr[i] = -1;
            else
                sqr[i] = 1;
        }
        assignments = sqr;
    }


    void testBoard() {
        assignments = switchTest;
    }


    int[] getAssignments() {
        return assignments;
    }

    void applySwitch(int[] sw) {
        if (Arrays.equals(sw, switchTest)) {

        } else {
            moveCount++;
        }
        for (int i = 0; i < 16; i++) {
            assignments[i] *= sw[i];
        }
    }

    void trackSeq(String c) {
        if (Sequence.equals("")) {
            Sequence = c;
        } else
            Sequence += ", " + c;
    }

    // Check if puzzle solved
    boolean isSolved(int[] squares) {
        int sumofSqrs = 0;
        for (int i : squares)
            sumofSqrs += i;
        return sumofSqrs == 16 || sumofSqrs == -16;
    }

    String findSolution() {

        for (int i = 0; i < switchNames.length; i++) {
            switchMap.put(switchNames[i], switches[i]);
        }

        String sol = findSolHelper(assignments, 0);

        if (sol.equals("No solution")) {
            Sequence = "No solution";
            return "Sequence solution: " + sol + "\n" + "Solution min count: inf";
        } else {
            return "Sequence solution: " + sol + "\n" + "Solution min count: " + sol.length();
        }
    }

    //DFS pre-order search
    private String findSolHelper(int[] squares, int swNum) {
        if (isSolved(squares)) {
            return "";
        } else {
            String currentSol = "No solution";

            int[] testSquares = new int[16];
            for (int i = 0; i < 16; i++)
                testSquares[i] = squares[i] * switches[swNum][i];

            for (int sw = swNum + 1; sw < switches.length; sw++) {
                String moveSeq = findSolHelper(testSquares, sw);

                if (!moveSeq.equals("No solution")) {
                    String tempSol = switchNames[swNum].concat(moveSeq);

                    if (tempSol.length() < currentSol.length()) {
                        currentSol = tempSol;
                    }
                }
            }
            return currentSol;
        }
    }

    int getMoveCount() {
        return moveCount;
    }

    String getSequence() {
        return Sequence;
    }

}
