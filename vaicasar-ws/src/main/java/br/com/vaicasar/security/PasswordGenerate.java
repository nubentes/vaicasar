package br.com.vaicasar.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerate {
	
	private static Logger log = LoggerFactory.getLogger(PasswordGenerate.class);
	
	private PasswordGenerate() {}
	
//	public static void main(String[] args) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String email = "demelo@vaicasar.com";
//		String senha = "123456";
//		
//		String encodedPassword = encoder.encode(email + senha);
//		String md5Password = new CustomPasswordEncoder().encodeMD5(email + senha);
//		log.info(md5Password);
//		log.info(encodedPassword);
//		log.info("update USUARIO set SENHA = '"+ md5Password+ "' where EMAIL = '" + email + "'");
//		String resultado = Boolean.toString(encoder.matches(senha, encodedPassword));
//		log.info(resultado);
//
//	}

}
