package com.locotor.kanpm.model.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(String[].class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class StringArrayJoinTypeHandler extends BaseTypeHandler<String[]> {

    String split = ",";

    @Override
    public String[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String reString = rs.getString(columnName);
        if (reString != null && !reString.isBlank()) {
            return reString.split(split);
        }
        return null;
    }

    @Override
    public String[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String reString = rs.getString(columnIndex);
        if (reString != null && !reString.isBlank()) {
            return reString.split(split);
        }
        return null;
    }

    @Override
    public String[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String reString = cs.getString(columnIndex);
        if (reString != null && !reString.isBlank()) {
            return reString.split(split);
        }
        return null;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String[] parameter, JdbcType type)
            throws SQLException {
        ps.setString(i, String.join(split, parameter));
    }

}
