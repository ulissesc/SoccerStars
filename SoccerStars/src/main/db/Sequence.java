package main.db;


public class Sequence {
	
	private Long value;
	private String clazz; 

	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public Long getNextValue(){
		return value++;
	}
	
}
