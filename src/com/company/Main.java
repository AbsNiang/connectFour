package com.company;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static String[][] grid = new String[6][7];

    static void originalGrid() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                grid[i][j] = "[_]";
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    static void gridSetup(String[][] updatedGrid) {
        // sets up the updated version of the grid
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(updatedGrid[i][j]);
            }
            System.out.println();
        }
    }

    static int rowCalculator(String[][] updatedGrid, int columnNo) {
        //this finds if there are any open spaces in the row for a counter and its location
        int openSpaceRow = 0;
        for (int i = 5; i >= 0; i--) {
            if (!updatedGrid[i][columnNo - 1].equals("[Y]") && !updatedGrid[i][columnNo - 1].equals("[R]")) {
                openSpaceRow = i;
                break;
            } else {
                openSpaceRow = 6;
            }
        }
        return openSpaceRow;
    }

    static boolean winner(String[][] updatedGrid, String counterColour) {
        //horizontal four-in-a-row check
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (Objects.equals(updatedGrid[i][j], counterColour) && Objects.equals(updatedGrid[i][j + 1], counterColour) && Objects.equals(updatedGrid[i][j + 2], counterColour) && Objects.equals(updatedGrid[i][j + 3], counterColour)) {
                    return false;
                }
            }

        }
        //vertical four-in-a-row check
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                if (Objects.equals(updatedGrid[i][j], counterColour) && Objects.equals(updatedGrid[i + 1][j], counterColour) && Objects.equals(updatedGrid[i + 2][j], counterColour) && Objects.equals(updatedGrid[i + 3][j], counterColour)) {
                    return false;
                }
            }

        }
        //diagonal four-in-a-row check (downward diagonal)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (Objects.equals(updatedGrid[i][j], counterColour) && Objects.equals(updatedGrid[i + 1][j + 1], counterColour) && Objects.equals(updatedGrid[i + 2][j + 2], counterColour) && Objects.equals(updatedGrid[i + 3][j + 3], counterColour)) {
                    return false;
                }
            }
        }
        //diagonal four-in-a-row check (upward diagonal)
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (Objects.equals(updatedGrid[i][j], counterColour) && Objects.equals(updatedGrid[i - 1][j + 1], counterColour) && Objects.equals(updatedGrid[i - 2][j + 2], counterColour) && Objects.equals(updatedGrid[i - 3][j + 3], counterColour)) {
                    return false;
                }
            }
        }
        //full grid check
        int fullColumns = 0;
        for (int i = 0; i < 7; i++) {
            if (updatedGrid[0][i].equals("[Y]") || updatedGrid[0][i].equals("[R]")) {
                fullColumns++;
            }
        }
        return fullColumns != 7;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int turnCount = 0;
        boolean activePlay = true;
        String counterColour = "Y";
        System.out.println("The game of connect 4 will now commence.");
        System.out.println("Player 1 will have yellow (Y) and player 2 will have red (R)");
        System.out.println(" 1  2  3  4  5  6  7");
        originalGrid(); //sets up the original, empty grid
        while (activePlay) {
            try {
                if (turnCount % 2 == 0) {
                    System.out.println("It is player 1's turn."); //rotates the turns between players
                    counterColour = "[Y]";
                } else {
                    System.out.println("It is player 2's turn."); //rotates the turns between players
                    counterColour = "[R]";
                }
                System.out.println("Which column would you like to put your counter in?");
                int counterColumn = input.nextInt();
                grid[rowCalculator(grid, counterColumn)][counterColumn - 1] = counterColour;
                gridSetup(grid);
                turnCount++;
            } catch (InputMismatchException e) {
                gridSetup(grid);
                System.out.println("You have not typed a number");
                input.next();
            } catch (ArrayIndexOutOfBoundsException e) {
                gridSetup(grid);
                System.out.println("You have typed a column that isn't available");
            }
            activePlay = winner(grid, counterColour);
            if (!activePlay && counterColour.equals("[Y]")) {
                System.out.println("Player 1 (Y) has won.");
            } else if (!activePlay && counterColour.equals("[R]")) {
                System.out.println("Player 2 (R) has won. ");
            }
        }
    }
}
