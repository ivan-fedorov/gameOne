package com.fivan.gameone.graphics;

/**
 * Game sprite class.
 */
public class Sprite {

  /**
   * Size of sprite.
   */
  public final int size;

  /**
   * An array with all pixels of current sprite.
   */
  public int[] pixels;
  private int x, y;
  private SpriteSheet sheet;

  public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
  public static Sprite voidSprite = new Sprite(16, 0);

  private Sprite(int size, int x, int y, SpriteSheet sheet) {
    this.size = size;
    this.x = x * size;
    this.y = y * size;
    this.sheet = sheet;
    this.pixels = new int[this.size * this.size];
    load();
  }

  private Sprite(int size, int color) {
    this.size = size;
    pixels = new int[size * size];
    setColor(color);
  }

  private void setColor(int color) {
    for (int i = 0; i < size * size; i++) {
      pixels[i] = color;
    }
  }

  private void load() {
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        pixels[x + y * size] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.size];
      }
    }
  }
}
