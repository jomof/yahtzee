package com.jomofisher.yahtzee;

import java.util.Map;

public enum Slot {
  Ones,
  Twos,
  Threes,
  Fours,
  Fives,
  Sixes,
  ThreeOfKind,
  FourOfKind,
  FullHouse,
  SmallStraight,
  LargeStraight,
  Chance,
  Yahtzee;

  private static boolean isMultiple(long value, long of) {
    return ((value / of) * of) == value;
  }

//  private boolean isValidValue(Long value) {
//    switch (this) {
//      case Ones:
//        return isMultiple(value, 1);
//      case Twos:
//        return isMultiple(value, 2);
//      case Threes:
//        return isMultiple(value, 3);
//      case Fours:
//        return isMultiple(value, 4);
//      case Fives:
//        return isMultiple(value, 5);
//      case Sixes:
//        return isMultiple(value, 6);
//      case ThreeOfKind:
//        return value >= 5; // Five ones is lowest 3-of-kind
//      case FourOfKind:
//        return value >= 5; // Five ones is lowest 4-of-kind
//      case FullHouse:
//        return value >= 5; // Five ones is lowest full house
//      case SmallStraight:
//        return value == 30;
//      case LargeStraight:
//        return value == 40;
//      case Chance:
//        return value >= 5; // Five ones is lowest chance
//      case Yahtzee:
//        return value >= 5; // Five ones is lowest yahtzee
//    }
//    throw new RuntimeException("Unrecognized");
//  }

  public long points(int hist) {
    switch (this) {
      case Ones:
        return Histogram.countAt(hist, 1);
      case Twos:
        return Histogram.countAt(hist, 2) * 2;
      case Threes:
        return Histogram.countAt(hist, 3) * 3;
      case Fours:
        return Histogram.countAt(hist, 4) * 4;
      case Fives:
        return Histogram.countAt(hist, 5) * 5;
      case Sixes:
        return Histogram.countAt(hist, 6) * 6;
      case ThreeOfKind: {
        for (int i = 1; i < 7; ++i) {
          long count = Histogram.countAt(hist, i);
          if (count >= 3) {
            return Histogram.sum(hist);
          }
        }
        return 0;
      }
      case FourOfKind: {
        for (int i = 1; i < 7; ++i) {
          long count = Histogram.countAt(hist, i);
          if (count >= 4) {
            return Histogram.sum(hist);
          }
        }
        return 0;
      }
      case FullHouse: {
        int buckets = Histogram.buckets(hist);
        if (buckets == 1) {
          return 25;
        }
        if (buckets != 2) {
          return 0;
        }
        long count = Histogram.firstBucketCount(hist);
        if (count == 2 || count == 3) {
          return 25;
        }
        return 0;
      }
      case Yahtzee: {
        return Histogram.buckets(hist) == 1 ? 50 : 0;
      }
      case SmallStraight: {
        int buckets = Histogram.buckets(hist);
        if (buckets == 5) {
          return 30;
        }

        if (buckets == 4) {
          // Well isn't this something?
          boolean three = Histogram.countAt(hist, 3) != 0;
          boolean four = Histogram.countAt(hist, 4) != 0;
          if (three && four) {
            boolean one = Histogram.countAt(hist, 1) != 0;
            boolean two = Histogram.countAt(hist, 2) != 0;
            if (one && two) {
              return 30;
            }
            boolean five = Histogram.countAt(hist, 5) != 0;
            if (two && five) {
              return 30;
            }
            boolean six = Histogram.countAt(hist, 6) != 0;
            if (five && six)
              return 30;
          }
        }
        return 0;
      }
      case LargeStraight: {
        int buckets = Histogram.buckets(hist);
        if (buckets == 5) {
          return 40;
        }
        return 0;
      }
      case Chance: {
        return Histogram.sum(hist);
      }
    }
    throw new RuntimeException(this.toString());
  }

  public boolean isTop() {
    switch (this) {
      case Ones:
      case Twos:
      case Threes:
      case Fours:
      case Fives:
      case Sixes:
        return true;
    }
    return false;
  }
}
