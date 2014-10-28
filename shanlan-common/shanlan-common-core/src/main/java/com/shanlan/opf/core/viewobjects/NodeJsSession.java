package com.shanlan.opf.core.viewobjects;

import com.shanlan.user.core.domain.UserDetail;

public class NodeJsSession {

	private Cookie cookie;
	
	private UserDetail user;
	
	
	
	
	public Cookie getCookie() {
		return cookie;
	}




	public void setCookie(Cookie cookie) {
		this.cookie = cookie;
	}




	public UserDetail getUser() {
		return user;
	}




	public void setUser(UserDetail user) {
		this.user = user;
	}




	public class Cookie {

	    private Integer originalMaxAge;

	    private String expires;
	    
	    private String httpOnly;
	    
	    private String path;

		public Integer getOriginalMaxAge() {
			return originalMaxAge;
		}

		public void setOriginalMaxAge(Integer originalMaxAge) {
			this.originalMaxAge = originalMaxAge;
		}

		public String getExpires() {
			return expires;
		}

		public void setExpires(String expires) {
			this.expires = expires;
		}

		public String getHttpOnly() {
			return httpOnly;
		}

		public void setHttpOnly(String httpOnly) {
			this.httpOnly = httpOnly;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}


	   
	}
	
}
