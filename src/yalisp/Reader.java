package yalisp;

import java.io.*;
import java.util.*;

public class Reader {

	public static Object read(String string) throws IOException {
		return read(new PushbackReader(new StringReader(string)));
	}

	static Object read(PushbackReader in) throws IOException {
		int c = in.read();
		if (((char) c) == '(')
			return readSexp(in);
		return readObject(c, in);
	}
	
	static Object readObject(int c, PushbackReader in) throws IOException {
		String token = readToken(c, in);
		if (token.matches("\\d+"))
			return Long.valueOf(token);
		if (token.matches("true|false"))
			return Boolean.valueOf(token);
		if (token.matches("nil"))
			return null;
		return new Symbol(token);
	}

	static List readSexp(PushbackReader in) throws IOException {
		List parts = new ArrayList();
		while (true) {
			int i = in.read();
			if (i == -1)
				throw new RuntimeException("EOF while reading " + parts);
			char c = (char) i;
			if (c == ')')
				break;
			if (Character.isWhitespace(c))
				continue;
			in.unread(c);
			parts.add(read(in));
		}
		return parts;
	}

	static String readToken(int c, PushbackReader in) throws IOException {
		StringBuffer b = new StringBuffer();
		while (true) {
			char ch = (char) c;
			if (c == -1 || Character.isWhitespace(ch) || ch == '(' || ch == ')')
				break;
			b.append(ch);
			c = in.read();
		}
		in.unread((char) c);
		return b.toString();
	}

}
