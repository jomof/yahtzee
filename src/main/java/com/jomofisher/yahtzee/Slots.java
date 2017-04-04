package com.jomofisher.yahtzee;

import java.util.Map;

class Slots {

  static long remaining(Map<Slot, Long> slots) {
    return Slot.values().length - slots.values().size();
  }

  static long score(Map<Slot, Long> slots) {
    long top = 0;
    long bottom = 0;
    for (Slot slot : slots.keySet()) {
      if (slot.isTop()) {
        if (slots.get(slot) == null) {
          throw new RuntimeException();
        }
        top += slots.get(slot);
      } else {
        bottom += slots.get(slot);
      }
    }
    if (top > 63) {
      top += 35;
    }
    return top + bottom;
  }

  static Slot greediest(Score score, int roll) {
    long bestScore = -1;
    Slot bestMove = null;
    int hist = Roll.histogram(roll);
    for (Slot slot : Slot.values()) {
      if (!score.contains(slot)) {
        long points = slot.points(hist);
        score.put(slot, points);
        long value = score.score();
        if (value > bestScore) {
          bestScore = value;
          bestMove = slot;
        }
        score.remove(slot);
      }
    }
    return bestMove;
  }
}
