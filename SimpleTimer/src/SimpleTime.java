import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public class SimpleTime {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		JsonParser jp = new JsonParser();
		jp.getUrl();
		jp.parseJson();
	}

}