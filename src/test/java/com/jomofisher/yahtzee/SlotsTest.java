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
  public void assignBest2() {
    Map<Slot, Long> slots = new HashMap<>();
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assertThat(Slots.score(slots)).isEqualTo(80);
  }

  @Test
  public void assignBest3() {
    Map<Slot, Long> slots = new HashMap<>();
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assertThat(Slots.score(slots)).isEqualTo(110);
  }

  @Test
  public void assignBest4() {
    Map<Slot, Long> slots = new HashMap<>();
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assertThat(Slots.score(slots)).isEqualTo(140);
  }

  @Test
  public void assignBest5() {
    Map<Slot, Long> slots = new HashMap<>();
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assertThat(Slots.score(slots)).isEqualTo(170);
  }

  @Test
  public void assignBest6() {
    Map<Slot, Long> slots = new HashMap<>();
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assertThat(Slots.score(slots)).isEqualTo(195);
  }

  @Test
  public void assignBest7() {
    Map<Slot, Long> slots = new HashMap<>();
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66666");
    assignGreedy(slots, "66665"); // Fives
    assertThat(Slots.score(slots)).isEqualTo(200);
  }

  private void assignGreedy(Map<Slot, Long> slots, String roll) {
    Slot greediest = Slots.greediest(slots, Roll.fromString(roll));
    long points = greediest.points(Roll.histogram(Roll.fromString(roll)));
    slots.put(greediest, points);
  }
}