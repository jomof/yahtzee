package com.jomofisher.yahtzee;

public class Score {
  private int count = 0;
  private Long score = null;
  final private boolean has [] = new boolean[13]; 
  final private long slots[] = new long[13];

  void put(Slot slot, long value) {
    int ordinal = slot.ordinal();
    assert(!has[ordinal]);
    slots[ordinal] = value;
    has[ordinal] = true;
    ++count;
    if (!slot.isTop() && score != null) {
      score += value;
    } else {
      score = null;
    }
  }

  void remove(Slot slot) {
    int ordinal = slot.ordinal();
    assert(has[ordinal]);
    if (!slot.isTop() && score != null) {
      score -= slots[ordinal];
    } else {
      score = null;
    }
    --count;
    slots[ordinal] = 0;
    has[ordinal] = false;
  }

  long score() {
    if (score != null) {
      return score;
    }
    long top = 0;
    long bottom = 0;
    for (Slot slot : Slot.values()) {
      Long value = slots[slot.ordinal()];
      if (value == null) {
        continue;
      }
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
}
