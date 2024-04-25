package com.bank.cedrus.util;


import org.json.JSONObject;

public class ParameterUtil {
	
	
	
	public static String extractMetadata(String data, boolean encryptionEnabled) {
		
         if (isJSON(data)) {
            JSONObject jsonObject = new JSONObject(data);
            if (jsonObject.has("metadata") && encryptionEnabled) {
                return jsonObject.optString("metadata");
            }
        }
        return data;
    }

    private static boolean isJSON(String data) {
        try {
            new JSONObject(data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
