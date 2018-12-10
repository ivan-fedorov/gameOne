package com.fivan.gameone.entity.mob;

import com.fivan.gameone.graphics.Screen;
import com.fivan.gameone.graphics.Sprite;
import com.fivan.gameone.input.Keyboard;

public class Player extends Mob {

  private Keyboard input;


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
    if (input.up) { ya--; }
    if (input.down) { ya++; }
    if (input.left) { xa--; }
    if (input.right) { xa++; }

    if (xa != 0 || ya != 0) { move(xa, ya); }
  }

  @Override
  public void render(Screen screen) {
    int flip = 0;
    switch (dir) {
      case 0: sprite = Sprite.playerForward; break;
      case 2: sprite = Sprite.playerBack; break;
      case 3: sprite = Sprite.playerSide; break;
      default: sprite = Sprite.playerSide; flip = 1;
    }
    screen.renderPlayer(x - 16, y - 16, sprite, flip);
  }
}
