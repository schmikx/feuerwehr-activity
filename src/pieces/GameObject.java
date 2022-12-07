package pieces;

import gameboard.Coordinates;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class GameObject {
  private static AtomicInteger nextId = new AtomicInteger();
  private String colour;
  private int id;
  private Coordinates objectPosition;
  private double width;
  private double height;
  private double movingAngle;
  private double movingDistance;
  /*
  public GameObject(String gColour) {
    position = 0;
    colour = gColour;
    id = nextId.incrementAndGet();
  }

   */

  public GameObject(Coordinates objectPosition, double width, double height) {
    this.objectPosition = objectPosition;
    this.width = width;
    this.height = height;
    movingAngle = 0;
    movingDistance = 0;
  }

  public static Coordinates polarToCartesianCoordinates(double angle) {

    // x-Achse zeigt nach Osten, y-Achse zeigt nach Süden beim Zeichnen
    double x = Math.cos(angle);
    double y = Math.sin(angle);

    return new Coordinates(x, y);
  }

  public Coordinates getObjectPosition() {
    return objectPosition;
  }

  public void setObjectPosition(Coordinates objectPosition) {
    this.objectPosition = objectPosition;
  }

  public double getWidth() {
    return width;
  }

  public void setWidth(double width) {
    this.width = width;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public double getMovingAngle() {
    return movingAngle;
  }

  public void setMovingAngle(double movingAngle) {
    this.movingAngle = movingAngle;
  }

  public double getMovingDistance() {
    return movingDistance;
  }

  public void setMovingDistance(double movingDistance) {
    this.movingDistance = movingDistance;
  }

  public boolean isLeftOf(GameObject that) {
    return this.getObjectPosition().getX() + this.getWidth() < that.getObjectPosition().getX();
  }

  public boolean isAbove(GameObject that) {
    return this.getObjectPosition().getY() + this.getHeight() < that.getObjectPosition().getY();
  }

  public boolean touches(GameObject that) {
    if (this.isLeftOf(that)) return false;
    if (that.isLeftOf(this)) return false;
    if (this.isAbove(that)) return false;
    if (that.isAbove(this)) return false;

    return true;
  }

  public void moveGameObject() {

    Coordinates direction = polarToCartesianCoordinates(movingAngle);

    objectPosition.setX(objectPosition.getX() + direction.getX() * movingDistance);
    objectPosition.setY(objectPosition.getY() + direction.getY() * movingDistance);
  }

  public void makeMove() {
    moveGameObject();
  }

  protected abstract void paintMe(java.awt.Graphics g);

}