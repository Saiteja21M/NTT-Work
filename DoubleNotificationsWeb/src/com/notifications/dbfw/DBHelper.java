package com.notifications.dbfw;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class DBHelper {

	private DBHelper() {

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List executeSelect(Connection conn, final String sqlStmt, ResultMapper outMap) throws DBFWException {
		List resultList = new ArrayList();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlStmt);
			while (rs.next()) {
				Object obj = outMap.mapRows(rs);
				resultList.add(obj);
			}
		} catch (SQLException e) {
			throw new DBFWException("Execution of select statement failed", e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();

			} catch (SQLException e) {
				throw new DBFWException("Couldn't release the resource", e);
			}

		}

		return resultList;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List executeSelect(Connection con, String sqlStmt, ResultMapper outMapper, ParamMapper inMapper)
			throws DBFWException {
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		List result = new ArrayList();
		try {
			pStmt = con.prepareStatement(sqlStmt);
			inMapper.mapParams(pStmt);

			rs = pStmt.executeQuery();
			while (rs.next()) {
				Object object = outMapper.mapRows(rs);
				result.add(object);
			}

		} catch (SQLException e) {

		} finally {
			try {
				rs.close();
				pStmt.close();
			} catch (SQLException | NullPointerException e) {
				throw new DBFWException(e);
			}
		}
		return result;
	}

	public static int executeUpdate(Connection con, String sqlStmt, ParamMapper inMapper) throws DBFWException {
		PreparedStatement pStmt = null;
		int res = -1;

		try {
			pStmt = con.prepareStatement(sqlStmt);
			inMapper.mapParams(pStmt);
			res = pStmt.executeUpdate();

		} catch (SQLException e) {

			throw new DBFWException(e);
		} finally {
			try {
				pStmt.close();
			} catch (SQLException e) {
				throw new DBFWException(e);
			}
		}

		return res;

	}

	public static Object executeProc(Connection conn, String sqlStmt, ParamMapper inParam, OutTypeMapper outType,
			OutParamMapper outParam) throws SQLException {
		Object res = null;
		if ((outType == null && outParam != null) || (outType != null && outParam == null)) {
			return null;
		}
		CallableStatement callStmt = conn.prepareCall(sqlStmt);
		inParam.mapParams(callStmt);

		if (outType != null) {
			outType.mapOutType(callStmt);
		}
		callStmt.execute();
		if (outParam != null) {
			res = outParam.mapOutParam(callStmt);
		}

		return res;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List unpackResultset(ResultSet rs, ResultMapper outMap) throws DBFWException {
		List resultList = new ArrayList();
		try {
			while (rs.next()) {
				Object obj = outMap.mapRows(rs);
				resultList.add(obj);
			}
		} catch (SQLException e) {
			throw new DBFWException("Unpacking Resultset failed", e);
		}
		return resultList;
	}

}
