import java.io.BufferedReader;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {
	private static URL url;
	private static InputStream is;
	private static BufferedReader bf;
	private static String line;
	private static File file = new File(System.getProperty("user.dir")+"//vakit.txt");
	private static FileWriter fw;
	//contain json  of one day
	private static JSONObject day_raw;
    //contain json  of Dates
    private static JSONObject date_raw;
    //contain json  of readable date
    private static String date_readable;
	
void parseJson() throws FileNotFoundException, IOException, ParseException {
	JSONParser parser = new JSONParser();
	 
    try {

        Object obj = parser.parse(new FileReader(System.getProperty("user.dir")+"//vakit.txt"));
     
        //contain raw json data
        JSONObject jsonObject = (JSONObject) obj; 
        //System.out.println(jsonObject);
        
        //contain Json Array of all data
        JSONArray data = (JSONArray) jsonObject.get("data");
        
        if(getIndexOfDate(data) == -1) {
        	System.out.println("could not find date in db!");
        }
        
        
        
       
    }finally {
		
    }
}
void replaceEnd() throws IOException {
	Path path = Paths.get(System.getProperty("user.dir")+"//vakit.txt");
	Charset charset = StandardCharsets.UTF_8;

	String content = new String(Files.readAllBytes(path), charset);
	content = content.replace(" (CET)", "");
	Files.write(path, content.getBytes(charset));
	}
int getIndexOfDate(JSONArray data) {
	
	DateFormat df = new SimpleDateFormat("d MMM yyyy");
	//Format system date in custom date to compare dates
	String today = df.format(new Date());
	 for (int i = 0; i < data.size(); i++) {
		 day_raw =  (JSONObject) data.get(i);
    	 date_raw = (JSONObject) day_raw.get("date");
    	 date_readable = (String) date_raw.get("readable");
    	 
    	 if(today.equals(date_readable)){
    		 //System.out.println(i);
    		 return i;
    	 }
	 }
	
	return -1;
	
}
void getUrl() {
	
	

	try {
		url = new URL("http://api.aladhan.com/calendarByCity?city=Duisburg&country=DE&method=3&tune=0,0,0,7,3,6,0,-30");
		is = url.openStream(); 
		bf = new BufferedReader(new InputStreamReader(is));
		fw = new FileWriter(file);
		
		//schreibt Quellcode in Datei
		while ((line = bf.readLine())!= null){
			fw.write(line);
		}
		
		
		fw.flush();
		fw.close();
		is.close();
		replaceEnd(); //replace (CET) after time
		
		
		
	} catch (Exception e) {
		System.out.println("Fehler beim Abrufen! Bitte Internetverbindung pruefen!");
		e.printStackTrace();
		
	}
}
}
