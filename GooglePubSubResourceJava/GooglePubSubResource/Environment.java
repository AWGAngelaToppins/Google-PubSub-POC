package GooglePubSubResource;

public class Environment {

	public static String getAccessTokenUrl(String brokerName) {
		
		if(brokerName.contains("IIBP1"))
			return "http://pdp1.awg.local:8020";
		else
		if(brokerName.contains("IIBP2"))
			return "http://pdp2.awg.local:8020";
		else
		if(brokerName.contains("IIBQ"))
			return "http://dpowerapp-qa.awg.local:8020";
		else
		if(brokerName.contains("IIBT"))
			return "http://dpdev01-api-dev.awg.local:8020";
//		    return "http://10.1.200.74:2011 ";
		else
			return "http://dpdev01-api-dev.awg.local:8020"; //default to test
	}
	public static String getGooglePubUrl(String brokerName, String topic, String action) {
		
		if(brokerName.contains("IIBP1"))
			return "http://pdp1.awg.local:8021/v1/projects/awgr-masc-stg-01-ops/subscriptions/"+topic.trim()+":"+action.trim();
		else
		if(brokerName.contains("IIBP2"))
			return "http://pdp2.awg.local:8021/v1/projects/awgr-masc-stg-01-ops/subscriptions/"+topic.trim()+":"+action.trim();
		else
		if(brokerName.contains("IIBQ"))
			return "http://dpowerapp-qa.awg.local:8021/v1/projects/awgr-masc-stg-01-ops/subscriptions/"+topic.trim()+":"+action.trim();
		else
		if(brokerName.contains("IIBT"))
			return "http://dpdev01-api-dev.awg.local:8021/v1/projects/awgr-masc-stg-01-ops/subscriptions/"+topic.trim()+":"+action.trim();
//		    return "http://10.1.200.74:2013/v1/projects/awgr-masc-stg-01-ops/subscriptions/";
		else
			return "http://dpdev01-api-dev.awg.local:8021/v1/projects/awgr-masc-stg-01-ops/subscriptions/"+topic.trim()+":"+action.trim(); //default to test
	}	
}
