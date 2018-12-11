package com.fivan.gameone;

import com.fivan.gameone.entity.mob.Player;
import com.fivan.gameone.graphics.Screen;
import com.fivan.gameone.input.Keyboard;
import com.fivan.gameone.level.Level;
import com.fivan.gameone.level.SpawnLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {

  private static final String TITLE = "Game";
  private static final int SCALE = 3;

  private static int width = 300;
  private static int height = width / 16 * 9;

  private Thread thread;
  private JFrame frame;
  private Keyboard key;
  private Level level;
  private Player player;
  private boolean running;

  private Screen screen;

  private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
  private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

  private Game() {
    Dimension size = new Dimension(width * SCALE, height * SCALE);
    setPreferredSize(size);

    screen = new Screen(width, height);
    frame = new JFrame();
    level = new SpawnLevel("/texture/level.png");
    key = new Keyboard();
    player = new Player(key);

    addKeyListener(key);
  }

  private synchronized void start() {
    running = true;
    thread = new Thread(this, "Display");
    thread.start();
  }

  private synchronized void stop() {
    running = false;
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    long lastTime = System.nanoTime();
    long timer = System.currentTimeMillis();
    final double ns = 1_000_000_000.0 / 60;
    double delta = 0;
    int frames = 0;
    int updates = 0;
    requestFocus();
    while (running) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while (delta >= 1) {
        update();
        updates++;
        delta--;
      }
      render();
      frames++;

      if (System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
        frame.setTitle(TITLE + " | upd: " + updates + " fps: " + frames);
        updates = 0;
        frames = 0;
      }
    }
    stop();
  }

  private void update() {
    key.update();
    player.update();
  }

  private void render() {
    BufferStrategy bs = getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(3);
      return;
    }

    screen.clear();

    int xScroll = player.x - screen.width / 2;
    int yScroll = player.y - screen.height / 2;
    level.render(xScroll, yScroll, screen);
    player.render(screen);

    System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);

    Graphics g = bs.getDrawGraphics();
    g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    g.dispose();
    bs.show();
  }

  public static void main(String[] args) {
    Game game = new Game();
    JFrame frame = game.frame;
    frame.setResizable(false);
    frame.setTitle(TITLE);
    frame.add(game);
    frame.pack();
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    game.start();
  }
}
