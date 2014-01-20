
public class Formula {

 public enum Form {
  AND, OR, IMPLIES, EQUIVALENT, NOT, VARIABLE
 }
 
 private static class ParseRes {
  public Formula form;
  public String remainStr;
 }

 protected static String printBinConn(Form f) {
  switch (f) {
   case AND: return "&";
   case OR: return "|";
   case IMPLIES: return "->";
   case EQUIVALENT: return "<->";
  }
  return null;
 }

 private static Form readBinConn(String s) {
  if (s.startsWith("&")) {
   return Form.AND;
  } else if (s.startsWith("|")) {
   return Form.OR;
  } else if (s.startsWith("->")) {
   return Form.IMPLIES;
  } else if (s.startsWith("<->")) {
   return Form.EQUIVALENT;
  }
  return null;
 }

 private static ParseRes rparse(String str) {
  if (str.length() > 0) {
   char c = str.charAt(0);
   if (c >= 'A' && c <= 'Z') {
    ParseRes r = new ParseRes();
    r.form = new FVar(str.substring(0,1));
    r.remainStr = str.substring(1);
    return r;
   } else if (c == '(') {
    ParseRes res1 = rparse(str.substring(1));
    if (res1 == null) {
     return null;
    }
    Form f = readBinConn(res1.remainStr);
    if (f != null) {
     ParseRes res2 = rparse(res1.remainStr.substring(printBinConn(f).length()));
     if (res2 == null) {
      return null;
     }
     if (res2.remainStr.startsWith(")")) {
      ParseRes r = new ParseRes();
      r.form = new FBinaryConn(f, res1.form, res2.form);
      r.remainStr = res2.remainStr.substring(1);
      return r;
     }
    } else if (res1.remainStr.startsWith("')")) {
     ParseRes r = new ParseRes();
     r.form = new FNot(res1.form);
     r.remainStr = res1.remainStr.substring(2);
     return r;
    }
   }
  }
  return null;
 }

 public static Formula parse(String str) {
  ParseRes r = rparse(str);
  if (r == null) {
   return null;
  }
  if (r.remainStr.length() == 0) {
   return r.form;
  } else {
   return null;
  }
 }
 
 public String toString() {
  return null;
 }

 public Form form() {
  return null;
 }
 
 public Formula subFormula(int i) {
  return null;
 }

 public String variableName() {
  return null;
 }
}

