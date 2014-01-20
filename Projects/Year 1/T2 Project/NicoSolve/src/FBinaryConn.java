public class FBinaryConn extends Formula {
 private Form f;
 private Formula fst, snd;

 public FBinaryConn(Form ff, Formula ffst, Formula fsnd) {
  f = ff;
  fst = ffst;
  snd = fsnd;
 }

 public String toString() {
  return "(" + fst.toString() + Formula.printBinConn(f) + snd.toString() + ")";
 }

 public Form form() {
  return f;
 }
 
 public Formula subFormula(int i) {
  if (i == 1) {
   return fst;
  } else if (i == 2) {
   return snd;
  } else {
   return null;
  }
 }
}

