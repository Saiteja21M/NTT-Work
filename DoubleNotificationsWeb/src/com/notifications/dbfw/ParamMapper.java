package com.notifications.dbfw;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParamMapper {

	public void mapParams(PreparedStatement pStmt) throws SQLException;
}
