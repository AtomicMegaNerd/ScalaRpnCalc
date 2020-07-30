package dunphy.chris.rpncalc

import java.util.Scanner
import dunphy.chris.rpncalc.calculator.RpnCalculator
import dunphy.chris.rpncalc.calculator.Registers

/*
 * This is a simple Reverse Polish Notation calculator that
 * works from the command line.
 */
object Main {

  // The one mutable variable in our program
  var registers = Registers()

  val STARS = "************************************************"

  def main(args: Array[String]) {
    val scanner = new Scanner(System.in)
    try {
      while (true) {
        println("\nRPN SuperCalc 3000 SLT Limited Edition")
        println(STARS)
        println("r3: " + registers.r3)
        println("r2: " + registers.r2)
        println("r1: " + registers.r1)
        println("r0: " + registers.r0)
        println(STARS)
        print("> ")

        // We are using java.util.Scaner here :-)
        val token = scanner.nextLine.trim.toLowerCase

        token match {
          case "q" =>
            println("Good-bye")
            return
          case "c" =>
            registers = registers.clear
          case _ =>
            try {
              registers = RpnCalculator.parseToken(token, registers)
            } catch {
              case e: Exception =>
                System.err.println("ERROR: " + e.getMessage)
            }
        }
      }
    } finally {
      scanner.close()
    }
  }

}
