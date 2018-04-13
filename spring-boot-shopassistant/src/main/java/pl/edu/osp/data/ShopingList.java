package pl.edu.osp.data;


public class ShopingList {
	private long timestamp;
	private String mail;
	private String[] shopingList;
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String[] getShopingList() {
		return shopingList;
	}
	public void setShopingList(String[] shopingList) {
		this.shopingList = shopingList;
	}
	

}
