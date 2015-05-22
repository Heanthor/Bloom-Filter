package filter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hashes.*;

/**
 * Implements Bloom Filters for storing strings, command line interface in main method.
 * @author Reed
 */
public class BloomFilter implements Serializable {
	private static final long serialVersionUID = -5034817267936681994L;
	private byte[] filter;
	private int size; // Size of filter
	private List<HashFunction> hashes;
	private static boolean DEBUG = false;
	private static boolean PRINT; //Print values or not?
	/**
	 * Number of hash functions registered with this object.
	 */
	public int numHashes;

	/**
	 * New BloomFilter with default size of hash 100.
	 */
	public BloomFilter() {
		filter = new byte[100];
		size = 100;
		PRINT = true;
		numHashes = 0;
		hashes = new ArrayList<HashFunction>();
	}

	/**
	 * Creates BloomFilter.
	 * @param size - Size of hash.
	 */
	public BloomFilter(int size) {
		filter = new byte[size];
		this.size = size;
		PRINT = true;
		numHashes = 0;
		hashes = new ArrayList<HashFunction>();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//Create instances of hash functions
		Hash1 one = new Hash1("One", 100);
		Hash2 two = new Hash2("Two", 100);
		Hash3 three = new Hash3("Three", 100);

		BloomFilter t = new BloomFilter();

		//Add hashes
		try {
			t.addHash(one).addHash(two).addHash(three);
		} catch (IllegalSizeException e) {
			e.printStackTrace();
		}

		while (true) {
			String choice = t.input(t, sc);

			//Choices
			if (choice.equals("s")) {
				System.out.print("\nEnter a string: ");
				String in = sc.nextLine();

				t.storeValue(in);

			} else if (choice.equals("r")) {
				System.out.print("\nEnter a string: ");
				String in = sc.nextLine();

				if (t.retrieve(in)) {
					System.out.println("Found \"" + in + "\".");
				} else {
					System.out.println("Not found.");
				}

			} else if (choice.equals("e")) { 
				System.exit(0);

			} else if (choice.equals("p")) {
				t.printTable();

			} else if (choice.equals("d")) { //Hidden
				DEBUG = !DEBUG;
				String s = DEBUG ? "Debug On." : "Debug Off.";
				System.out.println(s);

			} else {
				System.out.println("Invalid command.");
			}

			System.out.println();
		}
	}

	/**
	 * Adds a new hash for the BloomFilter object to use.
	 * @param f - The HashFunction to use.
	 * @return The current object, for chained calls.
	 * @throws IllegalSizeException - If the size f is designed to be used with does not
	 * match the size this is designed to work with.
	 */
	public BloomFilter addHash(HashFunction f) throws IllegalSizeException {
		if (f.getSize() != size) {
			throw new IllegalSizeException("Size " + f.getSize() + 
					" in HashFunction " + f.getName() +
					" does not match BloomFilter size " + size + ".");
		} else {
			hashes.add(f);
			numHashes++;

			return this;
		}
	}
	/**
	 * Print Bloom Filter output, or stay silent? Defaults to true.
	 * @param b - True for print, false for no printing.
	 */
	public void print(boolean b) {
		PRINT = b;
	}

	public int[] getHashes(String in) {
		int[] locs = new int[hashes.size()];
		int count = 0;

		//Debugging - prints hash value outputs
		for (HashFunction t: hashes) {
			int tmp = t.hash(in);
			if (DEBUG) {
				System.out.println(t.getName() + ": " + tmp);
			}

			locs[count++] = tmp;
		}

		return locs;
	}

	/**
	 * Stores the value in the current object's table.
	 * @param in String to store.
	 * @return the current object.
	 */
	public BloomFilter storeValue(String in) {
		int[] locs = getHashes(in);

		//Set Bloom filter
		for(int i = 0; i < locs.length; i++) {
			filter[locs[i]] = 1;
		}

		if (PRINT) {
			System.out.println("Value \"" + in + "\" stored.");
		}

		return this;
	}

	/**
	 * Prints the current Bloom Filter to console.
	 */
	public void printTable() {
		System.out.print("[ ");
		int count = 0;

		for (int i: filter) {
			System.out.print((i) + " ");
			if (count % 10 == 0) {
				System.out.println();
			}
			count++;
		}

		System.out.println("]");
	}

	/**
	 * 
	 * @param in String to check.
	 * @return True if String is in the current object, false otherwise.
	 */
	public boolean retrieve(String in) {
		int[] locs = getHashes(in);
		boolean toReturn = false;

		for (int i: locs) {
			if (filter[i] == 1) {
				toReturn = true;
			} else {
				toReturn = false;
				break;
			}
		}

		return toReturn;
	}

	/**
	 * @return number of hashes currently working with this.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Handles command line input. Only used with main.
	 * @param t - BloomFilter object
	 * @param sc - Scanner being used
	 * @return User choice.
	 */
	private String input(BloomFilter t, Scanner sc) {
		System.out.print("Enter choice. " + 
				"(Store value -> \"s\", retrieve -> \"r\", print filter -> "
				+ "\"p\", exit -> \"e\"): ");

		return sc.nextLine();
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof BloomFilter)) {
			return false;
		} else {
			BloomFilter b = (BloomFilter) o;
			
			//Compare each entry in both stored arrays
			for (int i = 0; i < filter.length; i++) {
				if (filter[i] != b.filter[i]) {
					return false;
				}
			}
			
			//All entries were equal
			return true;
		}
	}

	public int hashCode() {
		int n = 0;

		for (byte b: filter) {
			n += b;
		}

		return n;
	}
}
