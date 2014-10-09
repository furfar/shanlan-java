package com.shanlan.opf.infra.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.shanlan.user.core.domain.UserBase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.shanlan.common.util.DateUtil;
import com.shanlan.opf.core.repository.UserRepository;

/**
 * @ClassName:ServiceDaoImpl
 * @Description: TODO
 * @Author Albert
 * @Date:2013-2-4 下午9:48:32
 * 
 * @Remarks:
 * @Version:V1.1
 */

@Named
public class UserRespositoryImpl implements UserRepository {

	public static final String DEF_USER_BY_USERNAME_QUERY = "Select USER_ACCOUNT,USER_PASSWORD,NAME,EMAIL,ISVALID "
			+ "from KS_IDENTITY " + "where USER_ACCOUNT = ?";

    public static final String DEF_USER_BY_EMAIL_QUERY = "Select USER_ACCOUNT,USER_PASSWORD,NAME,EMAIL,ISVALID "
            + "from KS_IDENTITY " + "where EMAIL = ?";

	public static final String DEF_USER_ADD = "Insert Into KS_IDENTITY (IDENTITY_TYPE,USER_ACCOUNT,USER_PASSWORD,"
			+ "EMAIL,NAME,ISVALID,ABOLISH_DATE,VERSION,SERIAL_NUMBER,SORT_ORDER,IS_SUPER,CREATE_DATE) "
			+ "Values (?,?,?,?,?,?,?,?,?,?,?,?)";

    @Inject
	private JdbcTemplate jdbcTemplate;


	@Override
	public List<UserBase> getUserByUserName(String userName) {

		return jdbcTemplate.query(DEF_USER_BY_USERNAME_QUERY,
				new String[] { userName }, new RowMapper<UserBase>() {
					public UserBase mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return new UserBase(rs.getString(1), rs.getString(2), rs
								.getString(3), rs.getString(4),rs
								.getInt(5));
					}
				});
	}

	@Override
	public boolean add(UserBase user) {
		String now=DateUtil.getNow(DateUtil.format1);
		int insertNum = jdbcTemplate.update(DEF_USER_ADD,"User",
				user.getUserName(), user.getPassword(), user.getEmail(),
				user.getNickName(), user.getIsValid(),"8888-01-01 00:00:00","3","0","0","0",now);

		if (insertNum == 1) {
			return true;
		}

		return false;
	}

    @Override
    public List<UserBase> getUserByEmail(String email) {
        return jdbcTemplate.query(DEF_USER_BY_EMAIL_QUERY,
                new String[] { email }, new RowMapper<UserBase>() {
                    public UserBase mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        return new UserBase(rs.getString(1), rs.getString(2), rs
                                .getString(3), rs.getString(4),rs
                                .getInt(5));
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
