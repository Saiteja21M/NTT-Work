package com.dbfw;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultMapper
{
	public Object mapRow(ResultSet rs) throws SQLException;
	
}
