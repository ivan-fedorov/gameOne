package com.fivan.gameone.entity;

import com.fivan.gameone.graphics.Screen;
import com.fivan.gameone.level.Level;

import java.util.Random;

public class Entity {

  /**
   * Coordinates.
   */
  public int x, y;

  /**
   * Is entity removed.
   */
  private boolean removed;

  /**
   * Entity {@link Level}
   */
  protected Level level;

  /**
   * Random generator.
   */
  protected final Random random = new Random();

  public void update() {
  }

  public void render(Screen screen) {

  }

  /**
   * Remove from level.
   */
  public void remove() {
    removed = true;
  }

  public boolean isRemoved() {
    return removed;
  }
}
