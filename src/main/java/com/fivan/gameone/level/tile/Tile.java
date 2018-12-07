package com.fivan.gameone.level.tile;

import com.fivan.gameone.graphics.Screen;
import com.fivan.gameone.graphics.Sprite;

/**
 * Section, entity. block of a game, usually rendered with sprite.
 */
public class Tile {

  /**
   * Coordinates on screen.
   */
  public int x,y;

  /**
   * {@link Sprite} object for tile.
   */
  public Sprite sprite;

  public Tile(Sprite sprite) {
    this.sprite = sprite;
  }

  public void render(int x, int y, Screen screen) {
  }

  /**
   * Can current tile collide or it can be walk through.
   *
   * @return is solid object or not
   */
  public boolean solid() {
    return false;
  }
}
