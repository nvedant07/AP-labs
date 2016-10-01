import java.util.*;
import java.lang.*;
import java.io.*;

public class MediaSystem {

	static HashSet<Movie> movies = new HashSet<Movie>();
	static HashSet<Song> songs = new HashSet<Song>();
	
	public static void readmovie(String file) {
   		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
    		String line;
    		while ((line = br.readLine()) != null){
    			String[] arr = line.split(",");
    			movies.add(new Movie(arr[0],arr[1],Integer.parseInt(arr[2]),arr[3],Float.parseFloat(arr[4]),Integer.parseInt(arr[5]),arr[6],arr[7],arr[8],arr[9].charAt(0)));
    		}
   			br.close();
  		}	
  		catch(Exception e){
        	e.printStackTrace();
      } 
	}

	public static void readsong(String file) {
   		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
    		String line;
    		while ((line = br.readLine()) != null){
    			String[] arr = line.split(",");
    			songs.add(new Song(arr[0],arr[2],Integer.parseInt(arr[3]),arr[4],Float.parseFloat(arr[5]),Integer.parseInt(arr[6]),arr[7]));
    		}
   			br.close();
  		}	
  		catch(Exception e){
        	e.printStackTrace();
      } 
	}

	public static void main(String[] args) {


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

	@Override
	public int hashCode(){
        String str=this.title;
        return str.hashCode();
    }

    @Override 
    public boolean equals(Object obj){
        System.out.println("In equals");
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
}