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

  public void record(Map<Slot, Long> slots, Long value) {
    assert (slots.get(value) == null);
    assert (isValidValue(value));
    slots.put(this, value);
  }

  private boolean isValidValue(Long value) {
    switch (this) {
      case Ones:
        return isMultiple(value, 1);
      case Twos:
        return isMultiple(value, 2);
      case Threes:
        return isMultiple(value, 3);
      case Fours:
        return isMultiple(value, 4);
      case Fives:
        return isMultiple(value, 5);
      case Sixes:
        return isMultiple(value, 6);
      case ThreeOfKind:
        return value >= 5; // Five ones is lowest 3-of-kind
      case FourOfKind:
        return value >= 5; // Five ones is lowest 4-of-kind
      case FullHouse:
        return value >= 5; // Five ones is lowest full house
      case SmallStraight:
        return value == 30;
      case LargeStraight:
        return value == 40;
      case Chance:
        return value >= 5; // Five ones is lowest chance
      case Yahtzee:
        return value >= 5; // Five ones is lowest yahtzee
    }
    throw new RuntimeException("Unrecognized");
  }

  public long points(String roll) {
    switch (this) {
      case Ones:
        return Roll.count(roll, 1);
      case Twos:
        return Roll.count(roll, 2) * 2;
      case Threes:
        return Roll.count(roll, 3) * 3;
      case Fours:
        return Roll.count(roll, 4) * 4;
      case Fives:
        return Roll.count(roll, 5) * 5;
      case Sixes:
        return Roll.count(roll, 6) * 6;
      case ThreeOfKind: {
        long result = 0;
        for (int i = 1; i < 7; ++i) {
          long count = Roll.count(roll, i);
          if (count >= 3) {
            result = Math.max(result, count * i);
          }
        }
        return result;
      }
      case FourOfKind: {
        long result = 0;
        for (int i = 1; i < 7; ++i) {
          long count = Roll.count(roll, i);
          if (count >= 4) {
            result = Math.max(result, count * i);
          }
        }
        return result;
      }
      case FullHouse: {
        Map<Long, Long> hist = Roll.histogram(roll);
        if (hist.size() == 1) {
          return 25;
        }
        if (hist.size() != 2) {
          return 0;
        }
        long count = hist.values().iterator().next();
        if (count == 2 || count == 3) {
          return 25;
        }
        return 0;
      }
      case Yahtzee: {
        long result = 0;
        for (int i = 1; i < 7; ++i) {
          long count = Roll.count(roll, i);
          if (count == 6) {
            result = Math.max(result, count * i);
          }
        }
        return result != 0 ? 50 : 0;
      }
      case SmallStraight: {
        Map<Long, Long> hist = Roll.histogram(roll);
        if (hist.size() == 4 || hist.size() == 5) {
          return 30;
        }
        return 0;
      }
      case LargeStraight: {
        Map<Long, Long> hist = Roll.histogram(roll);
        if (hist.size() == 5) {
          return 40;
        }
        return 0;
      }
      case Chance: {
        return Roll.sum(roll);
      }
    }
    throw new RuntimeException(this.toString());
  }
}
