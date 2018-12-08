package com.fivan.gameone.graphics;

import com.fivan.gameone.level.tile.Tile;

import java.util.Random;

/**
 * Represents image for a game screen.
 */
public class Screen {

  public int[] pixels;
  private final int MAP_SIZE = 64;
  private int[] tiles = new int[MAP_SIZE * MAP_SIZE];

  public int width, height;
  private Random random = new Random();

  /**
   * Screen generation with randomly colored tiles.
   *
   * @param width  width
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
      if (yp < 0 || yp >= height) { continue; }
      for (int x = 0; x < width; x++) {
        int xp = x + xOffset;
        if (xp < 0 || xp >= width) { continue; }
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

  /**
   * Method for rendering a {@link Tile}.
   *
   * @param xp   x position
   * @param yp   y position
   * @param tile {@link Tile} object
   */
  public void renderTile(int xp, int yp, Tile tile) {
    for (int y = 0; y < tile.sprite.size; y++) {
      int ya = y + yp;

      for (int x = 0; x < tile.sprite.size; x++) {
        int xa = x + xp;
        if (xa < 0 || xa >= width || ya < 0 || ya >= height) { break; }
        pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.size];
      }
    }
  }

}
