package io.github.jacktown11.account.service;
/*
 * service class,
 * receive from controller and invoke class in dao
 */

import java.util.List;

import io.github.jacktown11.account.dao.AccountDao;
import io.github.jacktown11.account.domain.AccountItem;

public class AccountService {
	private AccountDao dao = new AccountDao();

	public List<AccountItem> selectAll() {
		return dao.selectAll();
	}

	public List<AccountItem> selectByDate(String startDate, String endDate) {
		return dao.selectByDate(startDate, endDate);
	}

	public int deleteAccount(int aid) {
		return dao.deleteAccount(aid);
	}

	public int insertAccount(AccountItem account) {
		return dao.insertAccount(account);
	}

	public int updateAccount(AccountItem account) {
		return dao.updateAccount(account);
	}

	public AccountItem selectByAid(int aid) {
		return dao.selectByAid(aid);
	}
}
