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
      case Double(e) => new ValueToken(e)

      // Some constants
      case "e" =>
        new ValueToken(math.E)
      case "pi" =>
        new ValueToken(math.Pi)

      // Some unary operators
      case "neg" =>
        new UnaryOpToken(x => -x)
      case "abs" =>
        new UnaryOpToken(x => math.abs(x))
      case "sqrt" =>
        new UnaryOpToken(x => math.sqrt(x))
      case "log" =>
        new UnaryOpToken(x => math.log(x))
      case "sin" =>
        new UnaryOpToken(x => math.sin(x))
      case "cos" =>
        new UnaryOpToken(x => math.sin(x))
      case "tan" =>
        new UnaryOpToken(x => math.sin(x))

      // Some binary operators
      case "+" =>
        new BinaryOpToken((x, y) => x + y)
      case "-" =>
        new BinaryOpToken((x, y) => x - y)
      case "*" =>
        new BinaryOpToken((x, y) => x * y)
      case "/" =>
        new BinaryOpToken((x, y) => x / y)
      case "%" =>
        new BinaryOpToken((x, y) => x % y)
      case "^" =>
        new BinaryOpToken((x, y) => math.pow(x, y))
      case _ =>
        throw new InvalidTokenException(value + " is not a valid token")
    }
  }
}
