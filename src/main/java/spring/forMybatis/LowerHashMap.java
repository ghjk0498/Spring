package spring.forMybatis;

import java.util.HashMap;

import org.springframework.jdbc.support.JdbcUtils;

public class LowerHashMap extends HashMap {
	
	private static final long serialVersionUID = -870381775497187463L;

	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return super.put(JdbcUtils.convertUnderscoreNameToPropertyName((String) key), value);
	}
}
