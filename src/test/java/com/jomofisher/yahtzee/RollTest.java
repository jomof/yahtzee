package com.jomofisher.yahtzee;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class RollTest {
  @Test
  public void simple() {
    assertThat(Roll.sort("12345")).isEqualTo("12345");
  }

  @Test
  public void reverse() {
    assertThat(Roll.sort("54321")).isEqualTo("12345");
  }
}