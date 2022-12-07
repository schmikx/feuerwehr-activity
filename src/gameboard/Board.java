package gameboard;

public class Board {
  //Board-Design
  private int numberOfFields;
  //Percentages
  private int pOfPantomime;
  private int pOfDrawing;
  private int pOfExplaining;
  private int pOfQuestions;
  public Board() {

  }
  public Board(int gFields, int panto, int draw, int explain, int quest) {
    numberOfFields = gFields;
    pOfPantomime = panto;
    pOfDrawing = draw;
    pOfExplaining = explain;
    pOfQuestions = quest;
  }
}
