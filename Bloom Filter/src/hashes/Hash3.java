package hashes;

/**
 * Hashes strings.
 * @Hash each character is square rooted, multiplied by 100, multiplied by index 
 * in string, casted to int, then index is added to it, and is modded by size at end.
 * @author Reed
 *
 */
public class Hash3 extends HashFunction {
	private static final long serialVersionUID = 1L;
	private String name;
	private int size;

	public Hash3(String name, int size) {
		this.name = name;
		this.size = size;
	}

	@Override
	public int hash(String s) {
		int hold = 0;
		char[] x = s.toCharArray();

		for (int i = 1; i <= s.length(); i++) {
			hold += (int) (Math.sqrt(x[i - 1]) * 100 * i) + i;
		}

		return hold % size;
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