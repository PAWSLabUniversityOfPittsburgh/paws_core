package edu.pitt.sis.paws.core.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StreamUtils
{
	public static String stack2string(Exception e)
	{
		try
		{
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			return sw.toString();
		}
		catch(Exception e2)
		{
			return "bad stack2string";
		}
	}// -- 	stack2string
}
