package com.jomofisher.yahtzee;

import java.util.Map;

class Slots {
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

  static Slot greediest(Score score, int hist) {
    long bestScore = -1;
    Slot bestMove = null;
    for (int i = 0; i < Slot.slotCount; ++i) {
      if (score.contains(i)) {
        continue;
      }
      Slot slot = Slot.values[i];
      long points = slot.points(hist);
      score.put(slot, points);
      long value = score.score();
      if (value > bestScore) {
        bestScore = value;
        bestMove = slot;
      }
      score.remove(slot);
    }
    return bestMove;
  }
}
