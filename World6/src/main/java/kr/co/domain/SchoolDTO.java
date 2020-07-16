package kr.co.domain;

import java.io.Serializable;

public class SchoolDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sid;
	private String sname;
	private String slocation;
	
	
	public SchoolDTO() {
		// TODO Auto-generated constructor stub
	}


	public SchoolDTO(String sid, String sname, String slocation) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.slocation = slocation;
	}


	public String getSid() {
		return sid;
	}


	public void setSid(String sid) {
		this.sid = sid;
	}


	public String getSname() {
		return sname;
	}


	public void setSname(String sname) {
		this.sname = sname;
	}


	public String getSlocation() {
		return slocation;
	}


	public void setSlocation(String slocation) {
		this.slocation = slocation;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sid == null) ? 0 : sid.hashCode());
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
		SchoolDTO other = (SchoolDTO) obj;
		if (sid == null) {
			if (other.sid != null)
				return false;
		} else if (!sid.equals(other.sid))
			return false;
		return true;
	}
	
	

}
