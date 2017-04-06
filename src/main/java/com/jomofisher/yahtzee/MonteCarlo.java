package com.jomofisher.yahtzee;

import javafx.util.Pair;

class MonteCarlo {
  public static double finishGreedy(Score score, int n) {
    double sum = 0;
    for (int i = 0; i < n; ++i) {
      sum += finishGreedyInPlace(score);
    }
    return (sum / n);
  }

  private static long finishGreedyInPlace(Score score) {
    if (score.remaining() == 0) {
      return score.score();
    }
    long best = -1;
    Slot bestSlot = null;
    for (int i = 0; i < 6; ++i) {
      int roll = Roll.randomInt();
      int hist = Roll.histogram(roll);
      Slot slot = Slots.greediest(score, hist);
      long points = slot.points(hist);
      if (points > best) {
        best = points;
        bestSlot = slot;
      }
    }
    score.put(bestSlot, best);
    long result = finishGreedyInPlace(score);
    score.remove(bestSlot);
    return result;
  }

  static Pair<Slot, Double> findBestPlaceForRoll(Score score, int roll, int n) {
    double bestScore = -1;
    Slot bestSlot = null;
    int hist = Roll.histogram(roll);
    for (Slot slot : Slot.values()) {
      if (!score.contains(slot)) {
        // Slot is open, place the roll there and see what the score looks like
        long points = slot.points(hist);
        score.put(slot, points);
        double value = finishGreedy(score, n);
        if (value > bestScore) {
          bestScore = value;
          bestSlot = slot;
        }
        score.remove(slot);
      }
    }
    return new Pair<>(bestSlot, bestScore);
  }

  public static Move findBest(Score score, int roll, boolean allowReroll) {
    // Loop over all possible placements
    Pair<Slot, Double> result = findBestPlaceForRoll(score, roll, 2000);
    Move bestMove = new Move(result.getKey());
    double bestScore = result.getValue();

    // Loop over the possible splits
    if (allowReroll) {
      for (Split split : Roll.splits(roll)) {
        // Reroll that split many times
        double sum = 0;
        long count = 30;
        for (int i = 0; i < count; ++i) {
          int reroll = Roll.reroll(split.keep);
          sum += findBestPlaceForRoll(score, reroll, 100).getValue();
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
