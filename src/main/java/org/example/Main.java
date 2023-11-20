package org.example;

import java.util.*;

public class Main {

    private static final int VALID_3_X_3_STRING_LENGTH = 17;
    private static final int VALID_3_X_3_ROW_LENGTH = 5;
    private static final int VALID_3_X_3_SIDE_LENGTH = 3;

    /**
     * Checks if a given list of integer lists is a 3 x 3 square
     *
     * @param square - a multi-dimensional list
     * @return a boolean based on if the passed list is a valid 3x3 square
     */
    public static boolean isValid3x3Square(List<List<Integer>> square) {
        if (square == null) return false;
        if (square.size() != VALID_3_X_3_SIDE_LENGTH) return false;
        for (List<Integer> row : square) {
            if (row == null || row.size() != VALID_3_X_3_SIDE_LENGTH) return false;
        }
        return true;
    }

    /**
     * Returns true or false based on if the passed string is the right length for a 3 x 3
     *
     * @param string - any string input
     * @return a boolean based on if the string is the correct length for a 3 x 3 output
     */
    public static boolean isValidSquareString(String string) {
        if (string.length() != VALID_3_X_3_STRING_LENGTH) return false;
        String[] rows = string.split("\n");
        if (rows.length != VALID_3_X_3_SIDE_LENGTH) return false;
        for (String row : rows) {
            if (row.length() != VALID_3_X_3_ROW_LENGTH) return false;
        }
        return true;
    }

    /**
     * Returns a formatted string output of a 3 x 3 two-dimensional integer list.
     * Example input... List<List<Integer> : ((1, 2, 3), (4, 5, 6), (7, 8, 9))
     * Example output...
     * """
     * 1 2 3
     * 4 5 6
     * 7 8 9"""
     *
     * @param square - 3 x 3 two-dimensional integer list
     * @return a string of formatted output of the input
     */
    public static String returnSquareToString(List<List<Integer>> square) {
        if (!isValid3x3Square(square)) throw new RuntimeException("Invalid 3 x 3 square!");
        StringBuilder output = new StringBuilder();
        for (List<Integer> integers : square) {
            for (int j = 0; j < integers.size(); j += 1) {
                output.append(integers.get(j));
                if (j != integers.size() - 1) {
                    output.append(" ");
                }
            }
            output.append("\n");
        }
        return output.toString().trim();
    }

    /**
     * Returns a 3 x 3 two-dimensional integer list given a formatted string of integers.
     * Example input...
     * """
     * 1 2 3
     * 4 5 6
     * 7 8 9"""
     * Example output... List<List<Integer> : ((1, 2, 3), (4, 5, 6), (7, 8, 9))
     *
     * @param input - a formatted string to represent a 3 x 3 square of digits
     * @return a 3 x 3 two-dimensional integer list
     */
    public static List<List<Integer>> returnStringToSquare(String input) {
        List<List<Integer>> square = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        String[] numStrs = input.split("\\s+");
        for (String numStr : numStrs) {
            Integer num = Integer.parseInt(numStr);
            nums.add(num);
        }
        int row = -1;
        for (int i = 0; i < nums.size(); i += 1) {
            if (i % 3 == 0) {
                square.add(new ArrayList<>());
                row++;
            }
            square.get(row).add(nums.get(i));
        }
        return square;
    }

    // loadCorners
    public static List<Integer> loadCorners(List<List<Integer>> square) {
        return new ArrayList<>(Arrays.asList(
            square.get(0).get(0), // top left corner
            square.get(0).get(2), // top right corner
            square.get(2).get(2), // bottom right corner
            square.get(2).get(0)  // bottom left corner
        ));
    }

    // writeCorners
    public static List<List<Integer>> writeCorners(List<List<Integer>> square, List<Integer> corners) {
        square.get(0).set(0, corners.get(0)); // replace top left corner
        square.get(0).set(2, corners.get(1)); // replace top left corner
        square.get(2).set(2, corners.get(2)); // replace top left corner
        square.get(2).set(0, corners.get(3)); // replace top left corner
        return square;
    }

    // rotateCorners
    public static List<List<Integer>> rotateCorners(List<List<Integer>> square) {
        List<Integer> corners = loadCorners(square);
        Collections.rotate(corners, 1);
        return writeCorners(square, corners);
    }

    // loadEdges
    public static List<Integer> loadEdges(List<List<Integer>> square) {
        return new ArrayList<>(Arrays.asList(
            square.get(0).get(1), // top edge
            square.get(1).get(2), // right edge
            square.get(2).get(1), // bottom edge
            square.get(1).get(0)  // left edge
        ));
    }

    // writeEdges
    public static List<List<Integer>> writeEdges(List<List<Integer>> square, List<Integer> edges) {
        square.get(0).set(1, edges.get(0)); // replace top edge
        square.get(1).set(2, edges.get(1)); // replace right edge
        square.get(2).set(1, edges.get(2)); // replace bottom edge
        square.get(1).set(0, edges.get(3)); // replace left edge
        return square;
    }

    // rotateEdges
    public static List<List<Integer>> rotateEdges(List<List<Integer>> square) {
        List<Integer> edges = loadEdges(square);
        Collections.rotate(edges, 1);
        return writeEdges(square, edges);
    }

    public static List<List<Integer>> rotateSquareClockwise(List<List<Integer>> square) {
        List<List<Integer>> rotatedEdgesSquare = rotateEdges(square);
        return rotateCorners(rotatedEdgesSquare);
    }

    public static List<List<Integer>> flipSquare(List<List<Integer>> square) {
        for (List<Integer> row : square) {
            int firstInt = row.get(0);
            row.set(0, row.get(2));
            row.set(2, firstInt);
        }
        return square;
    }

    public static List<List<Integer>> deepCopyOfSquareValues(List<List<Integer>> square) {
        List<List<Integer>> deepCopy = new ArrayList<>();
        for (List<Integer> row : square) {
            List<Integer> deepRow = new ArrayList<>(row);
            deepCopy.add(deepRow);
        }
        return deepCopy;
    }

    public static Map<Integer, List<List<Integer>>> createAllValid3x3PerfectSquares() {
        Map<Integer, List<List<Integer>>> squares = new HashMap<>();
        List<List<Integer>> square = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(8, 3, 4)),
            new ArrayList<>(Arrays.asList(1, 5, 9)),
            new ArrayList<>(Arrays.asList(6, 7, 2))
        ));
        for (int i = 1; i <= 8; i += 1) {
            squares.put(i, deepCopyOfSquareValues(square));
            if (i % 2 != 0) {
                flipSquare(square);
            } else {
                flipSquare(square);
                rotateSquareClockwise(square);
            }
        }
        return squares;
    }

    public static int find3x3SquareDifference(List<List<Integer>> square1, List<List<Integer>> square2) {
        int difference = 0;
        for (int i = 0; i < 3; i += 1) {
            for (int j = 0; j < 3; j += 1) {
                difference += Math.abs(square1.get(i).get(j) - square2.get(i).get(j));
            }
        }
        return difference;
    }

    public static int findLowestSquareDifference(List<List<Integer>> square) {
        int lowestDifference = 1000;
        Map<Integer, List<List<Integer>>> squares = createAllValid3x3PerfectSquares();
        for (int i = 1; i <= 8; i += 1) {
            int difference = find3x3SquareDifference(square, squares.get(i));
            if (difference < lowestDifference) {
                lowestDifference = difference;
            }
        }
        return lowestDifference;
    }

}