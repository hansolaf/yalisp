package yalisp;

import static yalisp.Evaluator.eval;
import static yalisp.Reader.read;

import java.io.*;
import java.util.*;

public class Repl {

	public static void main(String[] args) throws IOException {
		Map env = Evaluator.INITIALENV;
		eval(read("(defn nil? (x) (= x nil))"), env);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.print("repl-> ");
			try {
				String input = in.readLine();
				if (input.equals(""))
					continue;
				Object form = read(input);
				System.out.println(eval(form, env));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
