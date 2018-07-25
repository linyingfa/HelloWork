package fanshe;

//Point泛型类的实现
public class Point<T> {
  private T x,y;

  public T getX() {
      return x;
  }

  public void setX(T x) {
      this.x = x;
  }

  public T getY() {
      return y;
  }

  public void setY(T y) {
      this.y = y;
  }

}

