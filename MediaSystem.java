package Group_labs;

public class MediaSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Media implements java.io.Serializable{
	private String title;
	private String artist;
	private int year;
	private float size;
	private int rating;
	private String duration;
	private String genre;
	
}
class Movie extends Media{
	private String director;
	private String producer;
	private char certification;
}
class Song extends Media{
	private Movie m;
}