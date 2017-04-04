package com.jomofisher.yahtzee;

public class Split {
  final public int keep;
  final public int reroll;

  Split(int keep, int reroll) {
    this.keep = keep;
    this.reroll = reroll;
    Roll.check(Roll.asString(keep));
    Roll.check(Roll.asString(reroll));
  }

  @Override
  public String toString() {
    return Roll.asString(keep) + ":" + Roll.asString(reroll);
  }
}
