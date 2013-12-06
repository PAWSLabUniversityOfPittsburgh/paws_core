/* Disclaimer:
 * 	Java code contained in this file is created as part of educational
 *    research and development. It is intended to be used by researchers of
 *    University of Pittsburgh, School of Information Sciences ONLY.
 *    You assume full responsibility and risk of lossed resulting from compiling
 *    and running this code.
 */
 
/**
 * @author Michael V. Yudelson
 */

package edu.pitt.sis.paws.core;

public class Item2 extends Item implements iItem2
{
	static final long serialVersionUID = 4L;
	
	private String uri;
	
	public Item2()
	{
		super();
		uri = "No URI";
	}

	public Item2(int _id, String _title)
	{
		super();
		uri = "No URI";
	}

	public Item2(int _id, String _title, String _uri)
	{
		super(_id, _title);
		uri = _uri;
	}

	public String toString()
	{
		return super.toString() + " uri='" + uri + "'";
	}

	public String getURI() { return uri; }
	public void setURI(String _uri) { uri = _uri; }
	
}
