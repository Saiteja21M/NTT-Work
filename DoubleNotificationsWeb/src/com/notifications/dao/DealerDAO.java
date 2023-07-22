package com.notifications.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.notifications.dbfw.DBFWException;
import com.notifications.dbfw.DBHelper;
import com.notifications.dbfw.ParamMapper;
import com.notifications.domain.DealerTable;

public class DealerDAO {

	static Logger log = Logger.getLogger(HistoryDAO.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<DealerTable> getDealerData(Connection con, String nv_mf_orderer_id, String orderer_domestic)
			throws DAOAppException {

		List dealerData = null;

		try {
			ParamMapper paramMapper = new ParamMapper() {

				@Override
				public void mapParams(PreparedStatement pStmt) throws SQLException {
					pStmt.setString(1, nv_mf_orderer_id);
					pStmt.setString(2, orderer_domestic);
				}
			};
			dealerData = DBHelper.executeSelect(con, SqlMapper.GET_DEALER_DATA, SqlMapper.MAP_D02_DEALER_DATA,
					paramMapper);
		} catch (DBFWException e) {
			log.error(e);
			throw new DAOAppException(e);
		}

		return dealerData;

	}

}
