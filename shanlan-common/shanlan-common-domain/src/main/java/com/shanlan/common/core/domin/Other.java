package com.shanlan.common.core.domin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.shanlan.common.util.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;


public class Other {
	public Other() {
		
	}
	
	public HashMap<String, String> others;
	


	
	public String[] gVals(String[] keys) {
		String[] vals = new String[keys.length];
		for (int i = 0; i < keys.length; i++) {
			vals[i] = value(keys[i]);
		}
		return vals;
	}
	
	public String value(String key) {
		if (this.others == null) {
			return "";
		}
		if (!this.others.containsKey(key)) {
			return "";
		}
		return this.others.get(key);
	}
	
	/**
	 * 模糊查询others，用于支持多模板,查出不同版本。
	 * 
	 * @param likeKey
	 * @return
	 */
	public HashMap<String, String> values(String likeKey) {
		HashMap<String, String> hash = new HashMap<String, String>();
		// if(value(likeKey).equals(""))return hash;
		for (String key : this.others.keySet()) {
			if (key.contains(likeKey)) {
				String newKey = "";
				if (key.equals(likeKey)) {
					newKey = "def";
				} else {
					newKey = StringUtils.split(key, likeKey + "_")[1];
				}
				hash.put(newKey, this.others.get(key));
			}
		}
		return hash;
	}
	
	public float floatValue(String key) {
		return Float.parseFloat(value(key));
	}
	
	public int intValue(String key) {
		return Integer.parseInt(value(key));
	}
	
	public boolean booleanValue(String key) {
		return Boolean.parseBoolean(value(key));
	}
	
	public void svalue(String key, String val) {
		if (this.others == null) {
			this.others = new HashMap<String, String>();
		}
		this.others.put(key, val);
	}
	
	public void svalue(String key, float val) {
		if (this.others == null) {
			this.others = new HashMap<String, String>();
		}
		this.others.put(key, String.valueOf(val));
	}
	
	public void svalue(String key, int val) {
		if (this.others == null) {
			this.others = new HashMap<String, String>();
		}
		this.others.put(key, String.valueOf(val));
	}
	
	public void others(String others) {
		if (others == null || !others.trim().startsWith("{")) {
			this.others = new HashMap<String, String>();
		} else {
			this.others = JsonUtil.foJson(others,
					new TypeReference<HashMap<String, String>>() {
					});
		}
	}
	
	public void merge_new(Other o) {
		if (this.others == null) {
			this.others = new HashMap<String, String>();
		}
		this.others.putAll(o.others);
	}
	
	public void merge_old(Other o) {
		if (this.others == null) {
			this.others = new HashMap<String, String>();
		}
		for (String key : o.others.keySet()) {
			if (this.others.containsKey(key)) {
				continue;
			}
			this.others.put(key, o.others.get(key));
		}
	}
	
	public Map<String, String> diff(Other o) {
		Map<String, String> no = new HashMap<String, String>();
		for (String key : this.others.keySet()) {
			String oldval = o.others.get(key);
			if (oldval == null || !oldval.equals(this.others.get(key))) {
				no.put(key, this.others.get(key));
			}
		}
		return no;
	}
	
	/**
	 * 获取 others 中的所有 key
	 * 
	 * @return
	 */
	public Set<String> ketSet() {
		return this.others.keySet();
	}
	
	public String othersJson() {
		if (this.others == null) {
			return "{}";
		}
		return JsonUtil.toJson(this.others);
	}
}