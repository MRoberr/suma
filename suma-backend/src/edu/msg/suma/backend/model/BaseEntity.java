package edu.msg.suma.backend.model;

import java.io.Serializable;

public class BaseEntity extends AbstractModel implements Serializable{

	
	private static final long serialVersionUID = 8986095853766020363L;
	private Long id;
	
	public BaseEntity() {
		this(null, null);
	}
	
	public BaseEntity(Long id) {
		this(null, id);
	}
	
	public BaseEntity(String uuid, Long id) {
		super(uuid);
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
