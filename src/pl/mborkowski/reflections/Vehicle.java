package pl.mborkowski.reflections;

public class Vehicle {
	public String make;
	public String model;
	public int productionYear;
	public int mileage;
	
	public Vehicle(String make, String model, int productionYear) {
		super();
		this.make = make;
		this.model = model;
		this.productionYear = productionYear;
	}
	
	public void backMileage(int mileage) {
		this.mileage -= mileage; 
	}
}
