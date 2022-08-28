package vo;

public class BasketVO {

	String name, option, shot, id;
	int price, count,stamp, coupon;

	public BasketVO() {
	}

	public BasketVO(String name, String id, String option, String shot, int price, int count) {
		this.id = id;
		this.name = name;
		this.option = option;
		this.shot = shot;
		this.price = price;
		this.count = count;
	}
	public BasketVO(String name, String id, String option, String shot, int price, int count, int coupon) {
		this.id = id;
		this.name = name;
		this.option = option;
		this.shot = shot;
		this.price = price;
		this.count = count;
		this.coupon = coupon;
	}

	public BasketVO(String name, String option, String shot, int price, int count) {
		this.name = name;
		this.option = option;
		this.shot = shot;
		this.price = price;
		this.count = count;
	}
//	public BasketVO(int stamp, int coupon) {
//		this.stamp = stamp;
//		this.coupon = coupon;
//	}


	public int getCoupon() {
		return coupon;
	}

	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}
	public int getStamp() {
		return stamp;
	}

	public void setStamp(int stamp) {
		this.stamp = stamp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getShot() {
		return shot;
	}

	public void setShot(String shot) {
		this.shot = shot;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
