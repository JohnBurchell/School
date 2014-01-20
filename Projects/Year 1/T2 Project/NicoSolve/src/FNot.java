public class FNot extends Formula {
 private Formula sub;

 public FNot(Formula s) {
  sub = s;
 }

 public String toString() {
  return "(" + sub.toString() + "')";
 }

 public Form form() {
  return Form.NOT;
 }
 
 public Formula subFormula(int i) {
  if (i == 1) {
   return sub;
  } else {
   return null;
  }
 }
}

