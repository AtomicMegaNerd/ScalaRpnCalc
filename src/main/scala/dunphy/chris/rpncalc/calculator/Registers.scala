package dunphy.chris.rpncalc.calculator

import dunphy.chris.rpncalc.exceptions.MemoryFullException
import dunphy.chris.rpncalc.exceptions.MemoryEmptyException

/*
 * This class implements the memory registers
 * for our calculator.  They are named r0, r1, r2, and r3.
 * We can push and pop numbers to/from the bottom
 * registers only.  We can store four numbers in
 * our calculator's memory
 */
case class Registers(
  val r0: Double = 0.0,
  val r1: Double = 0.0,
  val r2: Double = 0.0,
  val r3: Double = 0.0,
  val size: Int = 0) {

  // This is fixed as we have four registers in
  // our calculator
  val capacity = 4

  // Pushes an element into the registers
  def push(elem: Double): Registers =
    if (size < capacity)
      Registers(elem, r0, r1, r2, size + 1)
    else throw new MemoryFullException("Cannot push to the registers, they are full")

  // Pops off the value from the lowest register
  def pop(): (Double, Registers) =
    if (size < 1)
      throw new MemoryEmptyException("Cannot pop from empty registers")
    else (r0, Registers(r1, r2, r3, 0, size - 1))

  def clear(): Registers = Registers()

}