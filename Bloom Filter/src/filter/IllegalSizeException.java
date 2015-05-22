package filter;

import java.io.Serializable;

/**
 * Thrown when the size of the hashes added to a BloomFilter do not match the size
 * assigned to the BloomFilter.
 * @author Reed
 *
 */
@SuppressWarnings("serial")
public class IllegalSizeException extends Exception implements Serializable{
	public IllegalSizeException(String message) {
		super(message);
	}
	//It's an exception
}
