package br.com.vaicasar.enums;

public enum Status {
	INATIVO, ATIVO;
	
	public static Status getStatus(Integer ordinal) {
		if (ordinal == null) {
			return null;
		}
		
		for (Status tipo : Status.values()) {
			if(tipo.ordinal() == ordinal) {
				return tipo;
			}
		}
		
		return null;
	}
}
