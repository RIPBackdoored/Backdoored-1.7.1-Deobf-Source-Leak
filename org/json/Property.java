package org.json;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.Properties;

public class Property
{
    public Property() {
        super();
    }
    
    public static JSONObject toJSONObject(final Properties properties) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        if (properties != null && !properties.isEmpty()) {
            final Enumeration<?> propertyNames = properties.propertyNames();
            while (propertyNames.hasMoreElements()) {
                final String s = (String)propertyNames.nextElement();
                jsonObject.put(s, properties.getProperty(s));
            }
        }
        return jsonObject;
    }
    
    public static Properties toProperties(final JSONObject jsonObject) throws JSONException {
        final Properties properties = new Properties();
        if (jsonObject != null) {
            for (final String s : jsonObject.keySet()) {
                final Object opt = jsonObject.opt(s);
                if (!JSONObject.NULL.equals(opt)) {
                    ((Hashtable<String, String>)properties).put(s, opt.toString());
                }
            }
        }
        return properties;
    }
}
