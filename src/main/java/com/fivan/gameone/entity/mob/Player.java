package com.fivan.gameone.entity.mob;

import com.fivan.gameone.graphics.Screen;
import com.fivan.gameone.graphics.Sprite;
import com.fivan.gameone.input.Keyboard;

public class Player extends Mob {

  private Keyboard input;
  private int anim;
  private boolean walking;

  public Player(Keyboard input) {
    this.input = input;
    sprite = Sprite.playerForward;
  }

  public Player(int x, int y, Keyboard input) {
    this.x = x;
    this.y = y;
    this.input = input;
  }

  @Override
  public void update() {
    int xa = 0, ya = 0;
    anim = anim < 7500 ? anim + 1 : 0;
    if (input.up) { ya--; }
    if (input.down) { ya++; }
    if (input.left) { xa--; }
    if (input.right) { xa++; }

    if (xa != 0 || ya != 0) {
      move(xa, ya);
      walking = true;
    } else { walking = false; }
  }

  @Override
  public void render(Screen screen) {
    int flip = 0;
    if (dir == 0) {
      sprite = Sprite.playerForward;
      if (walking) {
        if (anim % 20 > 10) {
          sprite = Sprite.playerForward1;
        }
        else { sprite = Sprite.playerForward2; }
      }
    } else if (dir == 2) {
      sprite = Sprite.playerBack;
      if (walking) {
        if (anim % 20 > 10) {
          sprite = Sprite.playerBack1;
        }
        else { sprite = Sprite.playerBack2; }
      }
    } else if (dir == 3) {
      sprite = Sprite.playerSide;
      if (walking) {
        if (anim % 20 > 10) {
          sprite = Sprite.playerSide1;
        }
        else { sprite = Sprite.playerSide2; }
      }
    } else {
      sprite = Sprite.playerSide;
      if (walking) {
        if (anim % 20 > 10) {
          sprite = Sprite.playerSide1;
        }
        else { sprite = Sprite.playerSide2; }
      }
      flip = 1;
    }
    screen.renderPlayer(x - 16, y - 16, sprite, flip);
  }
}
