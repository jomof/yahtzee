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

  static Slot greediest(Map<Slot, Long> slots, int roll) {
    long bestScore = -1;
    Slot bestMove = null;
    int hist = Roll.histogram(roll);
    for (Slot slot : Slot.values()) {
      if (!slots.containsKey(slot)) {
        long points = slot.points(hist);
        slots.put(slot, points);
        long score = score(slots);
        if (score > bestScore) {
          bestScore = score;
          bestMove = slot;
        }
        slots.remove(slot);
      }
    }
    return bestMove;
  }
}
