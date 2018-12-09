package com.fivan.gameone.entity.mob;

import com.fivan.gameone.entity.Entity;
import com.fivan.gameone.graphics.Sprite;

public abstract class Mob extends Entity {

  protected Sprite sprite;
  protected int dir;
  protected boolean moving;

  public void move() {}

  public void update() {}

  private boolean collision() {
    return false;
  }

}