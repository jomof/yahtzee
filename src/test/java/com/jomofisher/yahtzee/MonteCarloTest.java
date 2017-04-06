package com.jomofisher.yahtzee;

import javafx.util.Pair;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class MonteCarloTest {
  @Test
  public void simple() {
    double result = MonteCarlo.finishGreedy(new Score(), 7000);
    System.out.printf("%s\n", result);
  }

  @Test
  public void findBestPlaceForRoll() {
    Pair<Slot, Double> result = MonteCarlo.findBestPlaceForRoll(new Score(), Roll.fromString("66312"), 3000);
    //    assertThat(result.getKey()).isEqualTo(Slot.Sixes);
    System.out.printf("%s\n", result);
  }

  @Test
  public void findBestPlaceForRoll2() {
    Pair<Slot, Double> result = MonteCarlo.findBestPlaceForRoll(new Score(), Roll.fromString("12345"), 2000);
    assertThat(result.getKey()).isEqualTo(Slot.LargeStraight);
    System.out.printf("%s\n", result);
  }

  @Test
  public void tryit() {
    Move result = MonteCarlo.findBest(new Score(), Roll.fromString("66245"), true);
    System.out.printf("%s\n", result);
  }
}