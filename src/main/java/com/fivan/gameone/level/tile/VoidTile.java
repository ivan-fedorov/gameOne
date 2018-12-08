package com.fivan.gameone.level.tile;

import com.fivan.gameone.graphics.Screen;
import com.fivan.gameone.graphics.Sprite;

public class VoidTile extends Tile {

  public VoidTile(Sprite sprite) {
    super(sprite);
  }

  public void render(int x, int y, Screen screen) {
    screen.renderTile(x, y, this);
  }
}
