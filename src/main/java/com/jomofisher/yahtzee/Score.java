package com.jomofisher.yahtzee;

public class Score {
  private int count = 0;
  private long score = -1;
  final private boolean has [] = new boolean[13]; 
  final private long slots[] = new long[13];

  void put(Slot slot, long value) {
    int ordinal = slot.ordinal();
    assert(!has[ordinal]);
    slots[ordinal] = value;
    has[ordinal] = true;
    ++count;
    if (!slot.isTop() && score != -1) {
      score += value;
    } else {
      score = -1;
    }
  }

  void remove(Slot slot) {
    int ordinal = slot.ordinal();
    assert(has[ordinal]);
    if (!slot.isTop() && score != -1) {
      score -= slots[ordinal];
    } else {
      score = -1;
    }
    --count;
    slots[ordinal] = 0;
    has[ordinal] = false;
  }

  long score() {
    if (score != -1) {
      return score;
    }
    long top = 0;
    long bottom = 0;
    for (Slot slot : Slot.values()) {
      final int ordinal = slot.ordinal();
      if (!has[ordinal]) {
        continue;
      }
      long value = slots[ordinal];
      if (slot.isTop()) {
        top += value;
      } else {
        bottom += value;
      }
    }
    if (top > 63) {
      top += 35;
    }
    score = top + bottom;
    return score;
  }

  public int remaining() {
    return slots.length - count;
  }

  public boolean contains(Slot slot) {
    return has[slot.ordinal()];
  }

  public boolean contains(int n) {
    return has[n];
  }
}
