import java.util.*;
import java.text.*;

public class Lab_insurance {
	void print(vehicle[] arr){
		for (int i=0;i<6;i++){
			System.out.print(arr[i].getBrand()+" "+arr[i].getModel()+"\t"+arr[i].owner_name+"\t"+arr[i].number_of_wheels+"\t");
			System.out.print(arr[i].getPolicyName()+"\t"+arr[i].getExpiryDate());
			System.out.println();
		}
	}
	
	void makeCollisions(vehicle[] arr){
		Date dNow = new Date( );
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				if(j==i)continue;
				int num=0;
				System.out.println("I am "+arr[i].owner_name+" and have a "+arr[i].getBrand()+" "+arr[i].getModel()+", collided with "+arr[j].owner_name+" driving a "+arr[j].getBrand()+" "+arr[j].getModel());
				System.out.println("Damages Self: "+arr[i].CollisionCost());
				System.out.println("Damages Oncoming: "+arr[j].CollisionCost());
				if(arr[i].getPolicyName()!=null){
					if(arr[i].getExpiryDate().compareTo(ft.format(dNow).toString())==1){
						num++;
						System.out.println("Settlement Details.");
						if(arr[i].getPolicyName().equals("Package Policy")){
							System.out.println("\tPayable oncoming vehicle damages: "+arr[i].CollisionCost()*.2);
							System.out.println("\tPayable self vehicle damages: "+arr[i].CollisionCost()*.5);
						}
						else if(arr[i].getPolicyName().equals("Third Party Policy")){
							System.out.println("\tPayable oncoming vehicle damages: "+arr[i].CollisionCost()*.2);
							System.out.println("\tPayable self vehicle damages: "+arr[i].CollisionCost()*1.0);
						}
					}
					try{
						int num2=1024/num;
					}catch (ArithmeticException e){
						System.out.println("Exception is caught: Self policy expired!");
					}
				}
				try{
					arr[i].getPolicyName().toString();
				}catch(NullPointerException e){
					System.out.println("Exception is caught: Self does not have a policy!");
				}
			}
		}
	}
	public static void main(String[]args){
		polo car1=new polo("Polo","Vedant Nanda","2017-01-01","Package Policy");
		terrano car2=new terrano("Terrano","Regina Phelange","2015-02-27","Package Policy");
		ktm motorbike1=new ktm("Superduke","Arpan Mondal","2020-07-15","Third Party Policy");
		hero motorbike2=new hero("Ignitor","Juliana Moore","2014-12-26","Third Party Policy");
		bwin bike1=new bwin("City","Clive Bixby",null,null);
		firefox bike2=new firefox("Domane","Phil Dunphy",null,null);
		vehicle[] arr=new vehicle[6];
		arr[0]=car1;
		arr[1]=car2;
		arr[2]=motorbike1;
		arr[3]=motorbike2;
		arr[4]=bike1;
		arr[5]=bike2;
		System.out.println("Details of vehicles in the system:\n");
		Lab_insurance l=new Lab_insurance();
		l.print(arr);
		System.out.println();
		System.out.println("Collision Loop:\n");
		l.makeCollisions(arr);
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
	public abstract int CollisionCost();
	//	public abstract void makeclass(String model,String owner_name,String expiry_date,String policy_name);
}
//types of vehicles
abstract class engine_powered_vehicle extends vehicle{
	policy p=new policy();
}
//manual category
abstract class manual extends vehicle{
	policy p=new policy();
	Random rand=new Random();
	int rn=rand.nextInt(450 + 1) + 50;
	private int damage_cost=rn-(rn%10);
	public int CollisionCost(){
		return this.damage_cost;
	}
}
//engine powered categories
abstract class engine_powered_two_wheelers extends engine_powered_vehicle{
	Random rand=new Random();
	int rn=rand.nextInt(2500 + 1) + 500;
	private int damage_cost=rn-(rn%100);
	public int CollisionCost(){
		return this.damage_cost;
	}
}

abstract class engine_powered_four_wheelers extends engine_powered_vehicle{
	Random rand=new Random();
	int rn=rand.nextInt(8000 + 1) + 2000;
	private int damage_cost=rn-(rn%500);
	public int CollisionCost(){
		return this.damage_cost;
	}
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

