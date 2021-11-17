package jnpf.util;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司
 * @date 2021/3/16 10:51
 */
public class DesUtil {
    private static final String MD5 = "MD5";
    private static final String SHA1 = "SHA1";
    private static final String HMAC_MD_5 = "HmacMD5";
    private static final String HMAC_SHA_1 = "HmacSHA1";
    private static final String DES = "DES";
    private static final String AES = "AES";

    /**编码格式；默认使用uft-8*/
    private static String charset = "utf-8";
    /**DES*/
    private static int keysizeDES = 0;
    /**AES*/
    private static int keysizeAES = 128;

    private static String key = "jnpf";

    /**
     * 使用MessageDigest进行单向加密（无密码）
     * @param res 被加密的文本
     * @param algorithm 加密算法名称
     * @return
     */
    private static String messageDigest(String res,String algorithm){
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] resBytes = charset==null?res.getBytes():res.getBytes(charset);
            return base64(md.digest(resBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用KeyGenerator进行单向/双向加密（可设密码）
     * @param res 被加密的原文
     * @param algorithm  加密使用的算法名称
     * @param key 加密使用的秘钥
     * @return
     */
    private static String keyGeneratorMac(String res,String algorithm,String key){
        try {
            SecretKey sk = null;
            if (key==null) {
                KeyGenerator kg = KeyGenerator.getInstance(algorithm);
                sk = kg.generateKey();
            }else {
                byte[] keyBytes = charset==null?key.getBytes():key.getBytes(charset);
                sk = new SecretKeySpec(keyBytes, algorithm);
            }
            Mac mac = Mac.getInstance(algorithm);
            mac.init(sk);
            byte[] result = mac.doFinal(res.getBytes());
            return base64(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用KeyGenerator双向加密，DES/AES，注意这里转化为字符串的时候是将2进制转为16进制格式的字符串，不是直接转，因为会出错
     * @param res 加密的原文
     * @param algorithm 加密使用的算法名称
     * @param key  加密的秘钥
     * @param keysize
     * @param isEncode
     * @return
     */
    private static String keyGeneratorEs(String res, String algorithm, String key, int keysize, boolean isEncode){
        try {
            SecretKeySpec sks = null;
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(key.getBytes());
            KeyGenerator kg = KeyGenerator.getInstance(algorithm);
            if (keysize == 0) {
                kg.init(random);
            }else if (key==null) {
                kg.init(keysize);
            }else {
                kg.init(keysize, random);
            }
            SecretKey sk = kg.generateKey();
             sks = new SecretKeySpec(sk.getEncoded(), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            if (isEncode) {
                cipher.init(Cipher.ENCRYPT_MODE, sks);
                byte[] resBytes = charset==null?res.getBytes():res.getBytes(charset);
                return parseByte2HexStr(cipher.doFinal(resBytes));
            }else {
                cipher.init(Cipher.DECRYPT_MODE, sks);
                return new String(cipher.doFinal(parseHexStr2Byte(res)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String base64(byte[] res){
        return Base64.encode(res);
    }

    /**将二进制转换成16进制 */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    /**将16进制转换为二进制*/
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * md5加密算法进行加密（不可逆）
     * @param res 需要加密的原文
     * @return
     */
    public static String md5(String res) {
        return messageDigest(res, MD5);
    }

    /**
     * md5加密算法进行加密（不可逆）
     * @param res  需要加密的原文
     * @param key  秘钥
     * @return
     */
    public static String md5(String res, String key) {
        return keyGeneratorMac(res, HMAC_MD_5, key);
    }

    /**
     * 使用SHA1加密算法进行加密（不可逆）
     * @param res 需要加密的原文
     * @return
     */
    public static String sha1(String res) {
        return messageDigest(res, SHA1);
    }

    /**
     * 使用SHA1加密算法进行加密（不可逆）
     * @param res 需要加密的原文
     * @param key 秘钥
     * @return
     */
    public static String sha1(String res, String key) {
        return keyGeneratorMac(res, HMAC_SHA_1, key);
    }

    /**
     * 使用DES加密算法进行加密（可逆）
     * @param res 需要加密的原文
     * @return
     */
    public static String desEncode(String res) {
        return keyGeneratorEs(res, DES, key, keysizeDES, true);
    }

    /**
     * 对使用DES加密算法的密文进行解密（可逆）
     * @param res 需要解密的密文
     * @return
     */
    public static String desDecode(String res) {
        return keyGeneratorEs(res, DES, key, keysizeDES, false);
    }

    /**
     * 使用AES加密算法经行加密（可逆）
     * @param res 需要加密的密文
     * @return
     */
    public static String aesEncode(String res) {
        return keyGeneratorEs(res, AES, key, keysizeAES, true);
    }

    /**
     * 对使用AES加密算法的密文进行解密
     * @param res 需要解密的密文
     * @return
     */
    public static String aesDecode(String res) {
        return keyGeneratorEs(res, AES, key, keysizeAES, false);
    }

    /**
     * 使用Base64进行加密
     * @param res 密文
     * @return
     */
    public static String base64Encode(String res) {
        return Base64.encode(res.getBytes());
    }


    /**
     * 使用Base64进行解密
     * @param res
     * @return
     */
    public static String base64Decode(String res) {
        return new String(Base64.decode(res));
    }

}
