package com.jomofisher.yahtzee;

public class Yahtzee {
  final private Score score = new Score();

  Yahtzee with(Slot slot, long value) {
    score.put(slot, value);
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
