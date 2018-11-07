package io.github.jacktown11.account.controller;
/*
 * controller layer
 * receive data from view and pass it to service
 */

import java.util.List;

import io.github.jacktown11.account.domain.AccountItem;
import io.github.jacktown11.account.service.AccountService;

public class AccountController {
	private AccountService service = new AccountService();
	
	public List<AccountItem> selectAll(){
		return service.selectAll();
	}

	public List<AccountItem> selectByDate(String startDate, String endDate) {
		return service.selectByDate(startDate, endDate);
	}

	public int deleteAccount(int aid) {
		return service.deleteAccount(aid);
	}

	public int insertAccount(AccountItem account) {
		return service.insertAccount(account);
	}

	public int updateAccount(AccountItem account) {
		return service.updateAccount(account);
	}

	public AccountItem selectByAid(int aid) {
		return service.selectByAid(aid);
	}

}
