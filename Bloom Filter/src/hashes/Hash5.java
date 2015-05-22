package hashes;

public class Hash5 extends HashFunction {
	private static final long serialVersionUID = 6309664968439081310L;
	private String name;
	private int size;

	public Hash5(String name, int size) {
		this.name = name;
		this.size = size;
	}

	/**
	 * Hashes strings.
	 * @Hash String is split into two strings by alternating, while reversing it
	 * in some cases. Resulting strings are summed into two integers, which are
	 * XORd, then ORd together, and finally modded by 100.
	 */
	@Override
	public int hash(String s) {
		String temp = s;
		StringBuilder a = new StringBuilder();
		StringBuilder b = new StringBuilder();
		
		for (int i = 0; i < temp.length(); i++) {
			if (i % 2 == 0) {
				a.append(temp.charAt(i));
			} else {
				b.append(temp.charAt(i));
				temp = rev(temp);
			}
		}
		
		char[] a2 = a.toString().toCharArray();
		char[] b2 = b.toString().toCharArray();
		
		int hold1 = 0, hold2 = 0;
		
		for (char c: a2) {
			hold1 += c;
		}
		
		for (char c: b2) {
			hold2 += c;
		}
		
		int hold = hold1 ^ hold2 | hold2;
		return hold % size;
	}

	/**
	 * Reverses the given string. E.g. rev("Hello.") returns ".olleH".
	 * @param s - String to reverse.
	 * @return The string, whose characters are in reverse order.
	 */
	public String rev(String s) {
		char[] c = s.toCharArray();
		StringBuilder b = new StringBuilder();
		
		for (int i = c.length - 1; i >= 0; i--) {
			b.append(c[i]);
		}
		return b.toString();
	}
	
	/**
	 * Reverses the given char[]. E.g. rev([H, e, l, l, o, .]) returns 
	 * [., o, l, l, e, H].
	 * @param c
	 * @return
	 */
	public char[] rev(char[] c) {
		char[] toReturn = new char[c.length];
		
		for (int i = c.length - 1; i >= 0; i--) {
			toReturn[c.length - 1 - i] = c[i];
		}
		return toReturn;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getSize() {
		return size;
	}

}
