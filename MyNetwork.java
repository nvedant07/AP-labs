package Group_labs;

import java.util.*;
import java.io.*;

public class MyNetwork {
	//arpan code
	static ArrayList persons=new ArrayList();
	static int size=0;
	static boolean logged_in=false;
	public void reader(String file){
		File database = new File(file);
		String thisLine = null;
		try {			
			BufferedReader data = new BufferedReader(new FileReader(database));
			while ((thisLine = data.readLine()) != null){
				String[] array = thisLine.split(",");
//				System.out.println(array[0]+" "+array[1]+" "+array[2]+" "+array[3]);
//				System.out.println(array[3]);				
				int friends = Integer.parseInt(array[3]);
				int requests = Integer.parseInt(array[3+friends+1]);
				Person p = new Person(friends, requests,array[0],array[1],array[2]);
//				p.username=array[0];
//				p.password=array[1];
//				p.display_name=array[2];
				for(int i=4; i<(4+friends); i++){
					p.friends.add(array[i]);
				}
				for(int i=4+friends; i<(5+friends+requests); i++){
					p.pending.add(array[i]);
				}
				persons.add(p);
			}
			
		}
		catch (FileNotFoundException e) {	
			System.out.println("Couldn't Find the File");
			System.exit(0);
		}
		catch(IOException e){	
			System.out.println("An I/O Error Occurred");
			System.exit(0);
		}
	}

	public void login(){
		Scanner in=new Scanner(System.in);
		System.out.println("Please enter your username: ");
//		String garbage=in.nextLine();
		String name=in.nextLine();
		System.out.println("Please enter your password: ");
		String pass=in.nextLine();
//		System.out.println(name+" "+pass);
		for(int i=0;i<persons.size();i++){
			Person current = (Person)persons.get(i);
//			System.out.println(current.username+" "+name);
			if (current.get_username().equals(name) && current.get_password().equals(pass)){
				System.out.println("You have succesfully logged in " + current.get_display_name());
				logged_in=true;
			}
		}
		if(!logged_in){
			System.out.println("Username and password do not match");
		}
	}
	//end arpan code
	public void add_user(Person new_user) throws IOException{
		String user=new_user.get_username()+","+new_user.get_password()+","+new_user.get_display_name()+","+new_user.get_number_of_friends()+","+new_user.get_number_pending_requests()+","+new_user.get_status();
		int flag=0;
		for(int i=0;i<persons.size();i++){
			Person iter=(Person)persons.get(i);
			if(iter.get_username().equals(new_user.get_username())){
				flag=1;
			}
		}
		if(flag==0){
			String filename= "input.txt";
			FileWriter fw=new FileWriter(filename,true);
			try
			{
			    fw.write(user+"\n");
			}
			catch(IOException ioe)
			{
			    System.err.println("IOException: " + ioe.getMessage());
			}
			finally{
				fw.close();
			}

		}
		else{
			System.out.println("Username already exists!");
		}
	}
	public static void main(String[] args) {
		System.out.println("Reading database file...");
		System.out.println("Network is ready.");
		System.out.println();
		System.out.println("    1.Sign Up");
		System.out.println("    2.Login");
		Scanner in=new Scanner(System.in);
		while(true){
			int a=in.nextInt();
			while(a==1){
				MyNetwork n=new MyNetwork();
				n.reader("input.txt");
				System.out.println("Enter Username: ");
				String garbage=in.nextLine();
				String username=in.nextLine();
				System.out.println("Enter Display Name ");
				String display_name=in.nextLine();
				System.out.println(display_name);
				System.out.println("Enter Password ");
				String password=in.nextLine();
				Person anon=new Person(0,0,username,password,display_name);
//				anon.username=username;
//				anon.display_name=display_name;
//				anon.password=password;
				anon.set_status("<add a status>");
				try{
					n.add_user(anon);
				}
				catch(IOException ioe)
				{
				    System.err.println("IOException: " + ioe.getMessage());
				}
				System.out.println("    1.Sign Up");
				System.out.println("    2.Login");
				a=in.nextInt();
			}
			if (a==2){
				MyNetwork n=new MyNetwork();
				n.reader("input.txt");
				n.login();
			}
		}
	}
	
}
class Person{
	private String username;
	private String password;
	private String display_name;
	private int number_of_friends;//setter needed
	ArrayList friends=new ArrayList();//setter needed
	private int number_pending_requests;//setter needed
	ArrayList pending=new ArrayList();//setter needed
	private String status;//setter needed
	
	public Person(int a,int b,String username,String password,String display_name){
		this.number_of_friends=a;
		this.number_pending_requests=a;
		this.username=username;
		this.password=password;
		this.display_name=display_name;
	}
	public String get_username(){
		return this.username;
	}
	public String get_password(){
		return this.password;
	}
	public String get_display_name(){
		return this.display_name;
	}
	public int get_number_of_friends(){
		return this.number_of_friends;
	}
	public void set_number_of_friends(int number_of_friends){
		this.number_of_friends=number_of_friends;
	}
	public int get_number_pending_requests(){
		return this.number_pending_requests;
	}
	public void set_number_pending_requests(int number_pending_requests){
		this.number_pending_requests=number_pending_requests;
	}
	public String get_status(){
		return this.status;
	}
	public void set_status(String status){
		this.status=status;
	}
}