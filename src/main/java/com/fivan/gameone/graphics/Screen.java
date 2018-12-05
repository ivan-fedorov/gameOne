package com.fivan.gameone.graphics;


import java.util.Random;

public class Screen {

  public int[] pixels;
  private int[] tiles = new int[4096]; // 64 * 64

  private int width, height;
  private Random random = new Random();

  public Screen(int width, int height) {
    this.width = width;
    this.height = height;
    pixels = new int[width * height];

    for (int i = 0; i < 4096; i++) {
      tiles[i] = random.nextInt(0xffffff);
    }
  }

  public void render() {
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int tileIndex = (x >> 4) + (y >> 4) * 64;
        pixels[x + y * width] = tiles[tileIndex];
      }
    }
  }

  public void clear() {
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = 0;
    }
  }


}
