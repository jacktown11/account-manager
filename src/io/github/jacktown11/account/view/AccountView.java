package io.github.jacktown11.account.view;
/*
 * view layer
 * send data to controller
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import io.github.jacktown11.account.controller.AccountController;
import io.github.jacktown11.account.domain.AccountItem;

public class AccountView {
	private AccountController controller = new AccountController();
	private Scanner sc = new Scanner(System.in);
	
	public void run() {
		while(true) {
			printMenu();
			int choiceNum = sc.nextInt();
			switch(choiceNum) {
				case 1:
					insertAccount();
					break;
				case 2:
					updateAccount();
					break;
				case 3:
					deleteAccount();
					break;
				case 4:
					selectAccount();
					break;
				case 5:
					exit();
					break;
				default:
					System.out.println("不存在此操作。");
					break;
			}
		}
	}
	
	
	public void printMenu() {
		System.out.println("==== Account Manager ====");
		System.out.println("1.添加账务 2.编辑账务 3.删除账务 4.查询账务 5.退出系统");
		System.out.println("请输入你需要的操作对应序号：");
	}

	private void insertAccount() {
		System.out.println("===添加账务===");
		AccountItem account = new AccountItem();
		System.out.println("请输入类别：");
		account.setCategory(sc.next());
		System.out.println("请输入金额：");
		account.setMoney(sc.nextDouble());
		System.out.println("请输入支付方式：");
		account.setPayment_method(sc.next());
		System.out.println("请输入创建时间(yyyy-MM-dd)：");
		account.setCreatetime(sc.next());
		System.out.println("请输入描述：");
		account.setDescription(sc.next());
		
		int affectedRows = controller.insertAccount(account);
		if(affectedRows > 0) {
			System.out.println("添加账务成功。");
		}else {
			System.out.println("添加账务失败。");
		}
	}
	
	private void updateAccount() {
		System.out.println("===修改账务===");
		// show all accounts already exist
		selectAll();
		
		// get user input
		System.out.println("请输入需要修改的项目的编号：");
		int aid = sc.nextInt();

		// updated account
		AccountItem account = new AccountItem();
		account.setAid(aid);
		
		// show old account
		AccountItem oldAccount = selectByAid(aid);
		System.out.println("您将要修改的账务条目如下所示：");
		printAccountItem(oldAccount);
		
		System.out.println("修改类别吗（yes/no）？");
		if("yes".equals(sc.next())) {
			System.out.println("请输入新的类别：");
			account.setCategory(sc.next());			
		}else {
			account.setCategory(oldAccount.getCategory());
		}
		
		System.out.println("修改金额吗（yes/no）？");
		if("yes".equals(sc.next())) {
			System.out.println("请输入新的金额：");
			account.setMoney(sc.nextDouble());			
		}else {
			account.setMoney(oldAccount.getMoney());
		}
		
		System.out.println("修改支付方式吗（yes/no）？");
		if("yes".equals(sc.next())) {
			System.out.println("请输入新的支付方式：");
			account.setPayment_method(sc.next());			
		}else {
			account.setPayment_method(oldAccount.getPayment_method());
		}
		
		System.out.println("修改创建时间吗（yes/no）？");
		if("yes".equals(sc.next())) {
			System.out.println("请输入新的创建时间（yyyy-MM-dd）：");
			account.setCreatetime(sc.next());			
		}else {
			account.setCreatetime(oldAccount.getCreatetime());
		}
		
		System.out.println("修改描述吗（yes/no）？");
		if("yes".equals(sc.next())) {
			System.out.println("请输入新的描述：");
			account.setDescription(sc.next());			
		}else {
			account.setDescription(oldAccount.getDescription());
		}
		
		// update account and show result
		int affectedRows = controller.updateAccount(account);
		if(affectedRows > 0) {
			System.out.println("修改编号为"+aid+"的条目成功，结果如下：");
			printAccountItem(account);
		}else {
			System.out.println("修改编号为"+aid+"的条目失败");
		}
	}
	
	private void deleteAccount() {
		System.out.println("===删除账务===");
		selectAll();
		System.out.println("请输入要删除的账务的编号（输入0返回主菜单）：");
		int aid = sc.nextInt();
		if(aid == 0) {
			// back to main menu
			return ;
		}else {
			int affectedRows = controller.deleteAccount(aid);
			if(affectedRows > 0) {
				System.out.println("账务删除成功");
			}else {
				System.out.println("账务删除失败");
			}
		}
	}
	
	/*
	 * select account according to user's choice
	 */
	private void selectAccount() {
		System.out.println("===查询账务===");
		System.out.println("1.查询所有 2.条件查询 0.返回主菜单");
		System.out.println("请输入操作序号：");
		int selectNum = sc.nextInt();
		switch(selectNum) {
			case 1:
				selectAll();
				break;
			case 2:
				selectByDate();
				break;
			default:
				// back to main menu
				return;
		}
	}
	
	/*
	 * select all account record
	 */
	private void selectAll() {
		printAccountItems(controller.selectAll());
	}
	
	/*
	 * select by start date and end date
	 */
	private void selectByDate() {
		System.out.println("请输入起始日期(yyyy-MM-dd)：");
		String startDate = sc.next();
		System.out.println("请输入截止日期(yyyy-MM-dd)：");
		String endDate = sc.next();
		printAccountItems(controller.selectByDate(startDate, endDate));
	}
	private AccountItem selectByAid(int aid) {
		return controller.selectByAid(aid);
	}
	
	/*
	 * print result in a table
	 */
	private void printAccountItems(List<AccountItem> accounts) {
		if(accounts == null || accounts.size() == 0) {
			// no effective result
			System.out.println("没有账务信息");			
		}else {
			System.out.println("ID\t类别\t支付方式\t金额\t时间\t\t说明");
			for(AccountItem a : accounts) {
				System.out.println(a.getAid()+"\t"+a.getCategory()+"\t"+a.getPayment_method()+"\t"+a.getMoney()+"\t"+a.getCreatetime()+"\t"+a.getDescription());
			}
		}
	}
	private void printAccountItem(AccountItem account) {
		List<AccountItem> list = new ArrayList();
		list.add(account);
		printAccountItems(list);
	}
	
	private void exit() {
		System.exit(0);
	}
}
