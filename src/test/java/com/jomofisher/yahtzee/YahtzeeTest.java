package com.jomofisher.yahtzee;

import org.junit.Test;

import static org.junit.Assert.fail;

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
  public void play() {
    Score score = new Yahtzee()
        .with(Slot.FourOfKind, 24)
        .with(Slot.SmallStraight, 30)
        .with(Slot.FullHouse, 25)
        .with(Slot.ThreeOfKind, 23)
        .with(Slot.Ones, 3)
        .with(Slot.Yahtzee, 0)
        .with(Slot.Chance, 22)
        .with(Slot.Fives, 15)
        .with(Slot.Sixes, 18)
        .score();

    Move next = null;
    int rerollsRemaining = 2;
    int reroll = 0;

    while (score.remaining() > 0) {
      if (next == null) {
        reroll = Roll.randomInt();
        System.out.printf("Rolled %s\n", Roll.asString(reroll));
        next = MonteCarlo.findBest(score, reroll, true);
        continue;
      }
      if (next.split != null) {
        reroll = Roll.reroll(next.split.keep);
        System.out.printf("Rolled %s and got %s\n", Roll.asString(next.split.keep), Roll.asString(reroll));
        next = MonteCarlo.findBest(score, reroll, rerollsRemaining > 1);

        --rerollsRemaining;
        continue;
      }
      if (next.slot != null) {
        int hist = Roll.histogram(reroll);
        long points = next.slot.points(hist);

        score.put(next.slot, points);

        if (points == 0) {
          System.out.printf("// hist %s points %s\n", hist, points);
          System.out.printf("assertThat(Slot.%s.points(Roll.histogram(%s))).isEqualTo(%s);\n", next.slot, reroll, points);
        }

        System.out.printf("Take %s for %s points. Total: %s\n", next.slot, points, score.score());
        reroll = -1;
        next = null;
        rerollsRemaining = 2;
      }
    }

  }

  @Test
  public void invalid() {
    try {
      Yahtzee yahtzee = new Yahtzee()
          .withFives(4);
      fail();
    } catch (AssertionError ignored) {

    }
  }
}