package com.jomofisher.yahtzee;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by jomof on 4/3/2017.
 */
public class SlotTest {
  @Test
  public void points() {
    assertThat(Slot.Ones.points("12134")).isEqualTo(2);
    assertThat(Slot.Twos.points("12224")).isEqualTo(6);
  }

  @Test
  public void threeOfKind() {
    assertThat(Slot.ThreeOfKind.points("12134")).isEqualTo(0);
    assertThat(Slot.ThreeOfKind.points("12131")).isEqualTo(3);
    assertThat(Slot.ThreeOfKind.points("22232")).isEqualTo(8);
  }

  @Test
  public void fullHouse() {
    assertThat(Slot.FullHouse.points("12134")).isEqualTo(0);
    assertThat(Slot.FullHouse.points("12131")).isEqualTo(0);
    assertThat(Slot.FullHouse.points("22232")).isEqualTo(0);
    assertThat(Slot.FullHouse.points("22332")).isEqualTo(25);
    assertThat(Slot.FullHouse.points("22222")).isEqualTo(25);
  }

  @Test
  public void smallStraight() {
    assertThat(Slot.SmallStraight.points("12134")).isEqualTo(30);
    assertThat(Slot.SmallStraight.points("54312")).isEqualTo(30);
    assertThat(Slot.SmallStraight.points("12344")).isEqualTo(30);
    assertThat(Slot.SmallStraight.points("12131")).isEqualTo(0);
    assertThat(Slot.SmallStraight.points("22232")).isEqualTo(0);
    assertThat(Slot.SmallStraight.points("22332")).isEqualTo(0);
    assertThat(Slot.SmallStraight.points("22222")).isEqualTo(0);
  }

  @Test
  public void largeStraight() {
    assertThat(Slot.LargeStraight.points("12134")).isEqualTo(0);
    assertThat(Slot.LargeStraight.points("54312")).isEqualTo(40);
    assertThat(Slot.LargeStraight.points("12344")).isEqualTo(0);
    assertThat(Slot.LargeStraight.points("12131")).isEqualTo(0);
    assertThat(Slot.LargeStraight.points("22232")).isEqualTo(0);
    assertThat(Slot.LargeStraight.points("22332")).isEqualTo(0);
    assertThat(Slot.LargeStraight.points("22222")).isEqualTo(0);
  }

  @Test
  public void chance() {
    assertThat(Slot.Chance.points("12134")).isEqualTo(11);
    assertThat(Slot.Chance.points("54312")).isEqualTo(15);
    assertThat(Slot.Chance.points("12344")).isEqualTo(14);
    assertThat(Slot.Chance.points("12131")).isEqualTo(8);
    assertThat(Slot.Chance.points("22232")).isEqualTo(11);
    assertThat(Slot.Chance.points("22332")).isEqualTo(12);
    assertThat(Slot.Chance.points("22222")).isEqualTo(10);
  }

  @Test
  public void all() {
    for (Slot slot : Slot.values()) {
      slot.points("12134");
    }
  }
}