package com.jomofisher.yahtzee;

import org.junit.Test;

import static org.junit.Assert.*;

public class YahtzeeTest {
  @Test
  public void simple() {
    Yahtzee yahtzee = new Yahtzee()
        .withOnes(5)
        .withTwos(8)
        .withThrees(9)
        .withFours(12)
        .withFives(15)
        .withSixes(18);

  }

  @Test
  public void invalid() {
    try {
      Yahtzee yahtzee = new Yahtzee()
          .withFives(4);
      fail();
    } catch (AssertionError e) {

    }
  }
}