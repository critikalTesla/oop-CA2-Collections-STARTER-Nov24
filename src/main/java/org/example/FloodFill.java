package org.example;

import java.util.Scanner;
import java.util.Stack;

class Cell {
    int row;
    int col;

    // Constructor for Cell
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class FloodFill {
    public static void main(String[] args) {
        // Initialize a 10x10 array with all cells set to 0
        int[][] grid = new int[10][10];

        // Prompt user for the starting row and column
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter starting row (0-9): ");
        int startRow = scanner.nextInt();
        System.out.print("Enter starting column (0-9): ");
        int startCol = scanner.nextInt();

        // Check if the starting position is within bounds
        if (startRow < 0 || startRow >= 10 || startCol < 0 || startCol >= 10) {
            System.out.println("Invalid starting position. Exiting program.");
            return;
        }

        // Stack to manage the cells for flood fill
        Stack<Cell> stack = new Stack<>();
        stack.push(new Cell(startRow, startCol));

        // Counter for filling numbers
        int fillNumber = 1;

        // Perform the flood fill algorithm
        while (!stack.isEmpty()) {
            // Pop the top cell from the stack
            Cell current = stack.pop();
            int row = current.row;
            int col = current.col;

            // Check if the current cell is already filled
            if (grid[row][col] == 0) {
                // Fill the current cell
                grid[row][col] = fillNumber++;

                // Push unfilled neighbors onto the stack (North, East, South, West)
                if (row > 0 && grid[row - 1][col] == 0) stack.push(new Cell(row - 1, col)); // North
                if (col < 9 && grid[row][col + 1] == 0) stack.push(new Cell(row, col + 1)); // East
                if (row < 9 && grid[row + 1][col] == 0) stack.push(new Cell(row + 1, col)); // South
                if (col > 0 && grid[row][col - 1] == 0) stack.push(new Cell(row, col - 1)); // West
            }
        }

        // Print the resulting grid
        System.out.println("Flood-filled grid:");
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.printf("%3d", cell);
            }
            System.out.println();
        }
    }
}

