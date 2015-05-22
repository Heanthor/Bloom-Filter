package hashes;

/**
 * Hashes strings.
 * @Hash every character value squared,
 * with the character value * 3 subtracted from it, modded by size at end.
 * @author Reed
 *
 */
public class Hash1 extends HashFunction {
	private static final long serialVersionUID = 1L;
	private String name;
	private int size;

	public Hash1(String name, int size) {
		this.name = name;
		this.size = size;
	}

	@Override
	public int hash(String s) {
		int hold = 0;

		for (int x: s.toCharArray()) {
			hold += Math.abs(((x * x) - x*3));
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