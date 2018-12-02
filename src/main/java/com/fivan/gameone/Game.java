package com.fivan.gameone;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

  public static int width = 300;
  public static int height = width / 16 * 9;
  public static int scale = 3;

  private Thread thread;
  private JFrame frame;
  private boolean running;

  private Game() {
    Dimension size = new Dimension(width * scale, height * scale);
    setPreferredSize(size);

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
