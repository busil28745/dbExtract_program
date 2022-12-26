package com.java.dbeExtract;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class ConnectionDb {
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public HashMap<String, ArrayList<ArrayList<String>>> connectDB(String DB_TYPE, String DB_HOST, String DB_PORT, String DB_USERNAME,
			String DB_PASSWORD, String SCHEMA, String DBDB) {
		String DB_DRIVER_CLASS = null;
		String DB_URL = null;

		HashMap<String, ArrayList<ArrayList<String>>> infoMap = new HashMap<>();

		if (DB_TYPE.equals("mariadb")) {
			DB_DRIVER_CLASS = "org." + DB_TYPE + ".jdbc.Driver";
			DB_URL = "jdbc:" + DB_TYPE + "://" + DB_HOST + ":" + DB_PORT + "/" + SCHEMA;
		} else if (DB_TYPE.equals("mysql")) {
			DB_DRIVER_CLASS = "com." + DB_TYPE + ".cj.jdbc.Driver";
			DB_URL = "jdbc:" + DB_TYPE + "://" + DB_HOST + ":" + DB_PORT + "/" + SCHEMA;
		} else if (DB_TYPE.equals("postgresql")) {
			DB_DRIVER_CLASS = "org.postgresql.Driver";
			DB_URL = "jdbc:" + DB_TYPE + "://" + DB_HOST + ":" + DB_PORT + "/" + DBDB;
		}
//		DB_USERNAME = "uvms_gw_dev";
//		DB_PASSWORD = "p@ssw0rd!Q@W";
//		DB_DRIVER_CLASS = "org.mariadb.jdbc.Driver";
//		DB_URL = "jdbc:mariadb://192.168.0.100:3306/uvms2_dev";

		try {
			System.out.println("DB_DRIVER_CLASS : " + DB_DRIVER_CLASS);
			System.out.println("DB_URL : " + DB_URL);

			Properties properties = new Properties();
			properties.put("user", DB_USERNAME);
			properties.put("password", DB_PASSWORD);
			properties.put("characterEncoding", "UTF-8");

			Class.forName(DB_DRIVER_CLASS);
			connection = DriverManager.getConnection(DB_URL, properties);
			System.out.println("연결성공");

			StringBuffer sql = new StringBuffer();
			if (!DB_TYPE.equals("postgresql")) {
				sql.append(
						"SELECT distinct c.TABLE_NAME, t.TABLE_COMMENT,c.ORDINAL_POSITION, c.COLUMN_NAME ,c.COLUMN_COMMENT ,c.COLUMN_TYPE, c.CHARACTER_MAXIMUM_LENGTH ,c.IS_NULLABLE ,c.COLUMN_KEY, c.COLUMN_DEFAULT "
								+ "FROM information_schema.columns as c join information_schema.TABLES as t on c.TABLE_NAME = t.table_name "
								+ "WHERE c.table_schema = '" + SCHEMA + "' order by c.TABLE_NAME, c.ORDINAL_POSITION");
			} else {
				sql.append(
						"SELECT DISTINCT tbl.relname AS TABLE_NAME, tbl_dec.description AS TABLE_COMMENT, col.attnum AS ORDINAL_POSITION, col.attname AS COLUMN_NAME, col_dec.description AS COLUMN_COMMENT, col_att.udt_name AS COLUMN_TYPE, col_att.character_maximum_length AS CHARACTER_MAXIMUM_LENGTH, col_att.is_nullable AS IS_NULLABLE, t_index.constraint_type AS COLUMN_KEY, col_att.column_default AS COLUMN_DEFAULT "
								+ "FROM ( SELECT * FROM PG_STAT_USER_TABLES WHERE 1 = 1 " + "AND schemaname = '" + SCHEMA
								+ "') tbl LEFT JOIN PG_DESCRIPTION AS tbl_dec "
								+ "ON tbl_dec.objsubid = 0 AND tbl.relid = tbl_dec.objoid LEFT JOIN PG_ATTRIBUTE col ON tbl.relid = col.ATTRELID LEFT JOIN PG_DESCRIPTION col_dec ON col_dec.objsubid <> 0 AND col_dec.objoid  = tbl.relid AND col_dec.objoid = col.attrelid AND col_dec.objsubid = col.attnum "
								+ "LEFT JOIN INFORMATION_SCHEMA.COLUMNS col_att " + "ON " + "col_att.table_schema = tbl.schemaname "
								+ "AND col_att.table_name = tbl.relname " + "AND col_att.column_name = col.attname "
								+ "AND col_att.ordinal_position = col.attnum " + "LEFT JOIN  " + "( " + "SELECT "
								+ "t_con.table_schema    table_schema " + ",t_con.table_name      table_name " + ",t_colu.column_name   column_name "
								+ ",t_con.constraint_name constraint_name " + ",t_con.constraint_type   constraint_type " + "FROM "
								+ "INFORMATION_SCHEMA.TABLE_CONSTRAINTS t_con " + ", " + "INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE t_colu "
								+ "WHERE " + "t_con.table_catalog    = t_colu.table_catalog " + "AND t_con.table_schema    = t_colu.table_schema "
								+ "AND t_con.table_name       = t_colu.table_name " + "AND t_con.constraint_name = t_colu.constraint_name) t_index "
								+ "ON " + "t_index.table_schema        = tbl.schemaname " + "AND t_index.table_name    = tbl.relname "
								+ "AND t_index.column_name = col.attname " + "WHERE " + "1 = 1 " + "AND col.attstattarget = '-1' " + "ORDER BY "
								+ "tbl.relname, " + "col.attnum;");
			}

			pstmt = connection.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			ArrayList<ArrayList<String>> valueInfoList = new ArrayList<ArrayList<String>>();

			if (!DB_TYPE.equals("postgresql")) {
				while (rs.next()) {
					ArrayList<String> infoList = new ArrayList<String>();
					int index = 1;
					String TABLE_NAME = rs.getString(index++);
					String TABLE_COMMENT = rs.getString(index++);
					String ORDINAL_POSITION = rs.getString(index++);
					String COLUMN_NAME = rs.getString(index++);
					String COLUMN_COMMENT = rs.getString(index++);
					String COLUMN_TYPE = rs.getString(index++);
					int CHARACTER_MAXIMUM_LENGTH = rs.getInt(index++); //데이터 타입에 따른 크기 계산
					if (COLUMN_TYPE.equals("datetime") || COLUMN_TYPE.contains("decimal") || COLUMN_TYPE.contains("bigint")) {
						CHARACTER_MAXIMUM_LENGTH = 8;
					} else if (COLUMN_TYPE.contains("int") || COLUMN_TYPE.equals("timestamp")) {
						CHARACTER_MAXIMUM_LENGTH = 4;
					} else if (COLUMN_TYPE.equals("time") || COLUMN_TYPE.equals("date")) {
						CHARACTER_MAXIMUM_LENGTH = 3;
					}

					String IS_NULLABLE = rs.getString(index++);
					if (IS_NULLABLE.equals("NO")) {
						IS_NULLABLE = "N";
					} else if (IS_NULLABLE.equals("YES")) {
						IS_NULLABLE = "Y";
					}
					String COLUMN_KEY = rs.getString(index++);
					String COLUMN_DEFAULT = rs.getString(index++);
					if (COLUMN_DEFAULT == null) {
						COLUMN_DEFAULT = " ";
					}
					infoList.add(TABLE_NAME.toUpperCase());
					infoList.add(TABLE_COMMENT);
					infoList.add(ORDINAL_POSITION);
					infoList.add(COLUMN_NAME);
					infoList.add(COLUMN_COMMENT);
					infoList.add(COLUMN_TYPE);
					infoList.add(Integer.toString(CHARACTER_MAXIMUM_LENGTH));
					infoList.add(IS_NULLABLE);
					infoList.add(COLUMN_KEY);
					infoList.add(COLUMN_DEFAULT);
//				System.out.println(infoList.toString());
					if (!valueInfoList.contains(infoList)) {
						valueInfoList.add(infoList);
					}

				}
			} else { //postsql일때
				while (rs.next()) {
					System.out.println("postsql 연결함");
					ArrayList<String> infoList = new ArrayList<String>();
					int index = 1;
					String TABLE_NAME = rs.getString(index++);
					String TABLE_COMMENT = rs.getString(index++);
					String ORDINAL_POSITION = rs.getString(index++);
					String COLUMN_NAME = rs.getString(index++);
					String COLUMN_COMMENT = rs.getString(index++);
					String COLUMN_TYPE = rs.getString(index++);
					int CHARACTER_MAXIMUM_LENGTH = rs.getInt(index++); //데이터 타입에 따른 크기 계산
					if (COLUMN_TYPE.equals("varchar")) {
						COLUMN_TYPE = COLUMN_TYPE + "(" + CHARACTER_MAXIMUM_LENGTH + ")";
					}
					if (COLUMN_TYPE.contains("time") || COLUMN_TYPE.contains("decimal") || COLUMN_TYPE.equals("bigint")) {
						CHARACTER_MAXIMUM_LENGTH = 8;
					} else if (COLUMN_TYPE.contains("int") || COLUMN_TYPE.equals("date")) {
						CHARACTER_MAXIMUM_LENGTH = 4;
					}

					String IS_NULLABLE = rs.getString(index++);
					if (IS_NULLABLE.equals("NO")) {
						IS_NULLABLE = "N";
					} else if (IS_NULLABLE.equals("YES")) {
						IS_NULLABLE = "Y";
					}
					String COLUMN_KEY = rs.getString(index++);
					if (COLUMN_KEY == null || COLUMN_KEY == "NULL") {
						COLUMN_KEY = " ";
					}
					String COLUMN_DEFAULT = rs.getString(index++);
					String defCol;
					if (COLUMN_DEFAULT == null) {
						defCol = " ";
					} else {
						String[] array = COLUMN_DEFAULT.split(":");
						defCol = array[0];
						defCol = defCol.replace("'", "");
					}
					infoList.add(TABLE_NAME.toUpperCase());
					infoList.add(TABLE_COMMENT);
					infoList.add(ORDINAL_POSITION);
					infoList.add(COLUMN_NAME.toUpperCase());
					infoList.add(COLUMN_COMMENT);
					infoList.add(COLUMN_TYPE);
					infoList.add(Integer.toString(CHARACTER_MAXIMUM_LENGTH));
					infoList.add(IS_NULLABLE);
					infoList.add(COLUMN_KEY);
					infoList.add(defCol);
//					System.out.println(infoList.toString());
					if (!valueInfoList.contains(infoList)) {
						valueInfoList.add(infoList);
					}

				}
			}
			System.out.println(valueInfoList.toString());

			for (int i = 0; i < valueInfoList.size(); i++) {
				String key = (valueInfoList.get(i)).remove(0);
//				System.out.println(key);
//				System.out.println(valueInfoList.get(i));
				ArrayList<ArrayList<String>> listInMap = new ArrayList<ArrayList<String>>();
				if (infoMap.containsKey(key)) {
					listInMap = infoMap.get(key);
					listInMap.add(valueInfoList.get(i));
				} else {
					listInMap.add(valueInfoList.get(i));
				}
				infoMap.put(key, listInMap);
			}

			System.out.println(infoMap.toString());

		} catch (ClassNotFoundException e) {
			System.out.println("드라이브 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return infoMap;
	}

}
