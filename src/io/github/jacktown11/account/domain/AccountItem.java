package io.github.jacktown11.account.domain;

public class AccountItem {
	private int aid;
	private String category;
	private double money;
	private String payment_method;
	private String createtime;
	private String description;
	
	public AccountItem() {}

	public AccountItem(int aid, String category, double money, String payment_method, String createtime,
			String description) {
		super();
		this.aid = aid;
		this.category = category;
		this.money = money;
		this.payment_method = payment_method;
		this.createtime = createtime;
		this.description = description;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "AccountItem [aid=" + aid + ", category=" + category + ", money=" + money + ", payment_method="
				+ payment_method + ", createtime=" + createtime + ", description=" + description + "]";
	}
	
}
