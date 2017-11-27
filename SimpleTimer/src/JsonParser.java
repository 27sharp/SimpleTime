import java.io.BufferedReader;
import java.util.Iterator;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
void parseJson() throws FileNotFoundException, IOException, ParseException {
	JSONParser parser = new JSONParser();
	 
    try {

        Object obj = parser.parse(new FileReader(System.getProperty("user.dir")+"//vakit.txt"));

        JSONObject jsonObject = (JSONObject) obj; //contain raw json data
        //System.out.println(jsonObject);
        
        JSONArray data = (JSONArray) jsonObject.get("data");
        //date
        JSONObject tmp;
        JSONObject date;
        JSONObject date_readable;
        String readable;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        LocalDate localDate;
        localDate = LocalDate.parse("01 Nov 2017", formatter);
        System.out.println(localDate);
        for (int i = 0; i < data.size(); i++) {
        	 tmp =  (JSONObject) data.get(i);
        	 date = (JSONObject) tmp.get("date");
        	 readable = (String) date.get("readable");
        	 
			
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
int getIndexOfDate(JSONArray date) {
	
	//for(int i = 0;i<date.)
	return 0;
	
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
