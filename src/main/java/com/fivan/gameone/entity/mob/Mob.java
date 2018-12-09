package com.fivan.gameone.entity.mob;

import com.fivan.gameone.entity.Entity;
import com.fivan.gameone.graphics.Sprite;

public abstract class Mob extends Entity {

  protected Sprite sprite;
  protected int dir;
  protected boolean moving;

  public void move(int xa, int ya) {
    if (xa > 0) { dir = 1; }
    if (xa < 0) { dir = 3; }
    if (ya > 0) { dir = 2; }
    if (ya < 0) { dir = 0; }

    if (!collision()) {
      x += xa;
      y += ya;
    }
  }

  @Override
  public void update() {}

  public void render() {}

  private boolean collision() {
    return false;
  }


}