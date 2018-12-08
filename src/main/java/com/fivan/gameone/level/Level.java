package com.fivan.gameone.level;

import com.fivan.gameone.graphics.Screen;
import com.fivan.gameone.level.tile.Tile;

/**
 * Basic template for levels.
 */
public abstract class Level {

  protected int width, height;
  protected int[] tiles;

  /**
   * Random level generator.
   *
   * @param width width
   * @param height height
   */
  public Level(int width, int height) {
    this.width = width;
    this.height = height;
    this.tiles = new int[width * height];
    generateLevel();
  }

  /**
   * Create level from path.
   *
   * @param path path to file
   */
  public Level(String path) {
    loadLevel(path);
  }

  /**
   * Update action in level, i.e. monsters, projectiles
   */
  public void update() {

  }

  /**
   * Renders shifted {@link Screen} by x,y coordinates.
   *
   * @param xScroll x coordinate
   * @param yScroll y coordinate
   * @param screen screen to render
   */
  public void render(int xScroll, int yScroll, Screen screen) {
    int x0 = xScroll >> 4;
    int x1 = (xScroll + screen.width) >> 4;
    int y0 = yScroll >> 4;
    int y1 = (yScroll + screen.height) >> 4;

  }

  public Tile getTile(int x, int y) {
    if (tiles[x + y * width] == 0) { return Tile.grass; }
    return Tile.voidTile;
  }

  protected void generateLevel() {

  }

  private void loadLevel(String path) {

  }

  private void time() {}



}
