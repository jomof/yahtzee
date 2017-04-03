package com.jomofisher.yahtzee;

import java.util.Map;

/**
 * Created by jomof on 4/3/2017.
 */
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

}
