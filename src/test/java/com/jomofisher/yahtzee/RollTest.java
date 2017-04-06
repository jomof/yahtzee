package com.jomofisher.yahtzee;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class RollTest {

  @Test
  public void fromString() {
    int roll = Roll.fromString("62345");
    assertThat(Roll.asString(roll)).isEqualTo("62345");
  }

  @Test
  public void count() {
    int roll = Roll.fromString("62346");
    assertThat(Roll.count(roll, 6)).isEqualTo(2);
    assertThat(Roll.count(roll, 2)).isEqualTo(1);
  }

  @Test
  public void histogram() {
    assertThat(Histogram.buckets(Roll.histogram(Roll.fromString("62346")))).isEqualTo(4);
    assertThat(Histogram.buckets(Roll.histogram(Roll.fromString("62366")))).isEqualTo(3);
    assertThat(Histogram.buckets(Roll.histogram(Roll.fromString("66366")))).isEqualTo(2);
  }

  @Test
  public void splits() {
    Split[] splits = Roll.splits(Roll.fromString("62346"));
  }


  @Test
  public void asString() {
    assertThat(Roll.asString(0)).isEqualTo("xxxxx");
    assertThat(Roll.asString(1)).isEqualTo("xxxx1");
    assertThat(Roll.asString(1 << 3)).isEqualTo("xxx1x");
    assertThat(Roll.asString(1 << 6)).isEqualTo("xx1xx");
  }

  @Test
  public void digitAt() {
    assertThat(Roll.getDigitAt(Roll.fromString("62346"), 0)).isEqualTo(6);
    assertThat(Roll.getDigitAt(Roll.fromString("62346"), 1)).isEqualTo(4);
    assertThat(Roll.getDigitAt(Roll.fromString("62346"), 3)).isEqualTo(2);
  }
}