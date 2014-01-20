import java.util.HashMap;

class TautChecker {
	
	public static HashMap<String, Boolean> key = new HashMap<String, Boolean>();
	public static boolean tautology = false;

	public static String union(String s1, String s2) {
		int i;
		for (i = 0; i < s2.length(); i++) { // for each character in s1
			if (s1.indexOf(s2.charAt(i)) == -1) { // the character is not already in s2
				s1 = s1 + s2.charAt(i); // add the character to s2
			}
		}
		return s1;
	}

	public static String formulaVars(Formula f) {
		switch (f.form()) {
		case VARIABLE:
			return f.variableName();
		case NOT:
			return formulaVars(f.subFormula(1));
		default: // it's a binary connective
			return union(formulaVars(f.subFormula(1)), formulaVars(f.subFormula(2)));
			// we compute the union of the two subformulas to avoid several
			// occurrences of the same var
		}
	}
	
	public static boolean calc(Formula f) {
		switch (f.form()) {
		case VARIABLE:
			return key.get(f.variableName());
		case NOT:
			return !calc(f.subFormula(1));
		case AND:
			return calc(f.subFormula(1)) && calc(f.subFormula(2));
		case OR:
			return calc(f.subFormula(1)) || calc(f.subFormula(2));
		case IMPLIES:
			return !calc(f.subFormula(1)) || calc(f.subFormula(2));
		case EQUIVALENT:
			return calc(f.subFormula(1)) == calc(f.subFormula(2));
		default:
			return false;
		}
	}

	private static void recBinEnum(String str, int n, Formula f) {
		if (n == 0) {
			fillHashmap(str, formulaVars(f));
			if(!calc(f)){
				System.out.println("The formula is NOT a tautology!");
				//System.exit(1);
			}
		} else {
			recBinEnum(str + "T", n - 1, f);
			recBinEnum(str + "F", n - 1, f);
		}
	}
	
	private static void fillHashmap(String str, String vars) {
		key.clear();
		for (int i = 0; i<vars.length();i++){
			if(str.substring(i, i + 1).equals("T")){
				key.put(vars.substring(i, i + 1), true);
			}else{
				key.put(vars.substring(i, i + 1), false);
			}
		}
	}

	public static void main(String[] args) {
//		Formula f = Formula.parse(args[0]);
		Formula f = Formula.parse("((A|(B|D))<->((A|B)|D))");
		if (f == null) {
			System.out.println("Formula is not valid!");
		} else {
			recBinEnum("", formulaVars(f).length(), f);
			System.out.println("The formula is a tautology!");
		}
	}
}