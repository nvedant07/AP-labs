package Group_labs;

import java.util.*;
import java.lang.*;
import java.io.*;

public class MediaSystem {

	static HashSet<Movie> movies = new HashSet<Movie>();
	static HashSet<Song> songs = new HashSet<Song>();
	private static int movies_count;
	private static int songs_count;
	
	public static void set_movies_count(){
		try(BufferedReader br = new BufferedReader(new FileReader("movie.txt"))) {
    		String line;
    		int count=0;
    		String garbage=br.readLine();
    		while ((line = br.readLine()) != null){
    			count++;
    		}
   			br.close();
   			movies_count=count;
  		}	
  		catch(FileNotFoundException e) {	
			System.out.println("No Input TXT file! Deserializing data");
			System.exit(0);
		}
		catch(IOException e){	
			System.out.println("An I/O Error Occurred");
			System.exit(0);
		}catch(Exception e){
        	e.printStackTrace();
  		}
	}
	
	public static void set_songs_count(){
		try(BufferedReader br = new BufferedReader(new FileReader("song.txt"))) {
    		String line;
    		int count=0;
    		String garbage=br.readLine();
    		while ((line = br.readLine()) != null){
    			count++;
    		}
   			br.close();
   			songs_count=count;
  		}	
  		catch(FileNotFoundException e) {	
			System.out.println("No Input TXT file! Deserializing data");
			System.exit(0);
		}
		catch(IOException e){	
			System.out.println("An I/O Error Occurred");
			System.exit(0);
		}
		catch(Exception e){
        	e.printStackTrace();
  		}
	}
	
	public static void readmovie(String file) {
   		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
    		String line;
    		int count=0;
    		String garbage=br.readLine();
    		while ((line = br.readLine()) != null){
    			String[] arr = line.split(",");
    			count++;
    			movies.add(new Movie(arr[0],arr[1],Integer.parseInt(arr[2]),arr[3],Float.parseFloat(arr[4]),Integer.parseInt(arr[5]),arr[6],arr[7],arr[8],arr[9].charAt(0)));
    		}
   			br.close();
   			movies_count=count;
  		}
  		catch(FileNotFoundException e) {	
			System.out.println("No Input TXT file! Deserializing data");
			System.exit(0);
		}
		catch(IOException e){	
			System.out.println("An I/O Error Occurred");
			System.exit(0);
		}	
  		catch(Exception e){
        	e.printStackTrace();
      } 
	}
	
	public static void readsong(String file) {
   		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
    		String line;
    		int count=0;
    		String garbage=br.readLine();
    		while ((line = br.readLine()) != null){
    			String[] arr = line.split(",");
    			Movie film=null;
    			for(Movie films:movies){
            		if(arr[1].equals(films.gettitle())){
            			film=films;
            			break;
            		}
        		}
    			count++;
    			songs.add(new Song(arr[0], film, arr[2],Integer.parseInt(arr[3]),arr[4],Float.parseFloat(arr[5]),Integer.parseInt(arr[6]),arr[7]));
    		}
   			br.close();
   			songs_count=count;
  		}
  		catch(FileNotFoundException e) {	
			System.out.println("No Input TXT file! Deserializing data");
			System.exit(0);
		}
		catch(IOException e){	
			System.out.println("An I/O Error Occurred");
			System.exit(0);
		}	
  		catch(Exception e){
        	e.printStackTrace();
        } 
	}

	public static Movie searchDirector(String direction){
		
	}

	public static void main(String[] args) {
		
		MediaSystem m=new MediaSystem();
		m.set_movies_count();
		m.set_songs_count();
		if(new File("movie.ser").isFile()){//deserialize
			Movie e = null;
			try{
				FileInputStream fileIn = new FileInputStream("movie.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				for(int j=0;j<movies_count;j++){
					e = (Movie) in.readObject();
					movies.add(e);
				}
				in.close();
				fileIn.close();
			}
			catch(IOException i) {
				i.printStackTrace();
				return;
			}
			catch(ClassNotFoundException c) {
				System.out.println("Movie class not found");
				c.printStackTrace();
				return;
			}	
		}
		else{//serialze
			m.readmovie("movie.txt");
			try{
				FileOutputStream fileOut = new FileOutputStream("movie.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				for(Movie film:movies){
					out.writeObject(film);
				}
				out.close();
				fileOut.close();
			}
			catch(IOException i){
				i.printStackTrace();
			}
		}
		if(new File("song.ser").isFile()){//deserialize
			Song e = null;
			try{
				FileInputStream fileIn = new FileInputStream("song.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				for(int j=0;j<songs_count;j++){
					e = (Song) in.readObject();
					songs.add(e);
				}
				in.close();
				fileIn.close();
			}
			catch(IOException i){
				i.printStackTrace();
				return;
			}
			catch(ClassNotFoundException c){
				System.out.println("Song class not found");
				c.printStackTrace();
				return;
			}
		}
		else{//serialize
			m.readsong("song.txt");
			try {
				FileOutputStream fileOut = new FileOutputStream("song.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				for(Song s:songs){
					out.writeObject(s);
				}
				out.close();
				fileOut.close();
			}catch(IOException i) {
				i.printStackTrace();
			}
		}
		for(Movie mov : movies){
			System.out.println(mov.title);
		}
		for(Song mov : songs){
			System.out.println(mov.title);
		}
	}

}

class Media implements java.io.Serializable{
	protected String title;
	protected String artist;
	protected int year;
	protected float size;
	protected int rating;
	protected String duration;
	protected String genre;

	public String gettitle(){
		return this.title;
	}

	@Override
	public int hashCode(){
        String str=this.title;
        return str.hashCode();
    }

    @Override 
    public boolean equals(Object obj){
//        System.out.println("In equals");
        if (obj instanceof Media) {
            Media pp = (Media) obj;
            return (pp.title.equals(this.title));
        } else {
            return false;
        }
    }
	
}

class Movie extends Media{
	private String director;
	private String producer;
	private char certification;

	Movie(String name,String actor, int release, String type, float space, int stars, String time, String direction, String produce, char certificate){
		this.title=name;
		this.artist=actor;
		this.year=release;
		this.genre=type;
		this.size=space;
		this.rating=stars;
		this.duration=time;
		this.director=direction;
		this.producer=producer;
		this.certification=certificate;
	}
}

class Song extends Media{
	private Movie m;

	Song(String name, Movie film, String actor, int release, String type, float space, int stars, String time){
		this.title=name;
		this.m=film;
		this.artist=actor;
		this.year=release;
		this.genre=type;
		this.size=space;
		this.rating=stars;
		this.duration=time;
	}
}