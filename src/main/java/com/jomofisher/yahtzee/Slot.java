package com.jomofisher.yahtzee;

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
  final static Slot[] values = Slot.values();
  static final int slotCount = Slot.values().length;

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
          boolean one = Histogram.countAt(hist, 1) != 0;
          boolean two = Histogram.countAt(hist, 2) != 0;
          boolean three = Histogram.countAt(hist, 3) != 0;
          boolean four = Histogram.countAt(hist, 4) != 0;
          boolean five = Histogram.countAt(hist, 5) != 0;
          boolean six = Histogram.countAt(hist, 6) != 0;
          if (one && two && three && four && five) {
            return 30;
          }
          if (two && three && four && five && six) {
            return 30;
          }
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
          // Well isn't this something?
          boolean one = Histogram.countAt(hist, 1) != 0;
          boolean two = Histogram.countAt(hist, 2) != 0;
          boolean three = Histogram.countAt(hist, 3) != 0;
          boolean four = Histogram.countAt(hist, 4) != 0;
          boolean five = Histogram.countAt(hist, 5) != 0;
          boolean six = Histogram.countAt(hist, 6) != 0;
          if (one && two && three && four && five) {
            return 40;
          }
          if (two && three && four && five && six) {
            return 40;
          }
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
