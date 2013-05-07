package yalisp;

import static yalisp.Evaluator.eval;
import static yalisp.Reader.read;

import java.io.IOException;
import java.util.*;

import yalisp.Evaluator.Fn;

public class Core {
	
	public static Map makeEnv() throws IOException {
		Map env = new HashMap(INITIALENV);
		eval(read("(defn nil? (x) (= x nil))"), env);
		return env;
	}

	private static final Map INITIALENV = new HashMap() {
		{
			put(new Symbol("="), new Fn() {
				public Object invoke(Object... args) {
					return args[0] == args[1];
				};
			});
			put(new Symbol(">"), new Fn() {
				public Object invoke(Object... args) {
					return ((Long) args[0]) > ((Long) args[1]);
				};
			});
			put(new Symbol("<"), new Fn() {
				public Object invoke(Object... args) {
					return ((Long) args[0]) < ((Long) args[1]);
				};
			});
			put(new Symbol("%"), new Fn() {
				public Object invoke(Object... args) {
					return ((Long) args[0]) % ((Long) args[1]);
				};
			});
			put(new Symbol("+"), new Fn() {
				public Object invoke(Object... args) {
					Long result = 0L;
					for (Object o : args)
						result += (Long) o;
					return result;
				};
			});
			put(new Symbol("-"), new Fn() {
				public Object invoke(Object... args) {
					if (args.length == 0)
						return 0L;
					if (args.length == 1)
						return -(Long) args[0];
					Long result = 0L;
					for (Object o : args)
						result -= (Long) o;
					return result;
				};
			});
			put(new Symbol("cons"), new Fn() {
				public Object invoke(Object... args) {
					return new Cons(args[0], (Cons) args[1]);
				};
			});
			put(new Symbol("car"), new Fn() {
				public Object invoke(Object... args) {
					return ((Cons) args[0]).car;
				};
			});
			put(new Symbol("cdr"), new Fn() {
				public Object invoke(Object... args) {
					return ((Cons) args[0]).cdr;
				};
			});
		}
	};

}
