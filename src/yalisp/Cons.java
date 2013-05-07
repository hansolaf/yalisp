package yalisp;

public class Cons {

	public final Object car;
	public final Cons cdr;
	
	public Cons(Object car, Cons cdr) {
		this.car = car;
		this.cdr = cdr;
	}
	
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		Cons current = this;
		while (current != null) {
			b.append(current.car + " ");
			current = current.cdr;
		}
		String data = b.toString();
		return "(" + data.substring(0, data.length() - 1) + ")";
	}
	
}
