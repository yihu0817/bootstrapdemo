package xeonmic.vo;

public class demo {
	private int id;
	private String name;
	private int type;
	private double pay;
	private String text;
	
	public demo() {
		// TODO Auto-generated constructor stub
	}
	
	public demo(int id, int type, Double pay, String name, String text) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.pay = pay;
		this.text = text;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getPay() {
		return pay;
	}
	public void setPay(Double pay) {
		this.pay = pay;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "demo [id=" + id + ", name=" + name + ", type=" + type
				+ ", pay=" + pay + ", text=" + text + "]";
	}
	
}
