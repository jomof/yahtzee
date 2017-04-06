package com.jomofisher.yahtzee;

class Histogram {
  private static int lastHistogramForSum = 0;
  private static long lastSum = 0;
  private static int lastHistogramForBuckets = 0;
  private static int lastBuckets= 0;

  /**
   * Number of non-zero buckets in the histogram.
   */
  public static int buckets(int histogram) {
    if (histogram == lastHistogramForBuckets) {
      return lastBuckets;
    }
    lastHistogramForBuckets = histogram;
    int result = 0;
    while (histogram > 0) {
      if (histogram % 8 > 0) {
        ++result;
      }
      histogram >>= 3;
    }
    lastBuckets = result;
    return result;
  }

  /**
   * Return the number of items in the first non-zero bucket.
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
    lastHistogramForSum = histogram;

    int i = 0;
    long sum = 0;
    while (histogram > 0) {
      sum += (histogram % 8) * i;
      histogram >>= 3;
      ++i;
    }
    lastSum = sum;
    return sum;
  }
}
