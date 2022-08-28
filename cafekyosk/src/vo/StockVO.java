package vo;

public class StockVO {
	
	int number, price, count;
	String name, category;
	
	public StockVO(){
	}
	
	
	
	public StockVO(int number, String name, String category, int price, int count) {
		this.number = number;
		this.price = price;
		this.count = count;
		this.name = name;
		this.category = category;
	}
	
	public StockVO(String name, String category, int price, int count) {
		this.price = price;
		this.name = name;
		this.category = category;
		this.count = count;
	}



	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	

}
