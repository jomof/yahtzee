package com.jomofisher.yahtzee;

class Histogram {
  static int lastHistogramForSum = 0;
  static long lastSum = 0;

  /**
   * Noumber of non-zero buckets in the histogram.
   */
  public static int buckets(int histogram) {
    int result = 0;
    while (histogram > 0) {
      if (histogram % 8 > 0) {
        ++result;
      }
      histogram >>= 3;
    }
    return result;
  }

  /**
   * Return the number of items in th first non-zero bucket.
   */
  public static long firstBucketCount(int histogram) {
    while (histogram > 0) {
      if (histogram % 8 > 0) {
        return histogram & 7;
      }
      histogram >>= 3;
    }
    throw new RuntimeException();
  }

  /**
   * Return then number of digits found at the given position
   */
  public static long countAt(int histogram, int n) {
    histogram >>= (3 * n);
    return histogram & 7;
  }

  /**
   * Return the sum of the digits.
   */
  public static long sum(int histogram) {
    if (histogram == lastHistogramForSum) {
      return lastSum;
    }

    int i = 0;
    long sum = 0;
    while (histogram > 0) {
      sum += (histogram % 8) * i;
      histogram >>= 3;
      ++i;
    }
    lastHistogramForSum = histogram;
    lastSum = sum;
    return sum;
  }
}
