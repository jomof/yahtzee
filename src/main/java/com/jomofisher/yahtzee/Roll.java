package com.jomofisher.yahtzee;

import java.util.HashMap;
import java.util.Map;

public class Roll {
  public static String sort(String roll) {
    check(roll);
    for (int i = 0; i < roll.length(); ++i) {
      for (int j = 0; j < roll.length(); ++j) {
        if (i >= j) {
          continue;
        }
        if (roll.charAt(i) > roll.charAt(j)) {
          char ic = roll.charAt(i);
          char jc = roll.charAt(j);
          roll = roll.substring(0, i) + jc + roll.substring(i + 1, j) + ic + roll.substring(j + 1);
          check(roll);
        }
      }
    }
    return roll;
  }

  private static void check(String roll) {
    assert (roll != null);
    assert (roll.length() == 5);
    for (int i = 0; i < roll.length(); ++i) {
      checkDie(roll.charAt(i));
    }
  }

  private static void checkDie(char die) {
    assert (die == '0' || die == '1' || die == '2' || die == '3' || die == '4' || die == '5' || die == '6');
  }

  public static long count(String roll, int value) {
    check(roll);
    long result = 0;
    for (int i = 0; i < roll.length(); ++i) {
      if (valueAt(roll, i) == value) {
        ++result;
      }
    }
    return result;
  }

  public static long sum(String roll) {
    check(roll);
    long result = 0;
    for (int i = 0; i < roll.length(); ++i) {
      result += valueAt(roll, i);
    }
    return result;
  }

  public static Map<Long, Long> histogram(String roll) {
    check(roll);
    Map<Long, Long> result = new HashMap<>();
    for (int i = 0; i < roll.length(); ++i) {
      long valueAt = Roll.valueAt(roll, i);
      Long count = result.get(valueAt);
      if (count == null) {
        count = 0L;
      }
      ++count;
      result.put(valueAt, count);
    }
    return result;
  }

  private static int valueAt(String roll, int i) {
    return roll.charAt(i) - '0';
  }
}
