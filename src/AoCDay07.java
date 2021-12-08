//  Advent of Code 2021
//  Day 7 - The Treachery of Whales
//  https://adventofcode.com/2021/day/7
//  Created by Austin Swack
//  Created on 12/7/2021
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AoCDay07 {

  public static void main(String[] args) throws FileNotFoundException {
    File test = new File("data/day07test.txt");
    File input = new File("data/day07input.txt");

    System.out.println("--- TEST INPUT ---");
    cheapFuel(test);
    expensiveFuel(test);
    System.out.println("--- PUZZLE INPUT ---");
    cheapFuel(input);
    expensiveFuel(input);
  }

  public static void cheapFuel(File input) throws FileNotFoundException {
    Scanner scan = new Scanner(input);
    assert scan.nextLine() != null;
    String[] arr = scan.nextLine().split(",");
    int[] nums = new int[arr.length];

    for (int i = 0; i < arr.length; i++) {
      nums[i] = Integer.parseInt(arr[i]);
    }
    int minFuel = Integer.MAX_VALUE;
    int minFuelIndex = 0;

    for (int i = 1; i <= biggestValue(nums); i++) {
      int fuelBurned = 0;
      for (int num : nums) {
        int tempNum = num;
        while (tempNum != i) {
          if (tempNum < i)
            tempNum++;
          else
            tempNum--;
          fuelBurned++;
        }
        if (fuelBurned > minFuel) break;
      }
      if (fuelBurned < minFuel) {
        minFuel = fuelBurned;
        minFuelIndex = i;
      }
    }
    System.out.println("Submarines move to position " + minFuelIndex + " at a cost of " + minFuel + " fuel units.");
  }

  public static void expensiveFuel(File input) throws FileNotFoundException {
    Scanner scan = new Scanner(input);
    assert scan.nextLine() != null;
    String[] arr = scan.nextLine().split(",");
    int[] nums = new int[arr.length];

    for (int i = 0; i < arr.length; i++) {
      nums[i] = Integer.parseInt(arr[i]);
    }
    long minFuel = Long.MAX_VALUE;
    int minFuelIndex = 0;

    for (int i = 1; i <= biggestValue(nums); i++) {
      long fuelBurned = 0;

      for (long num : nums) {
        long tempNum = num;
        long unitsMoved = 0;

        while (tempNum != i) {
          if (tempNum < i)
            tempNum++;
          else if (tempNum > i)
            tempNum--;
          unitsMoved++;
        }
        fuelBurned += sumNums(unitsMoved);
        if (fuelBurned > minFuel) break;
      }
      if (fuelBurned < minFuel) {
        minFuel = fuelBurned;
        minFuelIndex = i;
      }
    }
    System.out.println("Submarines move to position " + minFuelIndex + " at a cost of " + minFuel + " fuel units.");
  }

  // Helper Methods
  public static int biggestValue(int[] arr) {
    int max = Integer.MIN_VALUE;

    for (int j : arr) {
      if (j > max)
        max = j;
    }
    return max;
  }
  public static long sumNums(long i) {
    if (i < 1) {
      return 0;
    } else {
      return i + sumNums(i-1);
    }
  }
}