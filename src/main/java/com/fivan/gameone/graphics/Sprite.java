package com.fivan.gameone.graphics;

public class Sprite {

  public int[] pixels;
  private final int size;
  private int x, y;
  private SpriteSheet sheet;

  public static Sprite grass = new Sprite(16, 0, 0, null);

  private Sprite(int size, int x, int y, SpriteSheet sheet) {
    this.size = size;
    this.x = x * size;
    this.y = y * size;
    this.sheet = sheet;
    load();
  }

  private void load() {
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        pixels[x + y * size] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.size];
      }
    }
  }
}
