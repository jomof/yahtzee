package com.jomofisher.yahtzee;

import java.util.HashMap;
import java.util.Map;

public class Yahtzee {
  final private Map<Slot, Long> slots = new HashMap<>();

  Yahtzee with(Slot slot, long value) {
    slot.record(slots, value);
    return this;
  }

  Yahtzee withOnes(long value) {
    return with(Slot.Ones, value);
  }

  Yahtzee withTwos(long value) {
    return with(Slot.Twos, value);
  }

  Yahtzee withThrees(long value) {
    return with(Slot.Threes, value);
  }

  Yahtzee withFours(long value) {
    return with(Slot.Fours, value);
  }

  Yahtzee withFives(long value) {
    return with(Slot.Fives, value);
  }

  Yahtzee withSixes(long value) {
    return with(Slot.Sixes, value);
  }
}
