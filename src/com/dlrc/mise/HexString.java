package com.dlrc.mise;

/**
 * ʮ�������ַ�����ʽ��
 * @author heavy
 *
 */
public class HexString {
	private static final String[] DIGITS_UPPER =
        {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
	/**
	 * ʮ�������ַ���ת��Ϊ�ֽ����顣��ʽΪ
	 * 	String[] srcArr = { 
	 *		"30 31 32 33 34 35363738390A20 68 65 6c 6c 6f 20 77 6f 72 6c 64 0a", 
	 *		"0x30 0x31 0X32 0x33 0X34 0X35 0X36 0X37 0X38 0X39 0X0A 0X20 0X68 0X65 0X6c 0X6c 0X6f 20 77 6f 72 6c 64 0a", 
     *		"303132333435363738390a2068656c6c6f20776f726c640a", 
	 *		"30, 31, 32, 33,   34 3536373839 A 20 68 65 6c 6c 6f 20 77 6f 72 6c 64 A", 
	 *		"30, 31, 32, 33,   34 3536373839 a 20 68 65 6c 6c 6f 20 77 6f 72 6c 64 a"};
     *
	 *	����ת��ΪString dst = "0123456789\n hello world\n";
	 *
	 * @param hexstr 16������ʾ���ַ���
	 * @return �ֽ�����
	 */
	public static byte[] toByte(String hexstr) {
		StringBuilder retstr = new StringBuilder();
		char high = 0;
		final int hexstrLen = hexstr.length();
		for (int i = 0; i < hexstrLen; i++) {
			char c = hexstr.charAt(i);
			if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f')
					|| (c >= 'A' && c <= 'F')) {
				// ����
				if (high > 0) {
					retstr.append((char) (0xff & Integer
							.decode("0x" + high + c)));
					high = 0;
				} else {
					high = c;
				}
			} else {
				//���ֽڵ�ֵ
				if (high > 0 && c != 'X' && c != 'x')
					retstr.append((char) (0xff & Integer
							.decode("0x" + high)));
				
				// �ָ���
				high = 0;
			}
		}
		if (high > 0) {
			retstr.append((char) (0xff & Integer.decode("0x" + high)));
		}
		return retstr.toString().getBytes();
	}

	/**
	 * �ֽ�����ת��Ϊ16�����ַ���
	 * @param pre 16�����ַ���ǰ׺
	 * @param separtor 16�����ַ��ķָ�����
	 * @return 16������ʾ���ַ���
	 */
	public static String toHexStr(byte[] b, String pre, String separtor) {
		StringBuilder retstr = new StringBuilder();
		for (byte i : b) {
			//String tmp = Integer.toHexString(0xff & (int) i);
			retstr.append(pre + DIGITS_UPPER[(0xF0 & i) >> 4] + DIGITS_UPPER[0x0F & i] + separtor);
		}
		String dst = retstr.toString();
		dst = dst.substring(0, dst.length() - separtor.length()); 
		return dst;
	}
	
	/**
	 * �ֽ�����ת��Ϊ16������ʾ���ַ���
	 * @param b �ֽ�����
	 * @param separtor �ָ�����
	 * @return �ֽ������16������ʾ���ַ���
	 */
	public static String toHexStr(byte[] b, String separtor) {
		return toHexStr(b, "", separtor);
	}

	/**
	 * �ֽ�����ת��Ϊ16������ʾ���ַ���, �ָ���Ϊ�ո�
	 *  ���磺
	 * 	String src = "0123456789 hello world\n";
	 *  ת��Ϊ
	 * 	String dst = "30 31 32 33 34 35 36 37 38 39 20 68 65 6C 6C 6F 20 77 6F 72 6C 64 0A";
	 * @param b 
	 * @return 16������ʾ���ַ���
	 */
	public static String toHexStr(byte[] b) {
		return toHexStr(b, " ");
	}
}
