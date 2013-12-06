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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Item implements iItem, Serializable//, Comparable
{
	static final long serialVersionUID = 3L;
	
	private int id;
	private String title;
	private int iTag;
	private String sTag;

	public Item()
	{
		id = 0;
		title = "Default Item Title";
		iTag = 0;
		sTag = "";
	}
	
	public Item(int _id, String _title)
	{
		id = _id;
		title = (_title==null) ? "" : _title;
		iTag = 0;
		sTag = "";
	}

	public int getId() { return id; }

	public void setId(int _id) { id = _id; }
	
	public int getITag() { return iTag; }

	public void setITag(int _itag) { iTag = _itag; }
	
	public String getSTag() { return sTag; }

	public void setSTag(String _stag) { sTag = _stag; }
	
	public String getTitle() { return title; }
	
	public void setTitle(String _title) { title = _title; }
	
	public String toString() { return this.getClass().getName() + ":: title='" + title + "' id=" + id + ""; }
	
	public boolean equals(Object obj)
	{
		return ( (this.getId() == ((iItem)obj).getId()) &&
			(this.getTitle().equals(((iItem)obj).getTitle())) );
	}	
	
	/** Writing the object into the stream
	 * @param out - the output stream */
	private void writeObject(ObjectOutputStream out) throws IOException
	{
		out.defaultWriteObject();
	}
	
	/** Reading the object from the stream
	 * @param in - the input stream */
	private void readObject(ObjectInputStream in)
		throws IOException, ClassNotFoundException
	{
		in.defaultReadObject();
	}

}
