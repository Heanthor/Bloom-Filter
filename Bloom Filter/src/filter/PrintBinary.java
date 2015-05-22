package filter;

public class PrintBinary {

	public static void main(String[] args) {
		PrintBinary b = new PrintBinary();
		//System.out.println(b.printBinary(2147483647, true, true));
		//System.out.println(b.printBinary(1024, true, true));
		System.out.println(b.printBinary(b.addOneAtIndex(15), true, false));
		b.printBinary(3, true, true);

		//System.out.println(b.stitch(1)); //remember, treat string like array
	}

	/**
	 * Treats output 32 bit integer as a 0-indexed array, when printed as binary.
	 * Sets bit to 1 at position index under this assumption.
	 * @param index - Index to set to 1. Since this is like an array, the 
	 * range of index can be 0 - 31
	 * @return New binary representation of array.
	 * @throws IndexOutOfBoundsException - If index is out of the 0 - 31 range.
	 */
	public int addOneAtIndex(int index) {
		if (index < 0 || index > 31) {
			throw new IndexOutOfBoundsException();
		}

		int a = 1;

		return a << 32 - (index + 1);
	}

	/**
	 * @deprecated
	 * Creates three integers, and them together to form a string that treats 
	 * them as a 96 element array, then swaps the bit located at the index in this new array.
	 * @param index - Index to set bit to 1.
	 * @return The string representation of this number.
	 * @throws IndexOutOfBoundsException If the index is not in the valid 
	 * range, e.g. 0 - 95.
	 */
	public String stitch(int index) { // 0-96
		if (index < 0 || index > 95) {
			throw new IndexOutOfBoundsException();
		}
		
		int fst = 0, snd = 0, trd = 0;

		if (index < 32) {
			fst = addOneAtIndex(index);
		} else if (index < 64) {
			snd = addOneAtIndex(index + 32);
		} else {
			trd = addOneAtIndex(index + 64);
		}
		
		return printBinary(fst, false, false) + 
				printBinary(snd, false, false) + 
				printBinary(trd, false, false);
	}

	/**
	 * Prints b in pretty binary format, grouping bits of four bytes,
	 * and trimming leading zeroes.
	 * @param b - Number to print in binary form.
	 * @param spaces - Insert spaces into return string to separate bytes?
	 * @param trimZeroes - Trim leading zeroes from output string?
	 * When false, this method will always return a 32 character string.
	 * @return String to print
	 */
	public String printBinary(int b, boolean spaces, boolean trimZeroes) {
		int a = 1;

		//This method builds the string backwards, so binary looks right.
		String out = "";

		for (int i = 0; i < 8; i++) {
			String byteString = "";

			for (int j = 0; j < 4; j++) {
				if ((a & b) != 0) {
					byteString = "1" + byteString;
				} else {
					byteString = "0" + byteString;
				}

				a = a << 1; //Shift over 1
			}

			if (spaces && i > 0) {
				out = " " + out;
			}

			out = byteString + out;
		}

		if (trimZeroes) {
			//Clear leading zeroes
			int firstOne = -1;

			for (int i = 0; i < out.length(); i++) {
				if (out.charAt(i) == '1') {
					firstOne = i;
					break;
				}
			}

			out = out.substring((firstOne / 5) * 5); //this really worked
			//System.out.println(out);
		}

		return out;
	}	
}
