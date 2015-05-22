package hashes;

/**
 * Hashes strings.
 * @Hash - Each character, bit shifted 5 left, modded by 10,000, bit shirted 2 right, modded by 10,000, 
 * added to the exp of the character, modded by 10,000,000, finally modded by size.
 * @author Reed
 *
 */
public class Hash4 extends HashFunction {
	private static final long serialVersionUID = 1L;
	private String name;
	private int size;

	public Hash4(String name, int size) {
		this.name = name;
		this.size = size;
	}
	
	@Override
	public int hash(String s) {
		int hold = 0;
		
		for (int x: s.toCharArray()) {
			double d = (((x << 5 % 10000) >> 2 % 10000) + Math.exp(x)) % 10000000;
			hold += d / s.length();
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
