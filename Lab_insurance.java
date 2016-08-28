package Group_labs;

import java.util.*;
import java.text.*;

public class Lab_insurance {
	void print(vehicle[] arr){
		for (int i=0;i<6;i++){
			System.out.println(arr[i].getBrand()+" "+arr[i].getModel()+","+arr[i].owner_name+","+arr[i].number_of_wheels);
		}
	}
	public static void main(String[]args){
		polo car1=new polo("Trendline","Vedant Nanda","01-01-2017","Package Policy");
		terrano car2=new terrano("Automatic","Regina Phelange","01-01-2015","Package Policy");
		ktm motorbike1=new ktm("Superduke","Arpan Mondal","07-07-2020","Third Party Policy");
		hero motorbike2=new hero("Ignitor","Juliana Moore","08-08-2014","Third Party Policy");
		bwin bike1=new bwin("City","Clive Bixby",null,null);
		firefox bike2=new firefox("Mountain","Phil Dunphy",null,null);
		vehicle[] arr=new vehicle[6];
		arr[0]=car1;
		arr[1]=car2;
		arr[2]=motorbike1;
		arr[3]=motorbike2;
		arr[4]=bike1;
		arr[5]=bike2;
		System.out.println("Details of vehicles in the system:");
		Lab_insurance l=new Lab_insurance();
		l.print(arr);
	}
}

class policy{
	String expiry_date;
	String policy_name;
}
//Parent class vehicle
abstract class vehicle{
	String owner_name;
	int number_of_wheels;
	public abstract String getBrand();
	public abstract String getModel();
	public abstract String getExpiryDate();
	public abstract String getPolicyName();
//	public abstract void makeclass(String model,String owner_name,String expiry_date,String policy_name);
}
//types of vehicles
abstract class engine_powered_vehicle extends vehicle{
	policy p=new policy();
}
//manual category
abstract class manual extends vehicle{
	policy p=new policy();
}
//engine powered categories
abstract class engine_powered_two_wheelers extends engine_powered_vehicle{
}

abstract class engine_powered_four_wheelers extends engine_powered_vehicle{
}
//specific models
class polo extends engine_powered_four_wheelers{
	private String brand;
	private String model;
	public polo(String model,String owner_name,String expiry_date,String policy_name){
		this.brand="Volkswagen";
		this.number_of_wheels=4;
		this.model=model;
		this.owner_name=owner_name;
		this.p.expiry_date=expiry_date;
		this.p.policy_name=policy_name;
	}
	public String getBrand(){
		return this.brand;
	}
	public String getModel(){
		return this.model;
	}
	public String getExpiryDate(){
		return this.p.expiry_date;
	}
	public String getPolicyName(){
		return this.p.policy_name;
	}
}
class terrano extends engine_powered_four_wheelers{
	private String brand;
	private String model;
	public terrano(String model,String owner_name,String expiry_date,String policy_name){
		this.brand="Nissan";
		this.number_of_wheels=4;
		this.model=model;
		this.owner_name=owner_name;
		this.p.expiry_date=expiry_date;
		this.p.policy_name=policy_name;
	}
	public String getBrand(){
		return this.brand;
	}
	public String getModel(){
		return this.model;
	}
	public String getExpiryDate(){
		return this.p.expiry_date;
	}
	public String getPolicyName(){
		return this.p.policy_name;
	}
}
class ktm extends engine_powered_two_wheelers{
	private String brand;
	private String model;
	public ktm(String model,String owner_name,String expiry_date,String policy_name){
		this.brand="KTM";
		this.number_of_wheels=2;
		this.model=model;
		this.owner_name=owner_name;
		this.p.expiry_date=expiry_date;
		this.p.policy_name=policy_name;
	}
	public String getBrand(){
		return this.brand;
	}
	public String getModel(){
		return this.model;
	}
	public String getExpiryDate(){
		return this.p.expiry_date;
	}
	public String getPolicyName(){
		return this.p.policy_name;
	}
}
class hero extends engine_powered_two_wheelers{
	private String brand;
	private String model;
	public hero(String model,String owner_name,String expiry_date,String policy_name){
		this.brand="Hero";
		this.number_of_wheels=2;
		this.model=model;
		this.owner_name=owner_name;
		this.p.expiry_date=expiry_date;
		this.p.policy_name=policy_name;
	}
	public String getBrand(){
		return this.brand;
	}
	public String getModel(){
		return this.model;
	}
	public String getExpiryDate(){
		return this.p.expiry_date;
	}
	public String getPolicyName(){
		return this.p.policy_name;
	}
}
class firefox extends manual{
	private String brand;
	private String model;
	public firefox(String model,String owner_name,String expiry_date,String policy_name){
		this.brand="Firefox";
		this.number_of_wheels=2;
		this.model=model;
		this.owner_name=owner_name;
		this.p.expiry_date=expiry_date;
		this.p.policy_name=policy_name;
	}
	public String getBrand(){
		return this.brand;
	}
	public String getModel(){
		return this.model;
	}
	public String getExpiryDate(){
		return this.p.expiry_date;
	}
	public String getPolicyName(){
		return this.p.policy_name;
	}
}
class bwin extends manual{
	private String brand;
	private String model;
	public bwin(String model,String owner_name,String expiry_date,String policy_name){
		this.brand="Bwin";
		this.number_of_wheels=2;
		this.model=model;
		this.owner_name=owner_name;
		this.p.expiry_date=expiry_date;
		this.p.policy_name=policy_name;
	}
	public String getBrand(){
		return this.brand;
	}
	public String getModel(){
		return this.model;
	}
	public String getExpiryDate(){
		return this.p.expiry_date;
	}
	public String getPolicyName(){
		return this.p.policy_name;
	}
}

