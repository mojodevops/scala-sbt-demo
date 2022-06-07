package cc.xstack

class Point(xc: Int, yc: Int) {
  var x: Int = xc
  var y: Int = yc

  println("x 的坐标点: " + x)
  println("y 的坐标点: " + y)

  def move(dx: Int, dy: Int): Unit = {
    x = x + dx
    y = y + dy
    println("x 的坐标点: " + x)
    println("y 的坐标点: " + y)
  }
}

object HelloScala {
  def main(args: Array[String]): Unit = {
    val pt = new Point(10, 20)
    println("移动坐标后")
    pt.move(10, 10)
    println("hello scala world")
  }
}