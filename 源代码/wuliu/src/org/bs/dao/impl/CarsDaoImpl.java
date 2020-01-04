package org.bs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.bs.dao.CarsDao;
import org.bs.model.Cars;
import org.bs.utils.ConnContext;
import org.bs.utils.DB;
import org.bs.utils.PageContext;

public class CarsDaoImpl extends BaseDao implements CarsDao {
	public void save(Cars cars) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "insert into t_cars (name,leixing,zaizhong,xiulicontent,jiayou,beizhucontent,jiashiyuancontent,carno,luxiancontent,youfei) values(?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, cars.getName());
			pstmt.setString(2, cars.getLeixing());
			pstmt.setString(3, cars.getZaizhong());
			pstmt.setString(4, cars.getXiulicontent());
			pstmt.setString(5, cars.getJiayou());
			pstmt.setString(6, cars.getBeizhucontent());
			pstmt.setString(7, cars.getJiashiyuancontent());
			pstmt.setString(8, cars.getCarno());
			pstmt.setString(9, cars.getLuxiancontent());
			pstmt.setString(10, cars.getYoufei());
			pstmt.executeUpdate();
			ResultSet results = pstmt.getGeneratedKeys();
			int num = -1;
			if (results.next()) {
				num = results.getInt(1);
			}
			cars.setId(num);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public void delete(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "delete from t_cars where id =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public void update(Cars cars) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "update t_cars set name=?,leixing=?,zaizhong=?,xiulicontent=?,jiayou=?,beizhucontent=?,jiashiyuancontent=?,carno=?,luxiancontent=?,youfei=?"
				+ " where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cars.getName());
			pstmt.setString(2, cars.getLeixing());
			pstmt.setString(3, cars.getZaizhong());
			pstmt.setString(4, cars.getXiulicontent());
			pstmt.setString(5, cars.getJiayou());
			pstmt.setString(6, cars.getBeizhucontent());
			pstmt.setString(7, cars.getJiashiyuancontent());
			pstmt.setString(8, cars.getCarno());
			pstmt.setString(9, cars.getLuxiancontent());
			pstmt.setString(10, cars.getYoufei());
			pstmt.setInt(11, cars.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public Cars getById(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Cars cars = new Cars();
		String sql = "select * from t_cars where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cars.setId(rs.getInt("id"));
				cars.setName(rs.getString("name"));
				cars.setLeixing(rs.getString("leixing"));
				cars.setZaizhong(rs.getString("zaizhong"));
				cars.setXiulicontent(rs.getString("xiulicontent"));
				cars.setJiayou(rs.getString("jiayou"));
				cars.setBeizhucontent(rs.getString("beizhucontent"));
				cars.setJiashiyuancontent(rs.getString("jiashiyuancontent"));
				cars.setCarno(rs.getString("carno"));
				cars.setLuxiancontent(rs.getString("luxiancontent"));
				cars.setYoufei(rs.getString("youfei"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return cars;
	}

	public Cars getByUserId(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Cars cars = new Cars();
		String sql = "select * from t_cars where users = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cars.setId(rs.getInt("id"));
				cars.setName(rs.getString("name"));
				cars.setLeixing(rs.getString("leixing"));
				cars.setZaizhong(rs.getString("zaizhong"));
				cars.setXiulicontent(rs.getString("xiulicontent"));
				cars.setJiayou(rs.getString("jiayou"));
				cars.setBeizhucontent(rs.getString("beizhucontent"));
				cars.setJiashiyuancontent(rs.getString("jiashiyuancontent"));
				cars.setCarno(rs.getString("carno"));
				cars.setLuxiancontent(rs.getString("luxiancontent"));
				cars.setYoufei(rs.getString("youfei"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return cars;
	}

	public List<Cars> query(String str) {
		List<Cars> list = new ArrayList<Cars>();
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from t_cars where 1=1 ";
		if (!"".equals(str) && str != null) {
			sql += " and name like '%" + str + "%' ";
		}
		String sqlRecordsCount = "select count(*)"
				+ sql.substring(sql.indexOf("from"));
		sql += " limit ?,?";
		PageContext.getPage().setRecordsCount(
				getRecordsCount(sqlRecordsCount, new Class[] {},
						new Object[] {}));
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, PageContext.getPage().getPageIndex());
			pstmt.setInt(2, PageContext.getPage().getPageSize());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Cars cars = new Cars();
				cars.setId(rs.getInt("id"));
				cars.setName(rs.getString("name"));
				cars.setLeixing(rs.getString("leixing"));
				cars.setZaizhong(rs.getString("zaizhong"));
				cars.setXiulicontent(rs.getString("xiulicontent"));
				cars.setJiayou(rs.getString("jiayou"));
				cars.setBeizhucontent(rs.getString("beizhucontent"));
				cars.setJiashiyuancontent(rs.getString("jiashiyuancontent"));
				cars.setCarno(rs.getString("carno"));
				cars.setLuxiancontent(rs.getString("luxiancontent"));
				cars.setYoufei(rs.getString("youfei"));
				list.add(cars);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return list;
	}
}