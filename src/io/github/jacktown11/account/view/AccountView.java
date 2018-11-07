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
					System.out.println("�����ڴ˲�����");
					break;
			}
		}
	}
	
	
	public void printMenu() {
		System.out.println("==== Account Manager ====");
		System.out.println("1.������� 2.�༭���� 3.ɾ������ 4.��ѯ���� 5.�˳�ϵͳ");
		System.out.println("����������Ҫ�Ĳ�����Ӧ��ţ�");
	}

	private void insertAccount() {
		System.out.println("===�������===");
		AccountItem account = new AccountItem();
		System.out.println("���������");
		account.setCategory(sc.next());
		System.out.println("�������");
		account.setMoney(sc.nextDouble());
		System.out.println("������֧����ʽ��");
		account.setPayment_method(sc.next());
		System.out.println("�����봴��ʱ��(yyyy-MM-dd)��");
		account.setCreatetime(sc.next());
		System.out.println("������������");
		account.setDescription(sc.next());
		
		int affectedRows = controller.insertAccount(account);
		if(affectedRows > 0) {
			System.out.println("�������ɹ���");
		}else {
			System.out.println("�������ʧ�ܡ�");
		}
	}
	
	private void updateAccount() {
		System.out.println("===�޸�����===");
		// show all accounts already exist
		selectAll();
		
		// get user input
		System.out.println("��������Ҫ�޸ĵ���Ŀ�ı�ţ�");
		int aid = sc.nextInt();

		// updated account
		AccountItem account = new AccountItem();
		account.setAid(aid);
		
		// show old account
		AccountItem oldAccount = selectByAid(aid);
		System.out.println("����Ҫ�޸ĵ�������Ŀ������ʾ��");
		printAccountItem(oldAccount);
		
		System.out.println("�޸������yes/no����");
		if("yes".equals(sc.next())) {
			System.out.println("�������µ����");
			account.setCategory(sc.next());			
		}else {
			account.setCategory(oldAccount.getCategory());
		}
		
		System.out.println("�޸Ľ����yes/no����");
		if("yes".equals(sc.next())) {
			System.out.println("�������µĽ�");
			account.setMoney(sc.nextDouble());			
		}else {
			account.setMoney(oldAccount.getMoney());
		}
		
		System.out.println("�޸�֧����ʽ��yes/no����");
		if("yes".equals(sc.next())) {
			System.out.println("�������µ�֧����ʽ��");
			account.setPayment_method(sc.next());			
		}else {
			account.setPayment_method(oldAccount.getPayment_method());
		}
		
		System.out.println("�޸Ĵ���ʱ����yes/no����");
		if("yes".equals(sc.next())) {
			System.out.println("�������µĴ���ʱ�䣨yyyy-MM-dd����");
			account.setCreatetime(sc.next());			
		}else {
			account.setCreatetime(oldAccount.getCreatetime());
		}
		
		System.out.println("�޸�������yes/no����");
		if("yes".equals(sc.next())) {
			System.out.println("�������µ�������");
			account.setDescription(sc.next());			
		}else {
			account.setDescription(oldAccount.getDescription());
		}
		
		// update account and show result
		int affectedRows = controller.updateAccount(account);
		if(affectedRows > 0) {
			System.out.println("�޸ı��Ϊ"+aid+"����Ŀ�ɹ���������£�");
			printAccountItem(account);
		}else {
			System.out.println("�޸ı��Ϊ"+aid+"����Ŀʧ��");
		}
	}
	
	private void deleteAccount() {
		System.out.println("===ɾ������===");
		selectAll();
		System.out.println("������Ҫɾ��������ı�ţ�����0�������˵�����");
		int aid = sc.nextInt();
		if(aid == 0) {
			// back to main menu
			return ;
		}else {
			int affectedRows = controller.deleteAccount(aid);
			if(affectedRows > 0) {
				System.out.println("����ɾ���ɹ�");
			}else {
				System.out.println("����ɾ��ʧ��");
			}
		}
	}
	
	/*
	 * select account according to user's choice
	 */
	private void selectAccount() {
		System.out.println("===��ѯ����===");
		System.out.println("1.��ѯ���� 2.������ѯ 0.�������˵�");
		System.out.println("�����������ţ�");
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
		System.out.println("��������ʼ����(yyyy-MM-dd)��");
		String startDate = sc.next();
		System.out.println("�������ֹ����(yyyy-MM-dd)��");
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
			System.out.println("û��������Ϣ");			
		}else {
			System.out.println("ID\t���\t֧����ʽ\t���\tʱ��\t\t˵��");
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
