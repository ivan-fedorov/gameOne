package com.fivan.gameone.graphics;

import java.util.Random;

/**
 * Represents image for a game screen.
 */
public class Screen {

  public int[] pixels;
  private final int MAP_SIZE = 64;
  private int[] tiles = new int[MAP_SIZE * MAP_SIZE];

  private int width, height;
  private Random random = new Random();

  /**
   * Screen generation with randomly colored tiles.
   *
   * @param width width
   * @param height height
   */
  public Screen(int width, int height) {
    this.width = width;
    this.height = height;
    pixels = new int[width * height];

    for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
      tiles[i] = random.nextInt(0xffffff);
    }
    tiles[0] = 0;
  }

  /**
   * Renders shifted screen by x and y coordinates.
   *
   * @param xOffset x coordinate
   * @param yOffset y coordinate
   */
  public void render(int xOffset, int yOffset) {
    for (int y = 0; y < height; y++) {
      int yp = y + yOffset;
      if (yp < 0 || yp  >= height) { continue; }
      for (int x = 0; x < width; x++) {
        int xp = x + xOffset;
        if (xp < 0 || xp  >= width) { continue; }
        pixels[xp + yp * width] = Sprite.grass.pixels[(x & 15) + (y & 15) * Sprite.grass.size];
      }
    }
  }

  /**
   * Clears screen.
   */
  public void clear() {
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = 0;
    }
  }


}
