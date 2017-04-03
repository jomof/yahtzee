package com.jomofisher.yahtzee;

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
}
