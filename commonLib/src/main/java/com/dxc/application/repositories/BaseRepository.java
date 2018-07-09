package com.dxc.application.repositories;

import java.util.List;
import java.util.Locale;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.dxc.application.constants.MessagesConstants;
import com.dxc.application.exceptions.ApplicationException;

public abstract class BaseRepository extends NamedParameterJdbcDaoSupport {
	
	@Autowired
	protected void setJdbcDaoSupportDataSource(DataSource appDataSource) {
		Locale.setDefault(new Locale("en", "US"));
		super.setDataSource(appDataSource);
	}
	public <T> List<T> query(String sql, SqlParameterSource param, RowMapper<T> mapper) throws ApplicationException, DataAccessException, Exception {

		RowCountCallbackHandler rowCountHandler = new RowCountCallbackHandler();

		getNamedParameterJdbcTemplate().query(sql, param, rowCountHandler);
		logger.debug("Total SQL : " + sql);
		StringBuffer maxSql = new StringBuffer();
		maxSql.append("SELECT GIM_VALUE FROM TB_M_GIM_D ");
		maxSql.append(" WHERE  GIM_TYPE = 'SYS_PARAM' ");
		maxSql.append(" AND GIM_CD  = 'MAX_RECORD' ");
		logger.debug("Count SQL : " + maxSql.toString());

		SqlRowSet srs = getJdbcTemplate().queryForRowSet(maxSql.toString());
		srs.next();
		int totalRecord = rowCountHandler.getRowCount();
		int limitRecord = Integer.parseInt(srs.getString(1));

		logger.info("Total Record : " + totalRecord);
		logger.info("limit Record : " + limitRecord);

		if (totalRecord > limitRecord) {
			throw new ApplicationException(MessagesConstants.SEARCH_EXCEED_MAX_ROW, new String[] { limitRecord + "" });
		} else {
			return getNamedParameterJdbcTemplate().query(sql, param, mapper);
		}
	}
}
