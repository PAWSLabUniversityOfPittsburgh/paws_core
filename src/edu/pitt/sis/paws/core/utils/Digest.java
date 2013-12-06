package edu.pitt.sis.paws.core.utils;

import java.security.MessageDigest;

public class Digest
{
	public static String SHA1(String _source)
	{
		StringBuffer hexString = new StringBuffer();
		try
		{
			// SHA1
			MessageDigest sha = MessageDigest.getInstance("SHA");
			byte[] src_byte = _source.getBytes();
			
			sha.update(src_byte);
			byte[] tgt_byte = sha.digest();
			
	        for (int i = 0; i < tgt_byte.length; i++) {
	        	_source = Integer.toHexString(0xFF & tgt_byte[i]);

	            if (_source.length() < 2) {
	            	_source = "0" + _source;
	            }
	            hexString.append(_source);
	        }
		}
		catch(Exception e) { e.printStackTrace(System.out); };
		return hexString.toString();
	}
	
	public static String MD5(String _source)
	{
		StringBuffer hexString = new StringBuffer();
		try
		{
			// SHA1
			MessageDigest sha = MessageDigest.getInstance("MD5");
			byte[] src_byte = _source.getBytes();
			
			sha.update(src_byte);
			byte[] tgt_byte = sha.digest();
			
	        for (int i = 0; i < tgt_byte.length; i++) {
	        	_source = Integer.toHexString(0xFF & tgt_byte[i]);

	            if (_source.length() < 2) {
	            	_source = "0" + _source;
	            }
	            hexString.append(_source);
	        }
		}
		catch(Exception e) { e.printStackTrace(System.out); };
		return hexString.toString();
	}
}
