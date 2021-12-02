//  Advent of Code 2021
//  Day 1 - Sonar Sweep
//
// Created by Austin Swack
// Created on 12/1/2021
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day01 {

  public static int sonarSweep(File input) throws FileNotFoundException {
    Scanner scan = new Scanner(input);
    int count = 0;
    int a;
    int b = scan.nextInt();

    while(scan.hasNextInt()) {
      a = b;
      b = scan.nextInt();
      if (b > a)
        count++;
    }
    return count;
  }

  public static int windowSweep(File input) throws FileNotFoundException {
    Scanner scan = new Scanner(input);
    int count = 0;
    int a;
    int b = scan.nextInt();
    int c = scan.nextInt();
    int d = scan.nextInt();


    while(scan.hasNextInt()) {
      a = b;
      b = c;
      c = d;
      d = scan.nextInt();
      if ((b+c+d) > (a+b+c))
        count++;
    }
    return count;
  }

  public static void main(String[] args) throws FileNotFoundException {
    File input = new File("data/day01input.txt");

    System.out.println(sonarSweep(input));
    System.out.println(windowSweep(input));
  }
}