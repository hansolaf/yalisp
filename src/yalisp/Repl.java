package yalisp;

import static yalisp.Evaluator.eval;
import static yalisp.Reader.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Repl {
	
	private static void evalFile(String filename, Map env) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
	    try {
	        String line = br.readLine();
	        while (line != null) {
	        	process(line, env);
	            line = br.readLine();
	        }
	    } finally {
	        br.close();
	    }
	}
	
	private static void process(String input, Map env) throws IOException {
		if (input.trim().equals(""))
			return;
		Object form = read(input);
		if (form instanceof Comment)
			return;
		System.out.println(eval(form, env));
	}
	
	public static void main(String[] args) throws IOException {
		Map env = Core.makeEnv();
		if (args.length > 0) {
			evalFile(args[0], env);
		} else {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				System.out.print("repl-> ");
				try {
					process(in.readLine(), env);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		}
	}

}
