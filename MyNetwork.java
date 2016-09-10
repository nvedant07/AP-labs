package Group_labs;

import java.util.*;
import java.io.*;

public class MyNetwork {
	//arpan code
	static Person [] persons=new Person[1000];
	static int size=0;
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
				Person p = new Person(friends, requests);
				p.username=array[0];
				p.password=array[1];
				p.display_name=array[2];
				
				for(int i=4; i<(4+friends); i++){
					p.friends.add(array[i]);
				}
				for(int i=4+friends; i<(5+friends+requests); i++){
					p.pending.add(array[i]);
				}
				persons[size]=p;
				size++;
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
		for(int i=0;i<size;i++){
			Person current = persons[i];
//			System.out.println(current.username+" "+name);
			if (current.username.equals(name) && current.password.equals(pass)){
				System.out.println("You have succesfully logged in " + current.display_name);
			}
		}
	}

	//end arpan code
	
	public void add_user(Person new_user) throws IOException{
		String user=new_user.username+","+new_user.password+","+new_user.display_name+","+new_user.number_of_friends+","+new_user.number_pending_requests+","+new_user.status;
		String filename= "input.txt";
		FileWriter fw=new FileWriter(filename,true);
		try
		{
		     //the true will append the new data
		    fw.write(user+"\n");//appends the string to the file
		    
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
		finally{
			fw.close();
		}

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Reading database file...");
		System.out.println("Network is ready.");
		System.out.println();
		System.out.println("    1.Sign Up");
		System.out.println("    2.Login");
		Scanner in=new Scanner(System.in);
		int a=in.nextInt();
		if(a==1){
			System.out.println("Enter Username: ");
			String garbage=in.nextLine();
			String username=in.nextLine();
			System.out.println("Enter Display Name ");
			String display_name=in.nextLine();
			System.out.println("Enter Password ");
			String password=in.nextLine();
			Person anon=new Person(0,0);
			anon.username=username;
			anon.display_name=display_name;
			anon.password=password;
			anon.status="<add a status>";
			MyNetwork n=new MyNetwork();
			try{
				n.add_user(anon);
			}
			catch(IOException ioe)
			{
			    System.err.println("IOException: " + ioe.getMessage());
			}
		}
		else if (a==2){
			MyNetwork n=new MyNetwork();
			n.reader("input.txt");
			n.login();
		}
	}
	
}
class Person{
	String username;
	String password;
	String display_name;
	int number_of_friends;
	ArrayList friends=new ArrayList();
	int number_pending_requests;
	ArrayList pending=new ArrayList();
	String status;
	public Person(int a,int b){
		this.number_of_friends=a;
		this.number_pending_requests=a;
	}
//	public String get_username(){
//		return this.username;
//	}
//	public String get_password(){
//		return this.password;
//	}
//	public String get_display_name(){
//		return this.password;
//	}
//	public int get_number_of_friends(){
//		return this.number_of_friends;
//	}
//	public ArrayList get_friends(){
//		return this.friends;
//	}
//	public int get_number_pending_requests(){
//		return this.number_pending_requests;
//	}
}