package ModelClasses;
import org.json.JSONObject;

public class Model {
    public static void main(String [] args){
        JSONObject jo = new JSONObject("{ \"abc\" : \"def\" }");
        System.out.println(jo);
    }
}
