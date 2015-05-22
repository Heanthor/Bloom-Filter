package hashes;

/**
 * Hashes strings.
 * @Hash Each character is raised to string length, then exp of the character is subtracted,
 * then number modded by 100,000. Casted to an integer, modded by size at end.
 * @author Reed
 *
 */
public class Hash2 extends HashFunction {
	private static final long serialVersionUID = 1L;
	private String name;
	private int size;

	public Hash2(String name, int size) {
		this.name = name;
		this.size = size;
	}

	@Override
	public int hash(String s) {
		int hold = 0;

		for (int x: s.toCharArray()) {
			double d = Math.abs(Math.pow(x, s.length()) - Math.exp(x)) % 100000;
			hold += (int) d;
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