package dunphy.chris.rpncalc.tokens;

sealed trait Token
case class ValueToken(value: Double) extends Token
case class UnaryOpToken(op: Double => Double) extends Token
case class BinaryOpToken(op: (Double, Double) => Double) extends Token