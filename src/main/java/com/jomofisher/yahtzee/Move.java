package com.jomofisher.yahtzee;

class Move {
  public final Split split;
  public final Slot slot;

  Move(Split split) {
    this.split = split;
    this.slot = null;
  }

  Move(Slot slot) {
    this.slot = slot;
    this.split = null;
  }

  @Override
  public String toString() {
    if (split != null) {
      return split.toString();
    }
    if (slot != null) {
      return slot.toString();
    }
    return "unknown";
  }
}
