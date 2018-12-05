package com.fivan.gameone;

import com.fivan.gameone.graphics.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {

  private static final String TITLE = "Game";

  private static int width = 300;
  private static int height = width / 16 * 9;
  private static final int SCALE = 3;

  private Screen screen;

  private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
  private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

  private Thread thread;
  private JFrame frame;
  private boolean running;

  private int x, y;

  private Game() {
    Dimension size = new Dimension(width * SCALE, height * SCALE);
    setPreferredSize(size);

    screen = new Screen(width, height);

    frame = new JFrame();
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
    int frames = 0;
    int updates = 0;
    long timer = System.currentTimeMillis();
    final double ns = 1_000_000_000.0 / 60;
    double delta = 0;
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
    x++;
  }

  private void render() {
    BufferStrategy bs = getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(3);
      return;
    }

    screen.clear();
    screen.render(x, y);

    System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);

    Graphics g = bs.getDrawGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, getWidth(), getHeight());
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
