package GooglePubSubResource;

import java.io.IOException;
import java.io.StringReader;
import java.util.Base64;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ConvertDataTypes {

	public static String removeExtraChars(String inputValue){
		
		String test1 = inputValue.replace("[\"", "");
		String test2 = test1.replace("\"]", "");
		String test3 = test2.replace("'", "\"");
		
		return test3;
	}
	
	public static String buildJSONmessage(String googleID, String jsonValue) {
		
		StringBuilder newValue = new StringBuilder();
		newValue.append("{").append("\"googleMsgID\":").append("\"").append(googleID).append("\",");
		newValue.append(jsonValue);
		newValue.append("}");

		return newValue.toString();
	}
	public static String convertBase64ToString(String inputValue) {
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] decodedBytes = decoder.decode(inputValue.getBytes());
		
		String test = new String(decodedBytes).trim();

		String test2 = new String(decodedBytes);

		String test3 = new String(decodedBytes).toString();

		String test4 = test.replace("\r\n", "");

		String test5 = test4.replace(" ", "");
		
		//String test6 = test5.replace("\"","'");

		String removeFirstBracket = test5.substring(0,test5.length());
		String removeLastBracket = removeFirstBracket.substring(1,removeFirstBracket.length()-1);

		return removeLastBracket;
	}
	public static Object convertStringToObj(String value) throws ParserConfigurationException, SAXException, IOException {
		String xmlString = "abc";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
	    DocumentBuilder builder        = factory.newDocumentBuilder(); 
	    InputSource is                 = new InputSource(new StringReader(xmlString)); 
	    Document dom                   = builder.parse(is); 
	    return dom;
	}
}
