package yalisp;

import static java.util.Arrays.asList;

import java.util.*;

public class Evaluator {

	public static Object eval(Object obj, Map env) {
		if (obj instanceof Number)
			return obj;
		if (obj instanceof Boolean)
			return obj;
		if (obj instanceof Symbol) {
			if (!env.containsKey(obj))
				throw new RuntimeException("Cannot find symbol " + obj + " in env " + env);
			return env.get(obj);
		}
		
		// Assume s-expression
		List sexp = (List) obj;
		Symbol method = (Symbol) sexp.get(0);
		
		// Is it a special form?
		if (asList("def", "if").contains(method.name))
			return evalSpecialForm(sexp, env);
		
		// Eval args
		List args = new ArrayList();
		for (Object o : sexp.subList(1, sexp.size()))
			args.add(eval(o, env));
		
		// Is it a built-in?
		if (asList("+", "=", ">").contains(method.name))
			return evalBuiltin(method, args);
		
		throw new RuntimeException("Unknown function " + method);
	}

	static Object evalBuiltin(Symbol method, List args) {
		if ("+".equals(method.name)) {
			Long result = 0L;
			for (Object o : args)
				result += (Long) o;
			return result;
		}
		if ("=".equals(method.name)) {
			return ((Long) args.get(0)) == ((Long) args.get(1));
		}
		if (">".equals(method.name)) {
			return ((Long) args.get(0)) > ((Long) args.get(1));
		}
		throw new RuntimeException("Not a builtin: " + method);
	}

	static Object evalSpecialForm(List sexp, Map env) {
		String name = ((Symbol) sexp.get(0)).name;
		if (name.equals("def")) {
			env.put(sexp.get(1), eval(sexp.get(2), env));
			return sexp.get(1);
		}
		if (name.equals("if")) {
			Boolean condition = (Boolean) eval(sexp.get(1), env);
			return condition ? eval(sexp.get(2), env) : eval(sexp.get(3), env);
		}
		throw new RuntimeException("Not a special form: " + sexp.get(0));
	}
	
	static boolean isSymbolAnyOf(Symbol method, String... strings) {
		return Arrays.asList(strings).contains(method.name);
	}

}
