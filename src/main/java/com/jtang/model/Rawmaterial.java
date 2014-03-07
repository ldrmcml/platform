package com.jtang.model;

import java.util.Date;

public class Rawmaterial {
	private int Id;
	private String Name;
	private String Manufacturer;
	private String ProductionPlace;
	private Date ProductionDate;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getManufacturer() {
		return Manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		Manufacturer = manufacturer;
	}
	public String getProductionPlace() {
		return ProductionPlace;
	}
	public void setProductionPlace(String productionPlace) {
		ProductionPlace = productionPlace;
	}
	public Date getProductionDate() {
		return ProductionDate;
	}
	public void setProductionDate(Date productionDate) {
		ProductionDate = productionDate;
	}
	
}
