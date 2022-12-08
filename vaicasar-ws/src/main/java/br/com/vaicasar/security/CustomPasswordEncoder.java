package br.com.vaicasar.security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomPasswordEncoder extends BCryptPasswordEncoder {

	private static final String BCRYPT = "$2a$";

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (StringUtils.isBlank(rawPassword) || StringUtils.isBlank(encodedPassword)) {
			return false;
		}

		if (encodedPassword.equals(rawPassword)) {
			return true;
		}

		if (encodedPassword.contains(BCRYPT)) {
			return super.matches(rawPassword, encodedPassword);
		} else {
			return encodedPassword.equals(encodeMD5(rawPassword.toString()));
		}
	}

	public String encodeMD5(String password) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(password.getBytes("ISO-8859-1"));
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
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			return password;
		}
	}

}
