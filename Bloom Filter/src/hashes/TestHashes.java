package hashes;

/**
 * Test class
 * @author Reed
 *
 */
public class TestHashes {

	public static void main(String[] args) {
		//Hash4 four = new Hash4("four", 100);
		
		//System.out.println(four.hash("testing adsfstrings"));
		
		Hash5 five = new Hash5("five", 100);
		String norm = "Testytewq";
		System.out.println(five.rev(norm));
		System.out.println(five.rev(norm.toCharArray()));
		System.out.println(five.hash(norm));
	}

}
