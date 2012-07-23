package tw.elliot.domain.core;

import java.io.Serializable;

public interface Identifiable<Oid extends Serializable> extends Serializable {
	/**
	 * Returns the id of the entity.
	 *
	 * @return the object id
	 */
	Oid getOid();
	
	void setOid(Oid oid);
}