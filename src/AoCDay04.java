//  Advent of Code 2021
//  Day 4 - Giant Squid
//
// Created by Austin Swack
// Created on 12/4/2021
import java.io.*;
import java.util.*;

public class AoCDay04 {
  public static void main(String[] args) throws FileNotFoundException {
    File test = new File("data/day04test.txt");
    File input = new File("data/day04input.txt");

    System.out.println("---TEST CASES---");
    playBingo(test);
    System.out.println("---PUZZLE INPUT---");
    playBingo(input);
  }

  public static void playBingo(File input) throws FileNotFoundException {
    Scanner scan = new Scanner(input);
    int[] numbers = Arrays.stream(scan.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
    bingoNum[][] board = new bingoNum[5][5];
    ArrayList<String> listOfWinners = new ArrayList<>();

    // Build the bingo boards - then adds the number of steps it took to win and the sum of unmarked numbers to an ArrayList
    while (scan.hasNext()){
      for (int i = 0; i < board.length; i++){
        for (int j = 0; j < board[i].length; j++){
          board[i][j] = new bingoNum(scan.nextInt());
        }
      }
      listOfWinners.add(Arrays.toString(markNums(board, numbers)));
    }

    // Find the board that was solved in the least number of steps
    Collections.sort(listOfWinners);

    String fastest = listOfWinners.get(0).split(",")[1];
    System.out.println("Fastest Winning Board = " + Integer.parseInt(fastest.substring(1,fastest.length()-1)));

    String slowest = listOfWinners.get(listOfWinners.size()-1).split(",")[1];
    System.out.println("Slowest Winning Board = " + Integer.parseInt(slowest.substring(1,slowest.length()-1))+"\n");
  }

  // Marks numbers on each board as called and checks to see if a winning board state is present
  public static Integer[] markNums(bingoNum[][] board, int[] numbers) {
    int steps = 0; // Tracks how many steps it took to win
    for (int num : numbers) {
      steps++;
      for (int row = 0; row < board.length; row++) {
        for (int col = 0; col < board[row].length; col++) {
          if (num == board[row][col].number) {
            board[row][col].marked = true;
            if (checkBoard(board)){ // Checks the board state every time a new number is marked
              Integer[] winner = new Integer[] {steps, sumUnmarkedNumbers(board, num)}; // Return array to be added to the winners ArrayList
              return winner;
            }
          }
        }
      }
    }
    return null; // No winner was found for this particular board
  }

  // Checks to see if a bingo has been called
  public static boolean checkBoard(bingoNum[][] board) {
    // CHECK FOR WINNING ROW
    for (int i = 0; i < board.length; i++) {
      boolean isBingo = true;
      for (int j = 0; j < board[i].length; j++){
        if (!board[i][j].marked) isBingo = false;
      }
      if (isBingo) return true;
    }

    // CHECK FOR WINNING COLUMN
    for (int i = 0; i < board.length; i++) {
      boolean isBingo = true;
      for (int j = 0; j < board[i].length; j++){
        if (!board[j][i].marked) isBingo = false;
      }
      if (isBingo) return true;
    }
    return false;
  }

  // For winning board states - checks and returns the sum of unmarked numbers * the last number called
  public static int sumUnmarkedNumbers(bingoNum[][] board, int winningNum) {
    int sum = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (!board[i][j].marked) {
          sum += board[i][j].number;
        }
      }
    }
    return sum * winningNum;
  }
}

class bingoNum {
  public int number;
  public boolean marked;

  public bingoNum(int number) {
    this.number = number;
    marked = false;
  }
}