public class FVar extends Formula {
 private String v;

 public Form form() {
  return Form.VARIABLE;
 }

 public String toString() {
  return v;
 }

 public String variableName() {
  return v;
 }

 public FVar(String s) {
  v = s;
 }
}

