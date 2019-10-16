package com.ait.pa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

public class PaDistinct {
    private static ServiceLocator services;

    public PaDistinct() {
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException ex) {
        }
    }

    /*
     * 
     * data list no pagecontrol
     * 
     */

    public Vector getPaDistinctList() {
        Connection con = null;
        PreparedStatement ps = null;
        PaDistinct padistinct = null;
        Vector v = new Vector();
        String sql = " Select " + " FIELD_NAME,FIELD_EN_NAME,FIELD_KOR_NAME, " + " DISTINCT_FIELD," + " ID "
                + " From PA_DISTINCT_LIST "
                + " where not (FIELD_NAME ='(CONST)'or FIELD_NAME ='(const)') and activity=1 and table_name='PA_HR_V'"
                + " order by ID";
        ResultSet rs = null;
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                padistinct = new PaDistinct();
                padistinct.setField_name(rs.getString("FIELD_NAME"));
                padistinct.setFieldEnName(rs.getString("FIELD_EN_NAME"));
                padistinct.setFieldKorName(rs.getString("FIELD_KOR_NAME"));
                padistinct.setDistinct_field(rs.getString("DISTINCT_FIELD"));
                padistinct.setId(rs.getInt("ID"));
                v.add(padistinct);
            }
            return v;
        } catch (Exception ex) {
            Logger.getLogger(getClass()).debug("PaDistinctlist =" + ex.getMessage());
            return null;
        } finally {
            SqlUtil.close(rs, ps, con);
        }
    }

    private int id;

    private String field_name;
    
    private String fieldEnName;
    
    private String fieldKorName;

    private String distinct_field;

    private String distinct_text_field;
    
	public static ServiceLocator getServices() {
		return services;
	}

	public static void setServices(ServiceLocator services) {
		PaDistinct.services = services;
	}

	

	public String getFieldEnName() {
		return fieldEnName;
	}

	public void setFieldEnName(String fieldEnName) {
		this.fieldEnName = fieldEnName;
	}

	public String getFieldKorName() {
		return fieldKorName;
	}

	public void setFieldKorName(String fieldKorName) {
		this.fieldKorName = fieldKorName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public String getDistinct_field() {
		return distinct_field;
	}

	public void setDistinct_field(String distinct_field) {
		this.distinct_field = distinct_field;
	}

	public String getDistinct_text_field() {
		return distinct_text_field;
	}

	public void setDistinct_text_field(String distinct_text_field) {
		this.distinct_text_field = distinct_text_field;
	}

	

}