package Group_labs;

public class ExistsException extends Exception{
	private String str;
    public ExistsException(){
        this.str = "Username Exists!";
    }
    public String toString(){
       return "Username Exists!";
    }
}
