package br.com.vaicasar.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.vaicasar.model.entity.Usuario;

public class CronogramaDTO implements Serializable {

	private static final long serialVersionUID = 5909077840776653824L;
	
	private Usuario usuario;
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT-3")
	private Date dataPrevista;
	
	public CronogramaDTO() {
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

}
