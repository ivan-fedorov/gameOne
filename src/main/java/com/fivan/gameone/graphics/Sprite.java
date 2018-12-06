package com.fivan.gameone.graphics;

public class Sprite {

  public final int size;
  public int[] pixels;
  private int x, y;
  private SpriteSheet sheet;

  public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);

  private Sprite(int SIZE, int x, int y, SpriteSheet sheet) {
    this.size = SIZE;
    this.x = x * SIZE;
    this.y = y * SIZE;
    this.sheet = sheet;
    this.pixels = new int[size * size];
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
