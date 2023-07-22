package com.notifications.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.notifications.dbfw.DBFWException;
import com.notifications.dbfw.DBHelper;
import com.notifications.dbfw.ParamMapper;
import com.notifications.domain.HistoryTable;

public class HistoryDAO {

	static Logger log = Logger.getLogger(HistoryDAO.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<HistoryTable> getHistoryTableDataByTimestamp(Connection con, int nv_ws_order_id, String timestamp)
			throws DAOAppException {
		List historyTableData = null;

		try {
			ParamMapper paramMapper = new ParamMapper() {

				@Override
				public void mapParams(PreparedStatement pStmt) throws SQLException {
					pStmt.setInt(1, nv_ws_order_id);
					pStmt.setString(2, timestamp);
				}
			};
			historyTableData = DBHelper.executeSelect(con, SqlMapper.GET_HISTOTY_TABLE_DATA_BY_TS,
					SqlMapper.MAP_H01_ORDER_DATA, paramMapper);
		} catch (DBFWException e) {
			log.error(e);
			throw new DAOAppException(e);
		}

		return historyTableData;
	}
}
