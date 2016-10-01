package Group_labs;
import java.util.*;

public class MatchingException extends Exception{
    private String str;
    public MatchingException(){
        this.str = "Username and paasword do not match";
    }
    public String toString(){
       return "Username and paasword do not match";
    }
}