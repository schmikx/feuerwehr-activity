package gameboard;

import pieces.Piece;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JPanel{
  private Piece testPieceOne;
  private Piece testPieceTwo;
  public static final String IMAGE_DIR = "images/";
  private final Dimension prefSize = new Dimension(1080, 720);
  private ImageIcon backgroundImage;
  private final String[] backgroundImages= new String [] {"background.jpg", "background2.jpg"};
  private boolean gameOver = false;
  private int questionsAnsweredCounter = 0;
  private Timer t;

  public GamePanel() {
    setFocusable(true);
    setPreferredSize(prefSize);

    initGame();
    startGame();
  }

  public boolean isGameOver() {
    return gameOver;
  }

  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }

  private void initGame () {
    setBackgroundImage(1);
    createGameObjects();

    t = new Timer(20, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        doOnTick();
      }
    });
  }

  private void createGameObjects() {
    testPieceOne = new Piece(new Coordinates(200, 100), 9, Math.toRadians(45), 5);
    testPieceTwo = new Piece(new Coordinates(200, 609), 9, Math.toRadians(-45), 5);
  }

  private void initPlayersPieces() {
    // for initialising players pieces
  }

  public void setBackgroundImage(int imageNumber) {
    String imagePath = IMAGE_DIR + backgroundImages[imageNumber];
    URL imageURL = getClass().getResource(imagePath);
    backgroundImage = new ImageIcon(imageURL);
  }

  private void startGame() {
    t.start();
  }

  public void pauseGame() {
    t.stop();
  }

  public void continueGame() {
    if (!isGameOver()) t.start();
  }

  public void restartGame() {
    questionsAnsweredCounter = 0;
    setGameOver(false);
    createGameObjects();
    startGame();
  }

  private void endGame() {
    setGameOver(true);
    pauseGame();
  }

  private void doOnTick() {
    questionsAnsweredCounter++;
    if (questionsAnsweredCounter > 2015) endGame();

    testPieceOne.makeMove();
    testPieceTwo.makeMove();
    if (testPieceOne.touches(testPieceTwo)) {
      endGame();
    }

    repaint();
  }

  @Override
  public void paintComponent (Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    backgroundImage.paintIcon(null, g, 0, 0);

    g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 19));
    g.setColor(Color.BLUE);
    g.drawString("Questions answered: " + questionsAnsweredCounter, 22, prefSize.height-5);

    if (isGameOver()) {
      g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
      g.setColor(Color.RED);
      g.drawString("GAME OVER!", prefSize.width/2 - 130, prefSize.height/5);
    }

    testPieceOne.paintMe(g);
    testPieceTwo.paintMe(g);
  }
}
