package com.notifications.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.notifications.dbfw.DBFWException;
import com.notifications.dbfw.DBHelper;
import com.notifications.dbfw.ParamMapper;
import com.notifications.domain.OrderingTable;

/**
 * @author SaiTeja Koppala
 */

public class OrderingDAO {

	static Logger log = Logger.getLogger(OrderingDAO.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<OrderingTable> getOrderingTableData(Connection con, int nv_ws_order_id) throws DAOAppException {

		List orderTableData = null;

		try {
			ParamMapper paramMapper = new ParamMapper() {

				@Override
				public void mapParams(PreparedStatement pStmt) throws SQLException {
					pStmt.setInt(1, nv_ws_order_id);
				}
			};
			orderTableData = DBHelper.executeSelect(con, SqlMapper.GET_ORDERING_TABLE_DATA,
					SqlMapper.MAP_O01_ORDER_DATA, paramMapper);
		} catch (DBFWException e) {
			log.error(e);
			throw new DAOAppException(e);
		}

		return orderTableData;
	}

}
