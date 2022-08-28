package vo;

public class OrderTableVO {

	int number, count, total;
	String name, orderDate, orderDate2;

	public OrderTableVO() {
	}

	public OrderTableVO(int number, int count, int total, String name, String orderDate) {
		this.number = number;
		this.count = count;
		this.total = total;
		this.name = name;
		this.orderDate = orderDate;
	}

	public OrderTableVO(int number, int count, int total, String name, String orderDate, String orderDate2) {
		this.number = number;
		this.count = count;
		this.total = total;
		this.name = name;
		this.orderDate = orderDate;
		this.orderDate2 = orderDate2;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderDate2() {
		return orderDate2;
	}
	
	public void setOrderDate2(String orderDate2) {
		this.orderDate2 = orderDate2;
	}

}
