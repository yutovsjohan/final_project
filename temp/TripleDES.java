package epay.Encript;

import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TripleDES {

	public static String Encrypt(String plainText, String key) {
		try {
			byte[] arrayBytes = getValidKey(key);
			KeySpec ks = new DESedeKeySpec(arrayBytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			SecretKey seckey = skf.generateSecret(ks);
			//
			cipher.init(Cipher.ENCRYPT_MODE, seckey);
			byte[] plainByte = plainText.getBytes("UTF8");
			byte[] encryptedByte = cipher.doFinal(plainByte);
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(encryptedByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String Decrypt(String encryptData, String key) {
		try {
			byte[] arrayBytes = getValidKey(key);
			KeySpec ks = new DESedeKeySpec(arrayBytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			SecretKey seckey = skf.generateSecret(ks);
			//
			cipher.init(Cipher.DECRYPT_MODE, seckey);
			BASE64Decoder decode = new BASE64Decoder();
			byte[] encryptByte = decode.decodeBuffer(encryptData);
			byte[] plainByte = cipher.doFinal(encryptByte);
			return new String(plainByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private static byte[] getValidKey(String key) {
		try {
			String sTemp = MD5.hash(key).substring(0,24);
			return sTemp.getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		String plainText = "thach";
		String key = "12345";
		String cipherText = Encrypt(plainText, key);
		System.out.println("Cipher text: " + cipherText);
		System.out.println("Plain text: " + Decrypt(cipherText, key));
	}
}