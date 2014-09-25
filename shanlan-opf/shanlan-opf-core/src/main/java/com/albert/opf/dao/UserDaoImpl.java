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

import com.shanlan.common.domain.User;
import com.shanlan.common.util.DateUtil;

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
public class UserDaoImpl implements UserDao {

	public static final String DEF_USER_BY_USERNAME_QUERY = "Select USER_ACCOUNT,USER_PASSWORD,NAME,EMAIL,CITY,ISVALID "
			+ "from KS_IDENTITY " + "where USER_ACCOUNT = ?";

	public static final String DEF_USER_ADD = "Insert Into KS_IDENTITY (IDENTITY_TYPE,USER_ACCOUNT,USER_PASSWORD,"
			+ "EMAIL,NAME,CITY,ISVALID,ABOLISH_DATE,VERSION,SERIAL_NUMBER,SORT_ORDER,IS_SUPER,CREATE_DATE) "
			+ "Values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private JdbcTemplate jdbcTemplate;

	/**
	 *
	 */
	public UserDaoImpl() {

	}

	@Override
	public List<User> getUserByUserName(String userName) {

		return jdbcTemplate.query(DEF_USER_BY_USERNAME_QUERY,
				new String[] { userName }, new RowMapper<User>() {
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return new User(rs.getString(1), rs.getString(2), rs
								.getString(3), rs.getString(4), rs.getString(5),rs
								.getBoolean(6));
					}
				});
	}

	@Override
	public boolean add(User user) {
		String now=DateUtil.getNow(DateUtil.format1);
		int insertNum = jdbcTemplate.update(DEF_USER_ADD,"User",
				user.getUserName(), user.getPassword(), user.getEmail(),
				user.getNickName(), user.getCity(), user.getIsValid(),"8888-01-01 00:00:00","3","0","0","0",now);

		if (insertNum == 1) {
			return true;
		}

		return false;
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
