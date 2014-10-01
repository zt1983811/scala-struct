package com.buransky.struct

import com.buransky.struct.mutable.{TinyFixedStructBuffer, SmallFixedStructBuffer, FixedStructBuffer}

case class MyClass(a: Boolean, b: Int, c: Int, d: Double)
case class MyClass2(a: Int, b: Int)

object MyAppStructs {
  val myClassStruct = Struct(MyClass.apply _, Function.unlift(MyClass.unapply))
  val myClass2Struct = Struct(MyClass2.apply _, Function.unlift(MyClass2.unapply))
}

object Main {
  import com.buransky.struct.MyAppStructs._

  def main(args: Array[String]) = {
    val small = new FixedStructBuffer(myClassStruct)
    val medium = new SmallFixedStructBuffer(myClassStruct)
    val large = new TinyFixedStructBuffer(myClassStruct)

    val pos1 = small.appendRef(MyClass(true, 42, 69, 13.65))
    val pos2 = medium.appendSmall(MyClass(false, 3, -100, 0.5))
    val pos3 = large.appendTiny(MyClass(true, 999, -456, 0.66666666))

    val all = small ++ medium ++ large
    all += MyClass(false, -44, -44, -44)

    val is = all.toIndexedSeq
    is.foreach(println)
  }
}
