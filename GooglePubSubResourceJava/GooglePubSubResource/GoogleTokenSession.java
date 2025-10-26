package GooglePubSubResource;

import java.util.Date;
import java.util.Hashtable;
import java.util.Set;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GoogleTokenSession {
	
	private static Hashtable<String, TokenSession> sessionHashtable = new Hashtable<String, TokenSession>();
	
	public static synchronized String getToken() throws ParseException {

		if (sessionHashtable.containsKey("expiredDate") && sessionHashtable.containsKey("accessToken") ) {
			String expiredDateString = sessionHashtable.get("expiredDate").myValue;
			Date expiredDate = convertToDate(expiredDateString);
			Date currentDate = getCurrentDate();
			
			if(currentDate.after(expiredDate)) {
				resetSessionMap();
				return "getNewToken";
			}else
            {
				System.out.println(sessionHashtable.get("accessToken").myValue);
				return sessionHashtable.get("accessToken").myValue;
			}
		} else {
			resetSessionMap();
			return "getNewToken";
		}
	}

	public static synchronized String setToken(String accessToken, int expiredInNbrOfSeconds) {

		if(expiredInNbrOfSeconds>0) {
			resetSessionMap();
			
	//		TokenSession tokenSession = new GoogleTokenSession().new TokenSession("Bearer "+accessToken);
			TokenSession tokenSession = new GoogleTokenSession().new TokenSession(accessToken);
			sessionHashtable.put("accessToken", tokenSession);
			
			String expiredDateString = getExpiredDate(expiredInNbrOfSeconds);
			TokenSession expiredSession = new GoogleTokenSession().new TokenSession(expiredDateString);
			sessionHashtable.put("expiredDate", expiredSession);
			return "setToken";
		}else {
		return "dont setToken";
		}
	}
	public class TokenSession {
		public String myValue = new String();
		public Date myLastUsed = new Date();

		public TokenSession(String inputValue) {
			myValue = inputValue;
		}
	}
	public static String getExpiredDate(int addSecs) {
		
		Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.SECOND, addSecs); // Add seconds to get the expired time.
        Date expiredDate = calendar.getTime();

        //System.out.println("Expired Date:  " + expiredDate);
        
        return convertToStringDate(expiredDate);
	}
	public static synchronized void resetSessionMap() {
		Set<String> hashMapKeys = sessionHashtable.keySet(); 
		for (String key : hashMapKeys) {
			sessionHashtable.remove(key);
		}
	}
	public static Date getCurrentDate() {
		Date currentDate = new Date();
        return currentDate;
	}	
	public static String convertToStringDate(Date inputDate) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");
		return fmt.format(inputDate);
	}
	public static Date convertToDate(String inputDate) throws ParseException {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");
		return fmt.parse(inputDate);
	}

}
