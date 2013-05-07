package yalisp;
public class Symbol {

	public final String name;

	public Symbol(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
	
	public boolean equals(Object obj) {
		return obj instanceof Symbol && name.equals(((Symbol) obj).name);
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
}
