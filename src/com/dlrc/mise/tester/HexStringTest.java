package com.dlrc.mise.tester;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dlrc.mise.HexString;

public class HexStringTest {

	@Test
	public void testToByte() {
		String[] srcArr = { 
				"30 31 32 33 34 35363738390A20 68 65 6c 6c 6f 20 77 6f 72 6c 64 0a", 
				"0x30 0x31 0X32 0x33 0X34 0X35 0X36 0X37 0X38 0X39 0X0A 0X20 0X68 0X65 0X6c 0X6c 0X6f 20 77 6f 72 6c 64 0a", 
				"303132333435363738390a2068656c6c6f20776f726c640a", 
				"30, 31, 32, 33,   34 3536373839 A 20 68 65 6c 6c 6f 20 77 6f 72 6c 64 A", 
				"30, 31, 32, 33,   34 3536373839 a 20 68 65 6c 6c 6f 20 77 6f 72 6c 64 a",
		};

		String dst = "0123456789\n hello world\n";
		for (String src : srcArr) {
			String rst = new String(HexString.toByte(src));
			if (dst.compareTo(rst) != 0) {
				System.out.println(src.length() + "src#" + src);
				System.out.println(dst.length() + "dst#" + dst);
				System.out.println(rst.length() + "rst#" + rst);
				System.out.println("error");
				fail("error");
			}
		}
	}

	@Test
	public void testToHexStrByteArrayStringString() {
		String src = "0123456789\n hello world\n";
		String dst = "0X30, 0X31, 0X32, 0X33, 0X34, 0X35, 0X36, " +
				"0X37, 0X38, 0X39, 0X0A, 0X20, 0X68, 0X65, 0X6C, " +
				"0X6C, 0X6F, 0X20, 0X77, 0X6F, 0X72, 0X6C, 0X64, 0X0A";
		String rst = HexString.toHexStr(src.getBytes(), "0X", ", ");
		if (dst.compareTo(rst) != 0) {
			System.out.println("HexString.toHexStr");
			System.out.println(src.length() + "src#" + src);
			System.out.println(dst.length() + "dst#" + dst);
			System.out.println(rst.length() + "rst#" + rst);			
			fail();
		}
		
	}

	@Test
	public void testToHexStrByteArrayString() {
		String src = "0123456789\n hello world\n";
		String dst = "30,31,32,33,34,35,36," +
				"37,38,39,0A,20,68,65,6C," +
				"6C,6F,20,77,6F,72,6C,64,0A";
		String rst = HexString.toHexStr(src.getBytes(), ",");
		if (dst.compareTo(rst) != 0) {
			System.out.println("HexString.toHexStr");
			System.out.println(src.length() + "src#" + src);
			System.out.println(dst.length() + "dst#" + dst);
			System.out.println(rst.length() + "rst#" + rst);			
			fail();
		}
	}

	@Test
	public void testToHexStrByteArray() {
		String src = "0123456789 hello world\n";
		String dst = "30 31 32 33 34 35 36 37 38 39 20 68 65 6C 6C 6F 20 77 6F 72 6C 64 0A";
		String rst = HexString.toHexStr(src.getBytes());
		if (dst.compareTo(rst) != 0) {
			System.out.println(src.length() + "src#" + src);
			System.out.println(dst.length() + "dst#" + dst);
			System.out.println(rst.length() + "rst#" + rst);
			fail();
		}
	}

}
