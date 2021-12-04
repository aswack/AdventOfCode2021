//  Advent of Code 2021
//  Day 2 - Dive!
//
// Created by Austin Swack
// Created on 12/2/2021
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AoCDay02 {

  public static int findDepth(File input) throws FileNotFoundException {
    Scanner scan = new Scanner(input);
    int xAxis = 0;
    int yAxis = 0;

    while (scan.hasNext()) {
      String direction = scan.next();
      int units = scan.nextInt();
      switch (direction) {
        case "forward" -> xAxis += units;
        case "down" -> yAxis += units;
        case "up" -> yAxis -= units;
        default -> {
        }
      }
    }
    return xAxis * yAxis;
  }

  public static int findActualDepth(File input) throws FileNotFoundException {
    Scanner scan = new Scanner(input);
    int xAxis = 0;
    int yAxis = 0;
    int aim = 0;

    while (scan.hasNext()) {
      String direction = scan.next();
      int units = scan.nextInt();
      switch (direction) {
        case "forward" -> {
          xAxis += units;
          yAxis += units * aim;
        }
        case "down" -> aim += units;
        case "up" -> aim -= units;
        default -> {
        }
      }
    }
    return xAxis * yAxis;
  }

  public static void main(String[] args) throws FileNotFoundException {
    File test = new File("data/day02test.txt");
    File input = new File("data/day02input.txt");

    System.out.println("Test Depth: " + findDepth(test));
    System.out.println("Test Actual Depth: " + findActualDepth(test));
    System.out.println("Input Depth: " + findDepth(input));
    System.out.println("Input Actual Depth: " + findActualDepth(input));
  }
}
