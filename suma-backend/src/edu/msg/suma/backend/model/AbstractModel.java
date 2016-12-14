package edu.msg.suma.backend.model;

import java.util.UUID;

public class AbstractModel {

	private String uuid;

	public AbstractModel(String uuid) {
		
		this.uuid = uuid;
	}
	
	public String getUUID() {
		
		if(uuid == null) {
			
			uuid = (UUID.randomUUID()).toString();
		}
		return uuid;
	}

	public void setUUID(String uUID) {
		
		uuid = uUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getUUID() == null) ? 0 : getUUID().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractModel other = (AbstractModel) obj;
		if (getUUID() == null) {
			if (other.getUUID() != null)
				return false;
		} else if (!getUUID().equals(other.getUUID()))
			return false;
		return true;
	}
	
}
