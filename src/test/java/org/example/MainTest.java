package org.example;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.example.Main.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void returnStringToSquareTest() {
        String input = """
            1 2 3
            4 5 6
            7 8 9
            """.trim();
        List<List<Integer>> square = returnStringToSquare(input);
        assertEquals("[1, 2, 3]", square.get(0).toString());
        assertEquals("[4, 5, 6]", square.get(1).toString());
        assertEquals("[7, 8, 9]", square.get(2).toString());
    }

    @Test
    void isValidSquareStringTest() {
        String validSquareInput = """
            1 1 1
            2 2 2
            3 3 3
            """.trim();
        String invalidSquareInput = """
            1 1 1
            2 2 2
            3 3 3
            """;
        String invalidSquareInput2 = "1 1 1 2 2 2 3 3 3";
        assertTrue(isValidSquareString(validSquareInput));
        assertFalse(isValidSquareString("bob"));
        assertFalse(isValidSquareString(invalidSquareInput));
        assertFalse(isValidSquareString(invalidSquareInput2));
    }

    @Test
    void isValid3x3SquareTest() {
        List<List<Integer>> nullSquare = null;
        assertFalse(isValid3x3Square(nullSquare));

        List<List<Integer>> emptyListSquare = new ArrayList<>();
        assertFalse(isValid3x3Square(emptyListSquare));

        List<List<Integer>> rowNullSquare = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(1, 1, 1)),
            new ArrayList<>(Arrays.asList(2, 2, 2)),
            null
        ));
        assertFalse(isValid3x3Square(rowNullSquare));

        List<List<Integer>> tooManyRowsSquare = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(1, 1, 1)),
            new ArrayList<>(Arrays.asList(2, 2, 2)),
            new ArrayList<>(Arrays.asList(3, 3, 3)),
            new ArrayList<>(Arrays.asList(4, 4, 4))
        ));
        assertFalse(isValid3x3Square(tooManyRowsSquare));

        List<List<Integer>> tooManyColumnsSquare = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(1, 1, 1, 1)),
            new ArrayList<>(Arrays.asList(2, 2, 2)),
            new ArrayList<>(Arrays.asList(3, 3, 3)))
        );
        assertFalse(isValid3x3Square(tooManyColumnsSquare));

        List<List<Integer>> correctSquare = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(1, 1, 1)),
            new ArrayList<>(Arrays.asList(2, 2, 2)),
            new ArrayList<>(Arrays.asList(3, 3, 3)))
        );
        assertTrue(isValid3x3Square(correctSquare));
    }

    @Test
    void returnSquareToStringTest() {
        List<List<Integer>> invalidInput = null;
        RuntimeException e = assertThrows(RuntimeException.class, () -> {
            returnSquareToString(invalidInput);
        });
        assertTrue(e.getMessage().contains("Invalid 3 x 3 square!"));
        List<List<Integer>> validInput = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(1, 1, 1)),
            new ArrayList<>(Arrays.asList(2, 2, 2)),
            new ArrayList<>(Arrays.asList(3, 3, 3)))
        );
        String expectedOutput = """
            1 1 1
            2 2 2
            3 3 3
            """.trim();
        assertEquals(expectedOutput, returnSquareToString(validInput));
    }

    @Test
    void rotateSquareClockwiseTest() {
        List<List<Integer>> input = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(1, 2, 3)),
            new ArrayList<>(Arrays.asList(8, 5, 4)),
            new ArrayList<>(Arrays.asList(9, 6, 7))
        ));
        List<List<Integer>> output = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(9, 8, 1)),
            new ArrayList<>(Arrays.asList(6, 5, 2)),
            new ArrayList<>(Arrays.asList(7, 4, 3))
        ));
        assertEquals(output, rotateSquareClockwise(input));
    }

    @Test
    void createAllValid3x3PerfectSquaresTest() {
        Map<Integer, List<List<Integer>>> squares = new HashMap<>();
        List<List<Integer>> square1 = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(8, 3, 4)),
            new ArrayList<>(Arrays.asList(1, 5, 9)),
            new ArrayList<>(Arrays.asList(6, 7, 2))
        ));
        List<List<Integer>> square1Flipped = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(4, 3, 8)),
            new ArrayList<>(Arrays.asList(9, 5, 1)),
            new ArrayList<>(Arrays.asList(2, 7, 6))
        ));
        List<List<Integer>> square2 = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(6, 1, 8)),
            new ArrayList<>(Arrays.asList(7, 5, 3)),
            new ArrayList<>(Arrays.asList(2, 9, 4))
        ));
        List<List<Integer>> square2Flipped = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(8, 1, 6)),
            new ArrayList<>(Arrays.asList(3, 5, 7)),
            new ArrayList<>(Arrays.asList(4, 9, 2))
        ));
        List<List<Integer>> square3 = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(2, 7, 6)),
            new ArrayList<>(Arrays.asList(9, 5, 1)),
            new ArrayList<>(Arrays.asList(4, 3, 8))
        ));
        List<List<Integer>> square3Flipped = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(6, 7, 2)),
            new ArrayList<>(Arrays.asList(1, 5, 9)),
            new ArrayList<>(Arrays.asList(8, 3, 4))
        ));
        List<List<Integer>> square4 = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(4, 9, 2)),
            new ArrayList<>(Arrays.asList(3, 5, 7)),
            new ArrayList<>(Arrays.asList(8, 1, 6))
        ));
        List<List<Integer>> square4Flipped = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(2, 9, 4)),
            new ArrayList<>(Arrays.asList(7, 5, 3)),
            new ArrayList<>(Arrays.asList(6, 1, 8))
        ));
        squares.put(1, square1);
        squares.put(2, square1Flipped);
        squares.put(3, square2);
        squares.put(4, square2Flipped);
        squares.put(5, square3);
        squares.put(6, square3Flipped);
        squares.put(7, square4);
        squares.put(8, square4Flipped);

        Map<Integer, List<List<Integer>>> squaresToTest = createAllValid3x3PerfectSquares();
        for (int i = 1; i <= 8; i += 1) {
            assertEquals(returnSquareToString(squaresToTest.get(i)), returnSquareToString(squares.get(i)));
        }
    }

    @Test
    public void findSquareDifferenceTest() {
        String square1 = """
            0 0 0
            0 0 0
            0 0 0
            """.trim();
        String square2 = """
            1 0 0
            0 0 0
            0 0 0
            """.trim();
        String square3 = """
            8 3 4
            1 5 9
            6 7 2
            """.trim();
        String square4 = """
            8 3 2
            1 5 9
            6 7 2
            """.trim();
        String square5 = """
            5 3 4
            1 5 8
            6 4 2
            """.trim();

        assertEquals(0, find3x3SquareDifference(returnStringToSquare(square1), returnStringToSquare(square1)));
        assertEquals(1, find3x3SquareDifference(returnStringToSquare(square1), returnStringToSquare(square2)));
        assertEquals(2, find3x3SquareDifference(returnStringToSquare(square3), returnStringToSquare(square4)));
        assertEquals(7, find3x3SquareDifference(returnStringToSquare(square3), returnStringToSquare(square5)));

    }

    @Test
    public void lowestDifferenceTest() {
        List<List<Integer>> squareInput = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(5, 3, 4)),
            new ArrayList<>(Arrays.asList(1, 5, 8)),
            new ArrayList<>(Arrays.asList(6, 4, 2))
        ));

        List<List<Integer>> squareInput2 = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(4, 9, 2)),
            new ArrayList<>(Arrays.asList(3, 5, 7)),
            new ArrayList<>(Arrays.asList(8, 1, 5))
        ));

        assertEquals(7, findLowestSquareDifference(squareInput));
        assertEquals(1, findLowestSquareDifference(squareInput2));
    }

}