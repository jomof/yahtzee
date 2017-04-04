package com.jomofisher.yahtzee;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

class MonteCarlo {
  public static double finishGreedy(Map<Slot, Long> slots, int n) {
    double sum = 0;
    for (int i = 0; i < n; ++i) {
      Map<Slot, Long> copy = new HashMap<>(slots);
      finishGreedyInPlace(copy);
      sum += Slots.score(copy);
    }
    return (sum / n);
  }

  private static void finishGreedyInPlace(Map<Slot, Long> slots) {
    if (Slots.remaining(slots) == 0) {
      return;
    }
    long best = -1;
    Slot bestSlot = null;
    for (int i = 0; i < 3; ++i) {
      int roll = Roll.randomInt();
      int hist = Roll.histogram(roll);
      Slot slot = Slots.greediest(slots, roll);
      long points = slot.points(hist);
      if (points > best) {
        best = points;
        bestSlot = slot;
      }
    }
    slots.put(bestSlot, best);
    finishGreedyInPlace(slots);
  }

  static Pair<Slot, Double> findBestPlaceForRoll(Map<Slot, Long> slots, int roll, int n) {
    double bestScore = -1;
    Slot bestSlot = null;
    int hist = Roll.histogram(roll);
    for (Slot slot : Slot.values()) {
      if (!slots.containsKey(slot)) {
        // Slot is open, place the roll there and see what the score looks like
        long points = slot.points(hist);
        slots.put(slot, points);
        double score = finishGreedy(slots, n);
        if (score > bestScore) {
          bestScore = score;
          bestSlot = slot;
        }
        slots.remove(slot);
      }
    }
    return new Pair<>(bestSlot, bestScore);
  }

  public static Move findBest(Map<Slot, Long> slots, int roll, boolean allowReroll) {
    // Loop over all possible placements
    Pair<Slot, Double> result = findBestPlaceForRoll(slots, roll, 200);
    Move bestMove = new Move(result.getKey());
    double bestScore = result.getValue();

    // Loop over the possible splits
    if (allowReroll) {
      for (Split split : Roll.splits(roll)) {
        // Reroll that split many times
        double sum = 0;
        long count = 25;
        for (int i = 0; i < count; ++i) {
          int reroll = Roll.reroll(split.keep);
          sum += findBestPlaceForRoll(slots, reroll, 200).getValue();
        }
        double mean = sum / count;
        if (mean > bestScore) {
          bestScore = mean;
          bestMove = new Move(split);
        }
      }
    }

    return bestMove;
  }
}
