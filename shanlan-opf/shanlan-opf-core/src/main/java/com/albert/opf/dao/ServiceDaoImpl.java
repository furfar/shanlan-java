/**
 *
 * Copyright(C) 2012-2016 All Rights
 * Reserved.
 */
package com.albert.opf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.albert.opf.common.model.domain.Service;

/**
 * @ClassName:ServiceDaoImpl
 * @Description: TODO
 * @Author Albert
 * @Date:2013-2-4 下午9:48:32
 * 
 * @Remarks:
 * @Version:V1.1
 */

@Component
public class ServiceDaoImpl implements ServiceDao {

	public static final String DEF_SERVICES_BY_SERVICENAMEANDVERSION_QUERY = "select serviceName,version,serviceType,serviceURL,enable "
			+ "from service " + "where serviceName = ? and version= ? ";

	private String getServicesByServiceNameAndVersionQuery;

	private JdbcTemplate jdbcTemplate;

	/**
	 *
	 */
	public ServiceDaoImpl() {
		getServicesByServiceNameAndVersionQuery = DEF_SERVICES_BY_SERVICENAMEANDVERSION_QUERY;
	}

	@Override
	public List<Service> getServicesByServiceNameAndVersion(String serviceName,
			String version) {

		return jdbcTemplate.query(getServicesByServiceNameAndVersionQuery,
				new String[] { serviceName, version },
				new RowMapper<Service>() {
					public Service mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String serviceName = rs.getString(1);
						String version = rs.getString(2);
						Integer serviceType = rs.getInt(3);
						String serviceURL = rs.getString(4);
						int enable = rs.getInt(5);
						return new Service(serviceName, version, serviceURL,
								serviceType, enable);
					}
				});
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
