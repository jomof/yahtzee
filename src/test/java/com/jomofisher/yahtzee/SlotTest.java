package com.jomofisher.yahtzee;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class SlotTest {
  @Test
  public void points() {
    assertThat(Slot.Ones.points(hist("12134"))).isEqualTo(2);
    assertThat(Slot.Twos.points(hist("12224"))).isEqualTo(6);
  }

  @Test
  public void threeOfKind() {
    assertThat(Slot.ThreeOfKind.points(hist("12134"))).isEqualTo(0);
    assertThat(Slot.ThreeOfKind.points(hist("12131"))).isEqualTo(8);
    assertThat(Slot.ThreeOfKind.points(hist("22232"))).isEqualTo(11);
  }

  @Test
  public void fourOfAKind() {
    assertThat(Slot.FourOfKind.points(hist("12131"))).isEqualTo(0);
    assertThat(Slot.FourOfKind.points(hist("12134"))).isEqualTo(0);
    assertThat(Slot.FourOfKind.points(hist("22232"))).isEqualTo(11);
  }

  @Test
  public void yahtzee() {
    assertThat(Slot.Yahtzee.points(hist("12131"))).isEqualTo(0);
    assertThat(Slot.Yahtzee.points(hist("12134"))).isEqualTo(0);
    assertThat(Slot.Yahtzee.points(hist("22232"))).isEqualTo(0);
    assertThat(Slot.Yahtzee.points(hist("22222"))).isEqualTo(50);
  }

  @Test
  public void fullHouse() {
    assertThat(Slot.FullHouse.points(hist("12134"))).isEqualTo(0);
    assertThat(Slot.FullHouse.points(hist("12131"))).isEqualTo(0);
    assertThat(Slot.FullHouse.points(hist("22232"))).isEqualTo(0);
    assertThat(Slot.FullHouse.points(hist("22332"))).isEqualTo(25);
    assertThat(Slot.FullHouse.points(hist("22222"))).isEqualTo(25);
  }

  @Test
  public void smallStraight() {
    assertThat(Slot.SmallStraight.points(hist("66245"))).isEqualTo(0);
    assertThat(Slot.SmallStraight.points(hist("66312"))).isEqualTo(0);
    assertThat(Slot.SmallStraight.points(hist("12134"))).isEqualTo(30);
    assertThat(Slot.SmallStraight.points(hist("54312"))).isEqualTo(30);
    assertThat(Slot.SmallStraight.points(hist("12344"))).isEqualTo(30);
    assertThat(Slot.SmallStraight.points(hist("12131"))).isEqualTo(0);
    assertThat(Slot.SmallStraight.points(hist("22232"))).isEqualTo(0);
    assertThat(Slot.SmallStraight.points(hist("22332"))).isEqualTo(0);
    assertThat(Slot.SmallStraight.points(hist("22222"))).isEqualTo(0);
  }

  @Test
  public void largeStraight() {
    assertThat(Slot.LargeStraight.points(hist("12134"))).isEqualTo(0);
    assertThat(Slot.LargeStraight.points(hist("54312"))).isEqualTo(40);
    assertThat(Slot.LargeStraight.points(hist("12345"))).isEqualTo(40);
    assertThat(Slot.LargeStraight.points(hist("12344"))).isEqualTo(0);
    assertThat(Slot.LargeStraight.points(hist("12131"))).isEqualTo(0);
    assertThat(Slot.LargeStraight.points(hist("22232"))).isEqualTo(0);
    assertThat(Slot.LargeStraight.points(hist("22332"))).isEqualTo(0);
    assertThat(Slot.LargeStraight.points(hist("22222"))).isEqualTo(0);
  }

  @Test
  public void chance() {
    assertThat(Slot.Chance.points(hist("12134"))).isEqualTo(11);
    assertThat(Slot.Chance.points(hist("54312"))).isEqualTo(15);
    assertThat(Slot.Chance.points(hist("12344"))).isEqualTo(14);
    assertThat(Slot.Chance.points(hist("12131"))).isEqualTo(8);
    assertThat(Slot.Chance.points(hist("22232"))).isEqualTo(11);
    assertThat(Slot.Chance.points(hist("22332"))).isEqualTo(12);
    assertThat(Slot.Chance.points(hist("22222"))).isEqualTo(10);
  }

  @Test
  public void all() {
    for (Slot slot : Slot.values()) {
      slot.points(hist("12134"));
    }
  }

  int hist(String s) {
    return Roll.histogram(Roll.fromString(s));
  }
}