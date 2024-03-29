/*
	@Authors:
	* Vedant Nanda - 2015114
	* Arpan Mondal - 2015132
 */

package Group_labs;

import java.util.*;
import java.io.*;

public class MyNetwork {
	//arpan code
	static ArrayList persons=new ArrayList();
	static int size=0;
	static int logged_in=-1;
	static String logged_in_username;
	public void reader(String file){
		persons.clear();
		File database = new File(file);
		String thisLine = null;
		try {			
			BufferedReader data = new BufferedReader(new FileReader(database));
			while ((thisLine = data.readLine()) != null){
				String[] array = thisLine.split(",");
				int friends_count = Integer.parseInt(array[3]);
//				System.out.println(array[0]+" "+array[1]+" "+array[2]+" "+array[3]+" "+array[4+friends_count]);
				int requests = Integer.parseInt(array[3+friends_count+1]);
				Person p = new Person(friends_count, requests,array[0],array[1],array[2]);
				for(int i=4; i<(4+friends_count); i++){
					p.friends.add(array[i]);
				}
				for(int i=5+friends_count; i<(5+friends_count+requests); i++){
					p.pending.add(array[i]);
				}
				p.set_status(array[5+friends_count+requests]);
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
	
	public static void update() throws IOException{
		PrintWriter pw = new PrintWriter("input.txt");
		pw.close();
		for(int i=0;i<persons.size();i++){
			Person new_user=(Person)persons.get(i);
			String friends_name="",pending="";
			for(int j=0;j<new_user.friends.size();j++){
				friends_name+=new_user.friends.get(j);
				friends_name+=",";
			}
			for(int j=0;j<new_user.pending.size();j++){
				pending+=new_user.pending.get(j);
				pending+=",";
			}
			String user=new_user.get_username()+","+new_user.get_password()+","+new_user.get_display_name()+","+new_user.get_number_of_friends()+","+friends_name+new_user.get_number_pending_requests()+","+pending+new_user.get_status();
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
	}
	
	public void login() throws Exception{
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
				System.out.println(current.get_status());
				logged_in=i;
				logged_in_username=current.get_username();
			}
		}
		if(logged_in<0){
			throw new MatchingException();			
//			System.out.println("Username and password do not match");
		}
	}
	//end arpan code
	public void add_user(Person new_user) throws IOException,Exception{
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
			throw new ExistsException();
//			System.out.println("Username already exists!");
		}
	}
	
	public void list_friends(){
		for(int i=0;i<persons.size();i++){
			Person iter=(Person)persons.get(i);
//			System.out.println(iter.get_username());
			if(iter.get_username().equals(logged_in_username)){
				System.out.print("Your friends are: ");
				for(int j=0;j<iter.friends.size();j++){
					System.out.print(iter.friends.get(j)+" ");
				}
				System.out.println();
			}
		}
	}
	
	public void search(String name) throws Exception{
		boolean friend=false;
		boolean pending=false;
		boolean exists=false;
		Scanner in=new Scanner(System.in);
		Person user=new Person(0,0,"","","");
		Person searched=new Person(0,0,"","","");
		ArrayList mutual_friends=new ArrayList();
		
		for(int i=0;i<persons.size();i++){
			Person iter=(Person)persons.get(i);
			if(iter.get_username().equals(name)){
				exists=true;
				for(int j=0;j<iter.friends.size();j++){
					if(logged_in_username.equals(iter.friends.get(j))){
						friend=true;
					}
				}
			}
		}
		if(exists){
			for(int i=0;i<persons.size();i++){
				Person iter=(Person)persons.get(i);
				if(name.equals(iter.get_username())){
					searched=iter;
				}
				if(logged_in_username.equals(iter.get_username())){
					user=iter;
				}
			}
			
			for(int i=0;i<user.friends.size();i++){
				for(int j=0;j<searched.friends.size();j++){
					if(user.friends.get(i).equals(searched.friends.get(j))){
						mutual_friends.add(user.friends.get(i));
					}
				}
			}
			
			if(friend){
				System.out.println("You and "+name+" are friends.\n");
				System.out.println("Display name: "+searched.get_display_name());
				System.out.println("Status: "+searched.get_status());
				System.out.print("Friends: ");
				for(int i=0;i<searched.friends.size();i++){
					System.out.print(searched.friends.get(i)+" ");
				}
				System.out.println();
				System.out.print("Mutual Friends: ");
				if(mutual_friends.size()==0)System.out.println("No mutual friends");
				else{
					for(int i=0;i<mutual_friends.size();i++){
						System.out.print(mutual_friends.get(i)+" ");
					}
					System.out.println();
				}
				System.out.println("    b.Back");
				char opt=in.next().charAt(0);
			}
			else{
				System.out.println(name+" is not a friend");
				System.out.print("Mutual Friends: ");
				if(mutual_friends.size()==0)System.out.println("No mutual friends");
				else{
					for(int i=0;i<mutual_friends.size();i++){
						System.out.print(mutual_friends.get(i)+" ");
					}
					System.out.println();
				}
				MyNetwork n=new MyNetwork();
//				System.out.println(n.shortest_path());
				n.shortest_path(name);
//				System.out.println("Shortest Path:TBD");
				for(int i=0;i<searched.pending.size();i++){
					if(logged_in_username.equals(searched.pending.get(i))){
						pending=true;
					}
				}
				if(pending){
					System.out.println("Request Pending.\n");
					System.out.println("    1.Cancel request");
					System.out.println("    b.Back");
					char opt=in.next().charAt(0);
					if(opt=='1'){
						try{
							n.cancel_request(name);
						}
						catch(IOException ioe)
						{
						    System.err.println("IOException: " + ioe.getMessage());
						}
						System.out.println("Request cancelled.");
					}
				}
				else{
					System.out.println("    1.Send request");
					System.out.println("    b.Back");
					char opt=in.next().charAt(0);
					if(opt=='1'){
						try{
							n.send_request(name);
						}
						catch(IOException ioe)
						{
						    System.err.println("IOException: " + ioe.getMessage());
						}
						System.out.println("Request sent.");
						n.search(name);
					}
				}
			}
		}
		else{
			throw new SearchException(name);
		}
	}
	
	public void send_request(String name)throws IOException{
		
		for(int i=0;i<persons.size();i++){
			Person iter=(Person)persons.get(i);
			if(name.equals(iter.get_username())){
				iter.pending.add(logged_in_username);
				iter.set_number_pending_requests(iter.get_number_pending_requests()+1);
				persons.set(i,iter);
			}
		}
		PrintWriter pw = new PrintWriter("input.txt");
		pw.close();
		for(int i=0;i<persons.size();i++){
			Person new_user=(Person)persons.get(i);
			String friends_name="",pending="";
			for(int j=0;j<new_user.friends.size();j++){
				friends_name+=new_user.friends.get(j);
				friends_name+=",";
			}
			for(int j=0;j<new_user.pending.size();j++){
				pending+=new_user.pending.get(j);
				pending+=",";
			}
			String user=new_user.get_username()+","+new_user.get_password()+","+new_user.get_display_name()+","+new_user.get_number_of_friends()+","+friends_name+new_user.get_number_pending_requests()+","+pending+new_user.get_status();
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
	}
	
	public void cancel_request(String name)throws IOException{
		
		for(int i=0;i<persons.size();i++){
			Person iter=(Person)persons.get(i);
			if(name.equals(iter.get_username())){
				int index=iter.pending.size()-1;
				for(int j=0;j<iter.pending.size();j++){
					if(iter.pending.get(j).equals(logged_in_username)){
						index=j;
					}
				}
				iter.pending.remove(index);
				iter.set_number_pending_requests(iter.get_number_pending_requests()-1);
				persons.set(i,iter);
			}
		}
		PrintWriter pw = new PrintWriter("input.txt");
		pw.close();
		for(int i=0;i<persons.size();i++){
			Person new_user=(Person)persons.get(i);
			String friends_name="",pending="";
			for(int j=0;j<new_user.friends.size();j++){
				friends_name+=new_user.friends.get(j);
				friends_name+=",";
			}
			for(int j=0;j<new_user.pending.size();j++){
				pending+=new_user.pending.get(j);
				pending+=",";
			}
			String user=new_user.get_username()+","+new_user.get_password()+","+new_user.get_display_name()+","+new_user.get_number_of_friends()+","+friends_name+new_user.get_number_pending_requests()+","+pending+new_user.get_status();
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
	}
	
	public int find_pos(String username){
		int pos=0;
		for(int i=0;i<persons.size();i++){
			Person iter=(Person)persons.get(i);
			if(iter.get_username().equals(username)){
				pos = i;
			}
		}
		return pos;
	}
	
	public void shortest_path(String name){
//		MyNetwork m=new MyNetwork();
		int start_pos=find_pos(logged_in_username);
		
		boolean visited[] = new boolean[persons.size()];
		 
        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        visited[start_pos]=true;
        queue.add(start_pos);
 
        while (queue.size() != 0)
        {
            start_pos = queue.poll();
            Person iter=(Person)persons.get(start_pos);
            for(int i=0;i<iter.friends.size();i++){
            	int n=find_pos((String)iter.friends.get(i));
            	if(!visited[n]){
            		visited[n] = true;
            		Person temp=(Person)persons.get(n);
            		temp.parent=start_pos;
            		persons.set(n,temp);
                    queue.add(n);
            	}
            }
        }
        System.out.print("Shortest Path: ");
        print_path(find_pos(logged_in_username),find_pos(name));
        System.out.println();
	}
	
	public void print_path(int start,int end){
		ArrayList path=new ArrayList();
		
		Person s=(Person)persons.get(start);
		Person v=(Person)persons.get(end);
		
		if(v.get_username().equals(s.get_username())){
			System.out.print(v.get_username()+"->");
		}
		else if(v.parent==-1){
			System.out.print("No path exists!");
		}
		else{
			print_path(start,v.parent);
			System.out.print(v.get_username()+"->");
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
//				System.out.println(display_name);
				System.out.println("Enter Password ");
				String password=in.nextLine();
				Person anon=new Person(0,0,username,password,display_name);
				anon.set_status("<add a status>");
				try{
					n.add_user(anon);
				}
				catch(IOException ioe)
				{
				    System.err.println("IOException: " + ioe.getMessage());
				}
				catch(Exception e){
					System.out.println(e.toString());
				}
				System.out.println("    1.Sign Up");
				System.out.println("    2.Login");
				a=in.nextInt();
			}
			if (a==2){
				MyNetwork n=new MyNetwork();
				n.reader("input.txt");
				try{
					n.login();
				}
				catch(Exception e){
					System.out.println(e.toString());
				}
			}
			while(logged_in>=0){
				System.out.println("    1.List Friends");
				System.out.println("    2.Search");
				System.out.println("    3.Update Status");
				System.out.println("    4.Pending request");
				System.out.println("    5.logout");
				a=in.nextInt();
				if(a==1){
					MyNetwork n=new MyNetwork();
					n.reader("input.txt");
					n.list_friends();
				}
				if(a==2){
					MyNetwork n=new MyNetwork();
					n.reader("input.txt");
					System.out.println("Enter name:");
					in.nextLine();
					String name=in.nextLine();
					try{
						n.search(name);
					}
					catch(Exception e){
						System.out.println(e.toString());
					}
				}
				if(a==3){
					MyNetwork n=new MyNetwork();
					n.reader("input.txt");
					Person iter=(Person)persons.get(logged_in);
					System.out.println("Enter new status: ");
					String garbage=in.nextLine();
					String str=in.nextLine();
					iter.set_status(str);
					persons.set(logged_in, iter);
					try{
						update();
					}
					catch(IOException ioe){
				    System.err.println("IOException: " + ioe.getMessage());
					}
					System.out.println("Status Updated!");
				}
				foo:{if(a==4){
					MyNetwork n=new MyNetwork();
					n.reader("input.txt");
					Person iter=(Person)persons.get(logged_in);
					System.out.print("You have "+iter.get_number_pending_requests()+" friend requests");
					if(iter.get_number_pending_requests()==0){
						System.out.println("!");
					}
					else{	
						System.out.println(":");
						int j;
						for(j=0;j<iter.pending.size();j++){
							System.out.println((j+1)+". "+iter.pending.get(j));
						}
						System.out.println("b. back");
						System.out.print("Enter number: ");
						String st=in.next();
						char opt=st.charAt(0);
						if(opt=='b')break foo;
						int pend = Integer.parseInt(st);
						System.out.print(iter.pending.get(pend-1));
						System.out.println("Enter choice: \n\t1.Accept\n\t2.Reject\n\t");
						int choi=in.nextInt();
						if(choi==1){
							for(int i=0;i<persons.size();i++){
								Person fri = (Person)persons.get(i);
								if (fri.get_username().equals(iter.pending.get(pend-1))){
									iter.friends.add(fri.get_username());
									iter.set_number_of_friends(iter.get_number_of_friends()+1);
									iter.set_number_pending_requests(iter.get_number_pending_requests()-1);
									iter.pending.remove(pend-1);
									fri.friends.add(iter.get_username());
									fri.set_number_of_friends(fri.get_number_of_friends()+1);
								}
							}
							System.out.println("You are now friends with "+iter.friends.get(iter.get_number_of_friends()-1));
						}
						else if(choi==2){
							iter.set_number_pending_requests(iter.get_number_pending_requests()-1);
							iter.pending.remove(pend-1);
							System.out.println("Friend Request Rejected");
						}
						else{
							break;
						}
						try{
							update();
						}
						catch(IOException ioe){
					    System.err.println("IOException: " + ioe.getMessage());
						}
					}
				}}
				if(a==5){
					logged_in=-1;
					logged_in_username=null;
					System.out.println("    1.Sign Up");
					System.out.println("    2.Login");
				}
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
	int parent=-1;
	
	public Person(int a,int b,String username,String password,String display_name){
		this.number_of_friends=a;
		this.number_pending_requests=b;
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