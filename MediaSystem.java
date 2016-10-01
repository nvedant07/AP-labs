

public class MediaSystem {

	public static void readFileByLine(String fileName) {
  		try{
   			File file = new File(fileName);
   			Scanner scanner = new Scanner(file);
   			String s = new String();
   			while (scanner.hasNext()) {
    			s=scanner.next();
    			String[] arr = thisLine.split(",");
    			Movie M = new Movie(arr[0],arr[1],Integer.parseInt(arr[2]),arr[3],Integer.parsefloat(arr[4]),Integer.parseInt(arr[5]),arr[6],arr[7],arr[8],arr[9]);
   			}
   		scanner.close();
  		}
  		catch (FileNotFoundException e) {
   			e.printStackTrace();
  		} 
	}

	public static void main(String[] args) {
		//Set<Media> set = new Hashset<Media>();


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