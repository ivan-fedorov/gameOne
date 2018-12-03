package com.fivan.gameone;

import com.fivan.gameone.graphics.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {

  public static int width = 300;
  public static int height = width / 16 * 9;
  public static int scale = 3;

  private Screen screen;

  private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
  private int[] pixels =  ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

  private Thread thread;
  private JFrame frame;
  private boolean running;

  private Game() {
    Dimension size = new Dimension(width * scale, height * scale);
    setPreferredSize(size);

    screen = new Screen(width, height);

    frame = new JFrame();
  }

  public synchronized void start() {
    running = true;
    thread = new Thread(this, "Display");
    thread.start();
  }

  public synchronized void stop() {
    running = false;
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    while (running) {
      update();
      render();
    }
  }

  public void update() {}

  public void render() {
    BufferStrategy bs = getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(3);
      return;
    }

    screen.clear();
    screen.render();

    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = screen.pixels[i];
    }

    Graphics g = bs.getDrawGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(0,0, getWidth(), getHeight());
    g.drawImage(image, 0,0, getWidth(), getHeight(), null);
    g.dispose();
    bs.show();

  }


  public static void main(String[] args) {
    Game game = new Game();
    JFrame frame = game.frame;
    frame.setResizable(false);
    frame.setTitle("Game");
    frame.add(game);
    frame.pack();
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    game.start();
  }
}
