package Group_labs;

public class SearchException extends Exception{
	private String str;
    public SearchException(String name){
        this.str = name;
    }
    public String toString(){
       return "Username "+str+" not found!";
    }
}
