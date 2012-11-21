package dunphy.chris.rpncalc.calculator

import dunphy.chris.rpncalc.exceptions._
import dunphy.chris.rpncalc.tokens._

object RpnCalculator {
  def parseToken(value: String, registers: Registers): Registers =
    TokenFactory.buildToken(value) match {
      case ValueToken(value) =>
        registers.push(value)

      case UnaryOpToken(op) =>
        if (registers.size < 1) {
          throw new InvalidOperationException("Not enough operands.")
        }
        val (op1, popped) = registers.pop()
        val res = op(op1)
        popped.push(res)

      case BinaryOpToken(op) =>
        if (registers.size < 2) {
          throw new InvalidOperationException("Not enough operands.")
        }

        val (op1, popped1) = registers.pop()
        val (op2, popped2) = popped1.pop()
        val res = op(op2, op1)
        popped2.push(res)
    }
}
