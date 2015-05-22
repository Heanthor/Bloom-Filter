package tests;

import static org.junit.Assert.*;
import hashes.Hash1;
import hashes.Hash2;
import hashes.Hash3;

import org.junit.Test;

import filter.BloomFilter;
import filter.IllegalSizeException;

public class BloomFilterTest {

	@Test
	public void test() {
		Hash1 one = new Hash1("One", 100);
		Hash2 two = new Hash2("Two", 100);
		Hash3 three = new Hash3("Three", 100);

		/*
		 * First filter
		 */
		BloomFilter t = new BloomFilter();

		try {
			t.addHash(one).addHash(two).addHash(three);
		} catch (IllegalSizeException e) {
			e.printStackTrace();
		}
		
		t.storeValue("Value1");
		t.storeValue("Value2");
		
		/*
		 * Second filter
		 */
		BloomFilter t2 = new BloomFilter();

		try {
			t2.addHash(one).addHash(two).addHash(three);
		} catch (IllegalSizeException e) {
			e.printStackTrace();
		}
		
		t2.storeValue("Value1");
		t2.storeValue("Value2");
		
		/* * * * * * * *
		 *  Assertion  *
		 * * * * * * * */
		assertTrue(t.equals(t2));
		
		/*
		 * Third filter
		 */
		BloomFilter t3 = new BloomFilter();

		try {
			t3.addHash(one).addHash(two).addHash(three);
		} catch (IllegalSizeException e) {
			e.printStackTrace();
		}
		
		t3.storeValue("Value1");
		t3.storeValue("Value22");
		
		/* * * * * * * *
		 *  Assertion  *
		 * * * * * * * */
		assertFalse(t.equals(t3));
	}

}
