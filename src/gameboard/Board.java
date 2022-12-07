package gameboard;

import javax.swing.JFrame;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Board extends JFrame{
  //Board-Design
  private int numberOfFields;
  //Percentages
  private int pOfPantomime;
  private int pOfDrawing;
  private int pOfExplaining;
  private int pOfQuestions;
  private final GamePanel activityGamePanel;

  public Board() {
    this.activityGamePanel = new GamePanel();

    registerWindowListener();
    createMenu();

    add(activityGamePanel);
    pack();

    setTitle("Feuerwehr-Activity");
    setLocation(10, 10);
    setResizable(false);

    setVisible(true);
  }

  public Board(int gFields, int panto, int draw, int explain, int quest, GamePanel activityGamePanel) {
    numberOfFields = gFields;
    pOfPantomime = panto;
    pOfDrawing = draw;
    pOfExplaining = explain;
    pOfQuestions = quest;
    this.activityGamePanel = activityGamePanel;
  }
  private void createMenu() {

    JMenuBar menuBar = new JMenuBar();
    this.setJMenuBar(menuBar);

    JMenu fileMenu = new JMenu("File");
    JMenu gameMenu = new JMenu("Game");
    JMenu prefMenu = new JMenu("Preferences");

    menuBar.add(fileMenu);
    menuBar.add(gameMenu);
    menuBar.add(prefMenu);

    addFileMenuItems(fileMenu);
    addGameMenuItems(gameMenu);
    addPrefMenuItems(prefMenu);
  }

  private void addFileMenuItems(JMenu fileMenu) {
    JMenuItem addTeam = new JMenuItem("Add Team");
    fileMenu.add(addTeam);
    addTeam.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });
    JMenuItem quitItem = new JMenuItem("Quit");
    fileMenu.add(quitItem);
    quitItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
  }
  private void registerWindowListener() {
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
      @Override
      public void windowDeactivated(WindowEvent e) {
        activityGamePanel.pauseGame();
      }
      @Override
      public void windowActivated(WindowEvent e) {
        activityGamePanel.continueGame();
      }
    });
  }
  private void addGameMenuItems(JMenu gameMenu) {
    JMenuItem pauseItem = new JMenuItem("Pause");
    gameMenu.add(pauseItem);
    pauseItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        activityGamePanel.pauseGame();
      }
    });

    JMenuItem continuetItem = new JMenuItem("Continue");
    gameMenu.add(continuetItem);
    continuetItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
       activityGamePanel.continueGame();
      }
    });

    gameMenu.addSeparator();
    JMenuItem restartItem = new JMenuItem("Restart");
    gameMenu.add(restartItem);
    restartItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        activityGamePanel.restartGame();
      }
    });
  }
  private void addPrefMenuItems(JMenu prefMenu) {

    JMenu submenu = new JMenu("Choose Background");
    prefMenu.add(submenu);

    JMenuItem menuItem = new JMenuItem("Option 1");
    submenu.add(menuItem);
    menuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        activityGamePanel.setBackgroundImage(0);
        repaint();
      }
    });

    menuItem = new JMenuItem("Option 2");
    submenu.add(menuItem);
    menuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        activityGamePanel.setBackgroundImage(1);
        repaint();
      }
    });
  }
}
