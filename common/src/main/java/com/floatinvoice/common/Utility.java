package com.floatinvoice.common;

import java.lang.reflect.Field;
import java.security.SecureRandom;

public class Utility {

	static final String acro = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	static final String password = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	
	static SecureRandom rnd = new SecureRandom();
	
	public static String acroRandomString( int len ){		
		StringBuilder sb = new StringBuilder( len );
		   for( int i = 0; i < len; i++ ) 
		      sb.append( acro.charAt( rnd.nextInt(acro.length()) ) );
		   return sb.toString();		
	}
	
	public static String passwdString( int len ){		
		StringBuilder sb = new StringBuilder( len );
		   for( int i = 0; i < len; i++ ) 
		      sb.append( password.charAt( rnd.nextInt(password.length()) ) );
		   return sb.toString();		
	}
	
	public static boolean set(Object object, String fieldName, Object fieldValue) {
	    Class<?> clazz = object.getClass();
	    while (clazz != null) {
	        try {
	            Field field = clazz.getDeclaredField(fieldName);
	            field.setAccessible(true);
	            field.set(object, fieldValue);
	            return true;
	        } catch (NoSuchFieldException e) {
	            clazz = clazz.getSuperclass();
	        } catch (Exception e) {
	            throw new IllegalStateException(e);
	        }
	    }
	    return false;
	}
}
