package org.bs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.bs.dao.ExpressDao;
import org.bs.model.Express;
import org.bs.utils.ConnContext;
import org.bs.utils.DB;
import org.bs.utils.PageContext;
import org.bs.dao.MemberDao;

public class ExpressDaoImpl extends BaseDao implements ExpressDao {
	private MemberDao memberDao = new MemberDaoImpl();

	public void save(Express express) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "insert into t_express (name,member,chufa,shoujianname,tel,place,leibie,settime,descp,state) values(?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, express.getName());
			pstmt.setInt(2, express.getMember().getId());
			pstmt.setString(3, express.getChufa());
			pstmt.setString(4, express.getShoujianname());
			pstmt.setString(5, express.getTel());
			pstmt.setString(6, express.getPlace());
			pstmt.setString(7, express.getLeibie());
			pstmt.setTimestamp(8, new java.sql.Timestamp(express.getSettime()
					.getTime()));
			pstmt.setString(9, express.getDescp());
			pstmt.setString(10, express.getState());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public void delete(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "delete from t_express where id =?";
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

	public void update(Express express) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "update t_express set name=?,member=?,chufa=?,shoujianname=?,tel=?,place=?,leibie=?,settime=?,descp=?,state=?"
				+ " where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, express.getName());
			pstmt.setInt(2, express.getMember().getId());
			pstmt.setString(3, express.getChufa());
			pstmt.setString(4, express.getShoujianname());
			pstmt.setString(5, express.getTel());
			pstmt.setString(6, express.getPlace());
			pstmt.setString(7, express.getLeibie());
			pstmt.setTimestamp(8, new java.sql.Timestamp(express.getSettime()
					.getTime()));
			pstmt.setString(9, express.getDescp());
			pstmt.setString(10, express.getState());
			pstmt.setInt(11, express.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public void updateState(Express express) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "update t_express set state=?  " + " where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, express.getState());
			pstmt.setInt(2, express.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public Express getById(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Express express = new Express();
		String sql = "select * from t_express where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				express.setId(rs.getInt("id"));
				express.setName(rs.getString("name"));
				express.setMember(memberDao.getById(rs.getInt("member")));
				express.setChufa(rs.getString("chufa"));
				express.setShoujianname(rs.getString("shoujianname"));
				express.setTel(rs.getString("tel"));
				express.setPlace(rs.getString("place"));
				express.setLeibie(rs.getString("leibie"));
				express.setSettime(rs.getTimestamp("settime"));
				express.setDescp(rs.getString("descp"));
				express.setState(rs.getString("state"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return express;
	}

	public Express getByUserId(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Express express = new Express();
		String sql = "select * from t_express where users = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				express.setId(rs.getInt("id"));
				express.setName(rs.getString("name"));
				express.setMember(memberDao.getById(rs.getInt("member")));
				express.setChufa(rs.getString("chufa"));
				express.setShoujianname(rs.getString("shoujianname"));
				express.setTel(rs.getString("tel"));
				express.setPlace(rs.getString("place"));
				express.setLeibie(rs.getString("leibie"));
				express.setSettime(rs.getTimestamp("settime"));
				express.setDescp(rs.getString("descp"));
				express.setState(rs.getString("state"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return express;
	}

	public List<Express> query(String str) {
		List<Express> list = new ArrayList<Express>();
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from t_express where 1=1 ";
		if (!"".equals(str) && str != null) {
			if (str.contains("ri")) {
				str = str.replace("ri", "");
				if (!"null".equals(str) && !"".equals(str) && str != null) {
					sql += " and settime like '%" + str.replace("ri", "")
							+ "%' ";
				}
			} else {
				if (str.contains("wancheng")) {
					sql += " and state = '已完成' ";
					str = str.replace("wancheng", "");
					if (!"null".equals(str) && !"".equals(str) && str != null) {
						sql += " and name like '%" + str + "%' ";
					}
				} else {
					sql += " and name like '%" + str + "%' ";
				}
			}
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
				Express express = new Express();
				express.setId(rs.getInt("id"));
				express.setName(rs.getString("name"));
				express.setMember(memberDao.getById(rs.getInt("member")));
				express.setChufa(rs.getString("chufa"));
				express.setShoujianname(rs.getString("shoujianname"));
				express.setTel(rs.getString("tel"));
				express.setPlace(rs.getString("place"));
				express.setLeibie(rs.getString("leibie"));
				express.setSettime(rs.getTimestamp("settime"));
				express.setDescp(rs.getString("descp"));
				express.setState(rs.getString("state"));
				list.add(express);
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