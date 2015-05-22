package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import filter.PrintBinary;

public class BinaryTest {
	PrintBinary b = new PrintBinary();

	@Test
	public void testOverflow() {
		assertEquals("01111111111111111111111111111111",
				b.printBinary(2147483647, false, false));
	}

	@Test
	public void testBasicPrint() {
		assertEquals("0100 0000 0000", b.printBinary(1024, true, true));
	}

	@Test
	public void testAddOneAtIndex() {
		//Index 0
		assertEquals("10000000000000000000000000000000",
				b.printBinary(b.addOneAtIndex(0), false, false));

		//Index 1
		assertEquals("01000000000000000000000000000000",
				b.printBinary(b.addOneAtIndex(1), false, false));

		//Index 31
		assertEquals("00000000000000000000000000000001",
				b.printBinary(b.addOneAtIndex(31), false, false));

	}

	/*
	@Test 
	public void stitch() {
		assertEquals("01000000000000000000000000000000000000000000000000000"
				+ "0000000000000000000000000000000000000000000", b.stitch(1));
		assertEquals("000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000", b.stitch(32));
	} */
}
