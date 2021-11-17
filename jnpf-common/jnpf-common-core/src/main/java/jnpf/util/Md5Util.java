
package jnpf.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司
 * @date 2021/3/16 10:51
 */
public class Md5Util {
	/**
	 * 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符,Apache校验下载的文件的正确性用的就是默认的这个组合
	 */
	protected static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e','f' };
	protected static MessageDigest messagedigest = null;
	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取文件MD5值
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getFileMd5String(File file) throws IOException {
		InputStream fis;
		fis = new FileInputStream(file);
		byte[] buffer = new byte[1024];
		int numRead = 0;
		while ((numRead = fis.read(buffer)) > 0) {
			messagedigest.update(buffer, 0, numRead);
		}
		fis.close();
		return bufferToHex(messagedigest.digest());
	}

	/**
	 * 密码字符串MD5加密 32位小写
	 * @param str
	 * @return
	 */
	public static String getStringMd5(String str) {
		//MessageDigest是线程不安全的，新建实例保证不出错
		MessageDigest messagedigest= null;
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (StringUtil.isEmpty(str)) {
			return "";
		}
		byte[] buffer = str.getBytes();
		messagedigest.update(buffer);
		return bufferToHex(messagedigest.digest());
	}

	public static String bufferToHex(byte[] bytes) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte[] bytes, int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	private static final String toHex(byte[] hash) {
		if (hash == null) {
			return null;
		}
		StringBuffer buf = new StringBuffer(hash.length * 2);
		int i;

		for (i = 0; i < hash.length; i++) {
			if ((hash[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString(hash[i] & 0xff, 16));
		}
		return buf.toString();
	}

	public static String hash(String s) {
		try {
			return new String(toHex(getStringMd5(s).getBytes(Constants.UTF8)).getBytes(Constants.UTF8), Constants.UTF8);
		} catch (Exception e) {
			return s;
		}
	}
}
