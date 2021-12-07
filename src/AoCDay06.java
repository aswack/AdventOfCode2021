//  Advent of Code 2021
//  Day 6 - Laternfish
//
//  Created by Austin Swack
//  Created on 12/6/2021
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AoCDay06 {

  public static void main(String[] args) throws FileNotFoundException {
    File test = new File("data/day06test.txt");
    File input = new File("data/day06input.txt");

    schoolSize(test, 80);
    schoolSize(input, 80);

    spawnCount(test, 80);
    spawnCount(input, 80);
    spawnCount(input, 256);
  }

  public static void spawnCount(File input, int days) throws FileNotFoundException {
    Scanner scan = new Scanner(input);
    long[] nums = new long[10];
    String[] temp = scan.nextLine().split(",");
    scan.close();

    for (String s : temp) {
      switch (Integer.parseInt(s.trim())) {
        case 0 -> nums[0]++;
        case 1 -> nums[1]++;
        case 2 -> nums[2]++;
        case 3 -> nums[3]++;
        case 4 -> nums[4]++;
        case 5 -> nums[5]++;
        case 6 -> nums[6]++;
        case 7 -> nums[7]++;
        case 8 -> nums[8]++;
        case 9 -> nums[9]++;
      }
    }

    int daysElapsed = 1;
    while (daysElapsed < days) {
      daysElapsed++;
      System.arraycopy(nums, 1, nums, 0, nums.length - 1);
      nums[9] = 0;
      if (nums[0] != 0) {
        long x = nums[0];
        nums[7] += x;
        nums[9] = x;
        nums[0] = 0;
      }
    }

    long sum = 0;
    for (int i = 1; i < nums.length; i++) {
      sum += nums[i];
    }

    System.out.println("After " + days + " days, there are " + sum + " fish.");
  }

  // First brute force attempt - did not scale to 256 days
  public static void schoolSize(File input, int days) throws FileNotFoundException {
    Scanner scan = new Scanner(input);
    ArrayList<Integer> fish = new ArrayList<>();
    String[] temp = scan.nextLine().split(",");

    for (String s : temp) {
      fish.add(Integer.parseInt(s.trim()));
    }

    int daysElapsed = 0;

    while (daysElapsed < days) {
      daysElapsed++;
      for (int i = 0; i < fish.size(); i++) {
        if (fish.get(i) == 0) {
          fish.set(i, 7);
          fish.add(9);
        }
        fish.set(i, fish.get(i) - 1);
      }
    }

    System.out.println("After " + days + " days, there are " + fish.size() + " fish.");
  }
}