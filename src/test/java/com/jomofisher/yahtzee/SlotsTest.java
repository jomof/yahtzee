package com.jomofisher.yahtzee;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

public class SlotsTest {
  @Test
  public void simple() {
    Map<Slot, Long> slots = new HashMap<>();
    slots.put(Slot.Ones, 6L);
    assertThat(Slots.score(slots)).isEqualTo(6);
  }

  @Test
  public void topOver() {
    Map<Slot, Long> slots = new HashMap<>();
    slots.put(Slot.Ones, 4L);
    slots.put(Slot.Twos, 6L);
    slots.put(Slot.Threes, 9L);
    slots.put(Slot.Fours, 12L);
    slots.put(Slot.Fives, 15L);
    slots.put(Slot.Sixes, 18L);
    assertThat(Slots.score(slots)).isEqualTo(99);
  }

  @Test
  public void assignBest7() {
    Score score = new Score();
    assignGreedy(score, "66666", Slot.Yahtzee, 50);
    assignGreedy(score, "66666", Slot.Sixes, 80);
    assignGreedy(score, "66666", Slot.ThreeOfKind, 110);
    assignGreedy(score, "66666", Slot.FourOfKind, 140);
    assignGreedy(score, "66666", Slot.Chance, 170);
    assignGreedy(score, "66666", Slot.FullHouse, 195);
    assignGreedy(score, "66665", Slot.Fives, 200); // Fives
    assertThat(score.score()).isEqualTo(200);
  }

  private void assignGreedy(Score score, String roll, Slot expected, long scoreExpected) {
    int roll1 = Roll.fromString(roll);
    Slot greediest = Slots.greediest(score, roll1);
    assertThat(greediest).isEqualTo(expected);
    long points = greediest.points(Roll.histogram(roll1));
    score.put(greediest, points);
    assertThat(score.score()).isEqualTo(scoreExpected);
  }
}