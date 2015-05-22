package hashes;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class HashFunction implements Serializable {
	/**
	 * Hashes string to bloom filter hash values.
	 * @param s - String to hash.
	 * @return Positions of byte to flip in size _size_ bloom filter.
	 */
	public abstract int hash(String s);
	
	/**
	 * @return - the name of the hash function, for printing.
	 */
	public abstract String getName();
	
	/**
	 * 
	 * @return - size this hash function is designed to work with.
	 */
	public abstract int getSize();
}
