package br.com.vaicasar.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FotosLojaID implements Serializable {
	
	
	private static final long serialVersionUID = -2407897208596903251L;

	@Column(name = "ID_FOTOS_LOJA")
	private Long idFotosLoja;
	
	@Column(name = "ID_LOJA")
	private Loja idLoja;
	
	public Long getIdFotosLoja() {
		return idFotosLoja;
	}

	public void setIdFotosLoja(Long idFotosLoja) {
		this.idFotosLoja = idFotosLoja;
	}

	public Loja getIdLoja() {
		return idLoja;
	}

	public void setIdLoja(Loja idLoja) {
		this.idLoja = idLoja;
	}

}
