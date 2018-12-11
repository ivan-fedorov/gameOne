package com.fivan.gameone.level.tile;

import com.fivan.gameone.graphics.Screen;
import com.fivan.gameone.graphics.Sprite;

public class FlowerTile extends Tile {

  public FlowerTile(Sprite sprite) {super(sprite);}

  @Override
  public void render(int x, int y, Screen screen) {
    screen.renderTile(x << 4, y << 4, this);
  }
}
