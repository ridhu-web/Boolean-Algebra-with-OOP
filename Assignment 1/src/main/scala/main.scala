object Main:

  trait state:
    var value : Boolean
    var expression : BooleanExpression

  case class Input(name: String) extends state:
    value=false
    expression=BooleanExpression.Value(false)


  case class LogicGate(name: String) extends state:
    value = false
    expression = BooleanExpression.Value(false)



  enum BooleanExpression:

    case Value(v: Boolean)
    case NOT(o1: BooleanExpression)
    case OR(o1: BooleanExpression, o2: BooleanExpression)
    case AND(o1: BooleanExpression, o2: BooleanExpression)
    case NAND(o1: BooleanExpression, o2: BooleanExpression)
    case NOR(o1: BooleanExpression, o2: BooleanExpression)
    case XOR(o1: BooleanExpression, o2: BooleanExpression)
    case XNOR(o1: BooleanExpression, o2: BooleanExpression)

    def eval: Boolean = this match
      case Value(x: Boolean) => x
      case NOT(o1) => !o1.eval
      case OR(o1, o2) => o1.eval | o2.eval
      case AND(o1, o2) => o1.eval & o2.eval
      case NAND(o1, o2) => !(o1.eval & o2.eval)
      case NOR(o1, o2) => !(o1.eval | o2.eval)
      case XOR(o1, o2) => o1.eval ^ o2.eval
      case XNOR(o1, o2) => !(o1.eval ^ o2.eval)

  def assign(gate : state, expression : BooleanExpression): Unit =
    gate.expression=expression


  @main def main(): Unit = {
    import BooleanExpression.*
    println(NOT(Value(true)).eval)
    var logicGate1 : LogicGate = new LogicGate("logicGate1")
    assign(logicGate1,NOT(XOR(Value(true),Value(true))))
    println(logicGate1.expression)
  }