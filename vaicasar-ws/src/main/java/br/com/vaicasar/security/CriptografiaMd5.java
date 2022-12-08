package br.com.vaicasar.security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CriptografiaMd5 {

	protected static final Logger log = LoggerFactory.getLogger(CriptografiaMd5.class);

	private static String encrypt(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		md.update(str.getBytes("ISO-8859-1"));
		byte[] hash = md.digest();

		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			if ((0xff & hash[i]) < 0x10) {
				hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
			} else {
				hexString.append(Integer.toHexString(0xFF & hash[i]));
			}
		}

		return hexString.toString();
	}

	public static String encript(String email, String senha) {
		try {
			String auxSenha = email + senha;
			return encrypt(auxSenha.trim());
		} catch (NoSuchAlgorithmException ex) {
			log.error(ex.getMessage());
			return null;
		} catch (UnsupportedEncodingException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

}
