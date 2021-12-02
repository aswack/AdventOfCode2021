//  Advent of Code 2021
//  Day 3 - Binary Diagnostic
//
// Created by Austin Swack
// Created on 12/3/2021
import java.io.*;
import java.util.*;

public class AoCDay03 {

  public static int powerConsumption(File input) throws FileNotFoundException {
    Scanner scan = new Scanner(input);
    int readings = 1;
    StringBuilder gamma = new StringBuilder();
    StringBuilder epsilon = new StringBuilder();
    String binary = scan.next();
    int[] digits = new int[binary.length()];

    while (scan.hasNext()) {
      for (int i = 0; i < binary.length(); i++) {
        if (binary.charAt(i) == '1') {
          digits[i]++;
        }
      }
      binary = scan.next();
      readings++;
    }

    for (int i = 0; i < binary.length(); i++) {
      if (digits[i] >= readings / 2) {
        gamma.append("1");
        epsilon.append("0");
      } else {
        gamma.append("0");
        epsilon.append("1");
      }
    }
    return Integer.parseInt(gamma.toString(), 2) * Integer.parseInt(epsilon.toString(), 2);
  }

  public static int lifeSupportRating(File input) throws FileNotFoundException {
    Scanner scan = new Scanner(input);
    int o2GenRating = 0;
    int co2ScrubRating = 0;
    ArrayList<String> oxygen = new ArrayList<>();
    ArrayList<String> dioxide = new ArrayList<>();

    // scan and add first input in the dataset
    String binary = scan.next();
    int numBits = binary.length();
    oxygen.add(binary);
    dioxide.add(binary);

    // add everything in the dataset to all three ArrayLists
    while (scan.hasNext()) {
      binary = scan.next();
      oxygen.add(binary);
      dioxide.add(binary);
    }

    // Iterates though each bit in the entire dataset
    for (int i = 0; i < numBits; i++) {
      int[] oxyBitCount = new int[numBits];
      int[] dioBitCount = new int[numBits];

      // find the most common bit for each position in the oxygen array
      for (String o2 : oxygen) {
        for (int j = 0; j < numBits; j++) {
          if (o2.charAt(j) == '1') {
            oxyBitCount[j]++;
          }
        }
      }

      // Finds the most common bit for each position in the dioxide array
      for (String co2 : dioxide) {
        for (int j = 0; j < numBits; j++) {
          if (co2.charAt(j) == '1') {
            dioBitCount[j]++;
          }
        }
      }

      // Finds and discards oxygen numbers that do not meet the bit criteria
      List<String> oxygenCopy = new ArrayList<>(oxygen);
      if (oxyBitCount[i] >= oxygen.size() - oxyBitCount[i]) {
        for (String o2 : oxygenCopy) {
          if (o2.charAt(i) == '0') {
            oxygen.remove(o2);
          }
        }
      } else {
        for (String o2 : oxygenCopy) {
          if (o2.charAt(i) == '1') {
            oxygen.remove(o2);
          }
        }
      }

      // Finds and discards dioxide numbers that do not meet the bit criteria
      List<String> dioxideCopy = new ArrayList<>(dioxide);
      if (dioBitCount[i] >= dioxide.size() - dioBitCount[i]) {
        for (String co2 : dioxideCopy) {
          if (co2.charAt(i) == '1') {
            dioxide.remove(co2);
          }
        }
      } else {
        for (String co2 : dioxideCopy) {
          if (co2.charAt(i) == '0') {
            dioxide.remove(co2);
          }
        }
      }

      // Stores the decimal value of the bit when the corresponding array = 1
      if (oxygen.size() == 1)
        o2GenRating = Integer.parseInt(oxygen.get(0), 2);
      if (dioxide.size() == 1)
        co2ScrubRating = Integer.parseInt(dioxide.get(0), 2);
    }
    return o2GenRating * co2ScrubRating;
  }

  public static void main(String[] args) throws FileNotFoundException {
    File test = new File("data/day03test.txt");
    File input = new File("data/day03input.txt");
    System.out.println("Test Power Consumption = " + powerConsumption(test));
    System.out.println("Input Power Consumption = " + powerConsumption(input));
    System.out.println("Test Life Support Rating = " + lifeSupportRating(test));
    System.out.println("Input Life Support Rating = " + lifeSupportRating(input));
  }
}