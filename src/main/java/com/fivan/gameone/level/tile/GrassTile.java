package com.fivan.gameone.level.tile;

import com.fivan.gameone.graphics.Screen;
import com.fivan.gameone.graphics.Sprite;

/**
 * Tile for grass.
 */
public class GrassTile extends Tile {

  public GrassTile(Sprite sprite) {
    super(sprite);
  }

  public void render(int x, int y, Screen screen) {
    screen.renderTile(x << 4, y << 4, this);
  }
}