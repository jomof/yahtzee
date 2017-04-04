package com.jomofisher.yahtzee;

import java.util.Arrays;

class Longs {
  public static long median(long values[]) {
    Arrays.sort(values);
    return values[values.length/2];
  }
}
