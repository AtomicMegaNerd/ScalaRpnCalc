package dunphy.chris.rpncalc.tokens

import dunphy.chris.rpncalc.exceptions.InvalidTokenException

/*
 * This allows us to perform pattern matching on our doubles...
 */
object Double {
  def unapply(s: String): Option[Double] = try {
    Some(s.toDouble)
  } catch {
    case _: NumberFormatException => None
  }
}

object TokenFactory {
  def buildToken(value: String): Token = {
    value match {
      // Any valid double
      case Double(e) => ValueToken(e)

      // Some constants
      case "e" =>
        ValueToken(math.E)
      case "pi" =>
        ValueToken(math.Pi)

      // Some unary operators
      case "neg" =>
        UnaryOpToken(x => -x)
      case "abs" =>
        UnaryOpToken(x => math.abs(x))
      case "sqrt" =>
        UnaryOpToken(x => math.sqrt(x))
      case "log" =>
        UnaryOpToken(x => math.log(x))
      case "sin" =>
        UnaryOpToken(x => math.sin(x))
      case "cos" =>
        UnaryOpToken(x => math.sin(x))
      case "tan" =>
        UnaryOpToken(x => math.sin(x))

      // Some binary operators
      case "+" =>
        BinaryOpToken((x, y) => x + y)
      case "-" =>
        BinaryOpToken((x, y) => x - y)
      case "*" =>
        BinaryOpToken((x, y) => x * y)
      case "/" =>
        BinaryOpToken((x, y) => x / y)
      case "%" =>
        BinaryOpToken((x, y) => x % y)
      case "^" =>
        BinaryOpToken((x, y) => math.pow(x, y))
      case _ =>
        throw new InvalidTokenException(value + " is not a valid token")
    }
  }
}
