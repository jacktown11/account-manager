package io.github.jacktown11.account.dao;
/*
 * 对账务表的增删改查操作
 */

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import io.github.jacktown11.account.domain.AccountItem;
import io.github.jacktown11.account.tools.JDBCUtils;

public class AccountDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	/*
	 * select all rows from table account
	 */
	public List<AccountItem> selectAll() {
		String sql = "SELECT * FROM account";
		List<AccountItem> res = null;
		try {
			res = qr.query(sql, new BeanListHandler<AccountItem>(AccountItem.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("全查询失败");
		}
		return res;
	}

	public List<AccountItem> selectByDate(String startDate, String endDate) {
		String sql = "SELECT * FROM account WHERE createtime BETWEEN ? AND ?";
		Object[] params = {startDate, endDate};
		List<AccountItem> res = null;
		try {
			res = qr.query(sql, new BeanListHandler<AccountItem>(AccountItem.class), params);
		}catch(Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("日期段条件查询失败");
		}
		return res;
	}

	public int deleteAccount(int aid) {
		String sql = "DELETE FROM account WHERE aid=?";
		Object[] params = {aid};
		int affectedRows = 0;
		try {
			affectedRows = qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("删除账务失败。");
		}
		return affectedRows;
	}

	public int insertAccount(AccountItem account) {
		String sql = "INSERT INTO account (category, money, payment_method, createtime, description) VALUES (?,?,?,?,?)";
		Object[] params = {account.getCategory(), account.getMoney(), account.getPayment_method(), account.getCreatetime(), account.getDescription()};
		int affectedRows = 0;
		try {
			affectedRows = qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("添加账务失败");
		}
		return affectedRows;
	}

	public int updateAccount(AccountItem account) {
		String sql = "UPDATE account SET category=?, money=?, payment_method=?, createtime=?, description=? WHERE aid=?";
		Object[] params = {account.getCategory(), account.getMoney(), account.getPayment_method(), account.getCreatetime(), account.getDescription(), account.getAid()};
		int affectedRows;
		try {
			affectedRows = qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("修改账务失败");
		}
		return affectedRows;
	}

	public AccountItem selectByAid(int aid) {
		String sql = "SELECT * FROM account WHERE aid=?";
		AccountItem account;
		try {
			account = qr.query(sql, new BeanHandler<AccountItem>(AccountItem.class), aid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("修改账务失败");
		}
		return account;
	}
}
