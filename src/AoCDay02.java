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
        case "forward":
          xAxis += units;
          break;
        case "down":
          yAxis += units;
          break;
        case "up":
          yAxis -= units;
          break;
        default:
          break;
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
        case "forward":
          xAxis += units;
          yAxis += units*aim;
          break;
        case "down":
          aim += units;
          break;
        case "up":
          aim -= units;
          break;
        default:
          break;
      }
    }
    return xAxis * yAxis;
  }

  public static void main(String[] args) throws FileNotFoundException {
    File input = new File("data/day02input.txt");
    System.out.println(findDepth(input));
    System.out.println(findActualDepth(input));
  }
}
