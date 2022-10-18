package br.com.vaicasar.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class FotosLoja implements Serializable {

	private static final long serialVersionUID = 3568378660944498339L;
	
	@EmbeddedId
	private FotosLojaID fotosLojaId;

	@Column(name = "URL_FOTO")
	private String urlFoto;
	
	public FotosLojaID getFotosLojaId() {
		return fotosLojaId;
	}

	public void setFotosLojaId(FotosLojaID fotosLojaId) {
		this.fotosLojaId = fotosLojaId;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	
}
