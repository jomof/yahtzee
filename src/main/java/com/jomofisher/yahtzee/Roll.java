package com.jomofisher.yahtzee;

import java.util.Random;

class Roll {
  private static Random rand = new Random();

  static void check(String roll) {
    assert (roll != null);
    assert (roll.length() == 5);
    for (int i = 0; i < roll.length(); ++i) {
      checkDie(roll.charAt(i));
    }
  }

  private static void checkDie(char die) {
    assert (die == '0' || die == '1' || die == '2' || die == '3'
        || die == '4' || die == '5' || die == '6' || die == 'x');
  }

  /**
   * Count the number of die that have value.
   */
  public static long count(int roll, int value) {
    long result = 0;
    while (roll > 0) {
      int valueAt = roll % 8;
      if (valueAt == value) {
        ++result;
      }
      roll >>= 3;
    }
    return result;
  }

  /**
   * Show the die values as a String
   */
  public static String asString(int roll) {
    String result = "";
    while (roll > 0) {
      int valueAt = roll % 8;
      if (valueAt == 0) {
        result = 'x' + result;
      } else {
        valueAt += '0';
        result = (char) valueAt + result;
      }
      roll >>= 3;
    }
    while (result.length() < 5) {
      result = 'x' + result;
    }
    return result;
  }

  /**
   * Convert a String value like 12345 to int form
   */
  public static int fromString(String roll) {
    check(roll);
    int result = 0;
    for (int i = 0; i < roll.length(); ++i) {
      result <<= 3;
      result += roll.charAt(i) - '0';
    }
    return result;
  }

  public static long sum(int roll) {
    int result = 0;
    while (roll > 0) {
      int valueAt = roll % 8;
      result += valueAt;
      roll >>= 3;
    }
    return result;
  }

  public static int histogram(int roll) {
    // Format of result is 3 bits per slot
    int result = 0;
    while (roll > 0) {
      int valueAt = roll % 8;
      result += (1 << valueAt * 3);
      roll >>= 3;
    }
    return result;
  }

  public static int reroll(int roll) {
    for (int i = 0; i < 5; ++i) {
      if (Roll.getDigitAt(roll, i) == 0) {
        int die = rand.nextInt(6) + 1;
        roll = Roll.putDigitAt(roll, i, die);
      }
    }
    return roll;
  }

  public static int randomInt() {
    return reroll(0);
  }

  public static Split[] splits(int roll) {
    String t = Roll.asString(roll);
    Split result[] = new Split[32];
    for (int i = 0; i < 32; ++i) {
      int keep = 0;
      int reroll = 0;
      int mod = i;
      for (int j = 0; j < 5; ++j) {
        int digit = Roll.getDigitAt(roll, j);
        if (digit == 0) {
          throw new RuntimeException(String.format("assertThat(Roll.getDigitAt(%s, %s)).isNotEqualTo(0);", roll, j));
        }
        assert(digit != 0);
        if (mod % 2 == 0) {
          reroll = Roll.putDigitAt(reroll, j, digit);
        } else {
          keep = Roll.putDigitAt(keep, j, digit);
        }
        mod >>= 1;
      }
      result[i] = new Split(keep, reroll);
    }
    return result;
  }

  public static int getDigitAt(int roll, int n) {
    assert(n >= 0 && n < 5);
    roll >>= (3 * n);
    int die = roll & 7;
    assert(die >= 0 && die <= 6);
    return die;
  }

  public static int putDigitAt(int roll, int n, int die) {
    assert(die > 0 && die <= 6);
    assert (getDigitAt(roll, n) == 0);
    die <<= (n * 3);
    return roll | die;
  }
}
