package br.com.vaicasar.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import br.com.vaicasar.enums.Status;

@MappedSuperclass
public class AuditEntity implements Serializable{
	
	private static final long serialVersionUID = -4687499194257223458L;

	@Column(name = "last_update")
	private Date lastUpdate;
	
	@Column(name = "last_update_user")
	private String lastUpdateUser;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status")
	private Status status;
	
	@PrePersist
	@PreUpdate
	public void addAudit() {
		setLastUpdate(new Date());
		setLastUpdateUser("admin@vaicasar");
		setStatus(Status.ATIVO);
	}
	
	@PreRemove
	public void removeEntity() {
		setLastUpdate(new Date());
		setLastUpdateUser("admin@vaicasar");
		setStatus(Status.INATIVO);
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	} 
	
	
}
