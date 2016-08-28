package Group_labs;

import java.util.*;
import java.text.*;

public class Lab_insurance {
	public static void main(String[]args){
		polo car1=new polo("Trendline","Vedant Nanda",2017,"Package Policy");
		terrano car2=new terrano("Automatic","Regina Phelange",2010,"Package Policy");
		ktm motorbike1=new ktm("Superduke","Arpan Mondal",2020,"Third Party Policy");
		hero motorbike2=new hero("Ignitor","Juliana Moore",2015,"Third Party Policy");
		bwin bike1=new bwin("Clive Bixby");
		firefox bike2=new firefox("Phil Dunphy");
	}
}

class policy{
	int expiry_year;
	String policy_name;
}
//Parent class vehicle
class vehicle{
	String owner_name;
	int number_of_wheels;
}
//types of vehicles
class engine_powered_vehicle extends vehicle{
	policy p=new policy();
}

class manual extends vehicle{
	String brand;
	String model;
}
//engine powered categories
class engine_powered_two_wheelers extends engine_powered_vehicle{
	String brand;
	String model;
}

class engine_powered_four_wheelers extends engine_powered_vehicle{
	String brand;
	String model;
}
//specific models
class polo extends engine_powered_four_wheelers{
	public polo(String model,String owner_name,int expiry_year,String policy_name){
		this.brand="Volkswagen";
		this.number_of_wheels=4;
		this.model=model;
		this.owner_name=owner_name;
		this.p.expiry_year=expiry_year;
		this.p.policy_name=policy_name;
	}
}
class terrano extends engine_powered_four_wheelers{
	public terrano(String model,String owner_name,int expiry_year,String policy_name){
		this.brand="Nissan";
		this.number_of_wheels=4;
		this.model=model;
		this.owner_name=owner_name;
		this.p.expiry_year=expiry_year;
		this.p.policy_name=policy_name;
	}
}
class ktm extends engine_powered_two_wheelers{
	public ktm(String model,String owner_name,int expiry_year,String policy_name){
		this.brand="KTM";
		this.number_of_wheels=2;
		this.model=model;
		this.owner_name=owner_name;
		this.p.expiry_year=expiry_year;
		this.p.policy_name=policy_name;
	}
}
class hero extends engine_powered_two_wheelers{
	public hero(String model,String owner_name,int expiry_year,String policy_name){
		this.brand="Hero";
		this.number_of_wheels=2;
		this.model=model;
		this.owner_name=owner_name;
		this.p.expiry_year=expiry_year;
		this.p.policy_name=policy_name;
	}
}
class firefox extends manual{
	public firefox(String owner_name){
		this.brand="Firfox";
		this.number_of_wheels=2;
		this.owner_name=owner_name;
	}
}
class bwin extends manual{
	public bwin(String owner_name){
		this.brand="Bwin";
		this.number_of_wheels=2;
		this.owner_name=owner_name;
	}
}

