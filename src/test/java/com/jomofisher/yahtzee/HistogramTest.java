package com.jomofisher.yahtzee;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class HistogramTest {
  @Test
  public void countAt() throws Exception {
    int hist = Roll.histogram(Roll.fromString("66123"));
    assertThat(Histogram.countAt(hist, 6)).isEqualTo(2);
    assertThat(Histogram.countAt(hist, 1)).isEqualTo(1);
    assertThat(Histogram.countAt(hist, 2)).isEqualTo(1);
    assertThat(Histogram.countAt(hist, 3)).isEqualTo(1);
    assertThat(Histogram.countAt(hist, 4)).isEqualTo(0);
    assertThat(Histogram.countAt(hist, 5)).isEqualTo(0);
  }

  @Test
  public void countAt2() throws Exception {
    int hist = Roll.histogram(Roll.fromString("66234"));
    assertThat(Histogram.countAt(hist, 1)).isEqualTo(0);
    assertThat(Histogram.countAt(hist, 2)).isEqualTo(1);
    assertThat(Histogram.countAt(hist, 3)).isEqualTo(1);
    assertThat(Histogram.countAt(hist, 4)).isEqualTo(1);
    assertThat(Histogram.countAt(hist, 5)).isEqualTo(0);
    assertThat(Histogram.countAt(hist, 6)).isEqualTo(2);
  }

  @Test
  public void testSum() {
    assertThat(sum("12345")).isEqualTo(15);
  }

  private long sum(String s) {
    int hist = Roll.histogram(Roll.fromString(s));
    return Histogram.sum(hist);
  }
}