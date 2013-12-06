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
import java.util.*;

public class Item2Vector<E extends iItem2> extends ItemVector<E>
{
    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = -2767605614048989439L;
	
//	private boolean id_sorted;
//	private boolean title_sorted;
	private boolean uri_sorted;
//	private Vector<E> sorted_by_id;
//	private Vector<E> sorted_by_title;
	private Vector<E> sorted_by_uri;
//	private Comparator<E> cmpId;
//	private Comparator<E> cmpTitle;
	private Comparator<iItem2> cmpURI;
	
	public Item2Vector()
	{
		super();
//		id_sorted = false;
//		title_sorted = false;
		uri_sorted = false;
		
//		sorted_by_id = new Vector<E>(0, 0);
//		sorted_by_title = new Vector<E>(0, 0);
		sorted_by_uri = new Vector<E>(0, 0);
		
//		cmpId = new ItemIdComparator<E>();
//		cmpTitle = new ItemTitleComparator<E>();
		cmpURI = new ItemURIComparator<iItem2>();
	}

	public boolean add(E _e)
	{
		boolean res = super.add(_e);
		if(res)
		{
//			sorted_by_id.add(_e);
//			sorted_by_title.add(_e);
			sorted_by_uri.add(_e);
//			id_sorted = false;
//			title_sorted = false;
			uri_sorted = false;
		}
		return res;
	}
	
//	public void add(int _idx, E _e) throws ArrayIndexOutOfBoundsException
//	{
//		insertElementAt(_e, _idx);
//	}
	
	public synchronized boolean addAll(Collection<? extends E> c)
	{
		boolean res = super.addAll(c);
		if(res)
		{
//			sorted_by_id.addAll(c);
//			sorted_by_title.addAll(c);
			sorted_by_uri.addAll(c);
//			id_sorted = false;
//			title_sorted = false;
			uri_sorted = false;
		}
		return res;
	}

	public synchronized boolean addAll(int index, Collection<? extends E> c) 
		throws ArrayIndexOutOfBoundsException
	{
		boolean res = super.addAll(index, c);
		if(res)
		{
//			sorted_by_id.addAll(c);
//			sorted_by_title.addAll(c);
			sorted_by_uri.addAll(c);
//			id_sorted = false;
//			title_sorted = false;
			uri_sorted = false;
		}
		return res;
	}

//	public synchronized void addElement(E _e) { add(_e); }
	
//	public void clear() { removeAllElements(); }
	
	public synchronized void insertElementAt(E _e, int idx)
	{
		super.insertElementAt(_e, idx);
//		sorted_by_id.add(_e);
//		sorted_by_title.add(_e);
		sorted_by_uri.add(_e);
//		id_sorted = false;
//		title_sorted = false;
		uri_sorted = false;
	}
	
	public synchronized E remove(int idx)
	{
//		E e = this.get(idx);
//		super.remove(e);
		E e = super.remove(idx);
//		sorted_by_id.remove(e);
//		sorted_by_title.remove(e);
		sorted_by_uri.remove(e);
		return e;
	}
	
	public synchronized boolean removeAll(Collection<?> c)
	{
		boolean res = super.removeAll(c);
		if(res)
		{
//			sorted_by_id.removeAll(c);
//			sorted_by_title.removeAll(c);
			sorted_by_uri.removeAll(c);
		}
		return  res;
	}
	
	public synchronized void removeAllElements()
	{
		super.removeAllElements();
//		sorted_by_id.removeAllElements();
//		sorted_by_title.removeAllElements();
		sorted_by_uri.removeAllElements();
	}

	public synchronized void removeElementAt(int _idx)
	{
		E e = this.get(_idx);
		super.removeElementAt(_idx);
//		sorted_by_id.remove(e);
//		sorted_by_title.remove(e);
		sorted_by_uri.remove(e);
	}
	
//	private synchronized void removeRange(int fromIndex, int toIndex)
//	{
//		for(int i=fromIndex; i<=toIndex; i++)
//			remove(i);
//	}

	public synchronized boolean retainAll(Collection<?> c)
	{
		boolean res = super.retainAll(c);
		if(res)
		{
//			sorted_by_id.retainAll(c);
//			sorted_by_title.retainAll(c);
			sorted_by_uri.retainAll(c);
		}
		return res;
	}
	
	public synchronized E set(int index, E element)
	{
		E e = super.set(index, element);
//		sorted_by_id.remove(e);
//		sorted_by_title.remove(e);
		sorted_by_uri.remove(e);
		
//		sorted_by_id.add(element);
//		sorted_by_title.add(element);
		sorted_by_uri.add(element);
		
//		id_sorted = false;
//		title_sorted = false;
		uri_sorted = false;
		
		return e;
	}
	
//	public synchronized void setElementAt(E element, int index)
//	{
//		set(index, element);
//	}
	
	
	
	// ---- new methods
	public boolean addNew(E _e)
	{
		boolean res = false;
		if( (this.findById(_e.getId()) == null) || (this.findByURI(_e.getURI())==null) )
		{
			res = add(_e);
			res = true;
		}
		return res;
	}

	
//	public void sortIds()
//	{
//		if(!id_sorted)
//		{
//			Collections.sort(sorted_by_id, cmpId);
//			id_sorted = true;
//		}
//	}
//	
//	public void sortTitles()
//	{
//		if(!title_sorted)
//		{
//			Collections.sort(sorted_by_title, cmpTitle);
//			title_sorted = true;
//		}
//	}

	public void sortURIs()
	{
		if(!uri_sorted)
		{
			Collections.sort(sorted_by_uri, cmpURI);
			uri_sorted = true;
		}
	}
	
	public void append(Item2Vector<E> appendant)
	{
		addAll(appendant);
	}

	public void addAllNew(Item2Vector<E> appendant)
	{
		Item2Vector<E> appendant__ = new  Item2Vector<E>();
		appendant__.addAll(appendant);
		appendant__.removeAll(this);
		addAll(appendant__);
	}


//	public E findById(int _id)
//	{
//		sortIds();
//		Item target = new Item(_id, "Empty");
//		int idx = Collections.binarySearch(sorted_by_id, (E)target, cmpId);
//		if(idx < 0) return null;
//		else return sorted_by_id.get(idx);
//	}
//
//	public int findIndexById(int _id)
//	{
//		sortIds();
//		Item target = new Item(_id, "Empty");
//		return Collections.binarySearch(sorted_by_id, (E)target, cmpId);
//	}
//	
//	public E findByTitle(String _title)
//	{ 
//		sortTitles();
//		Item target = new Item(0, _title);
//		int idx = Collections.binarySearch(sorted_by_title, (E)target, cmpTitle);
//		if(idx < 0) return null;
//		else return sorted_by_title.get(idx);
//	}
//
//	public int findIndexByTitle(String _title)
//	{ 
//		sortTitles();
//		Item target = new Item(0, _title);
//		return Collections.binarySearch(sorted_by_title, (E)target, cmpTitle);
//	}

	public E findByURI(String _uri)
	{ 
		sortURIs();
		iItem2 target = new Item2(0, "Empty Title", _uri);
		int idx = Collections.binarySearch(sorted_by_uri, target, cmpURI);
		if(idx < 0) return null;
		else return sorted_by_uri.get(idx);
	}

	public int findIndexByURI(String _uri)
	{ 
		sortURIs();
		iItem2 target = new Item2(0, "Empty Title",  _uri);
		return Collections.binarySearch(sorted_by_uri, target, cmpURI);
	}
	
}


//class ItemIdComparator<E extends iItem> implements Comparator<E>
//{
//	public int compare(E i1, E i2)
//	{
//		return (i1.getId() -  i2.getId());
//	}	
//}
//
//class ItemTitleComparator<E extends iItem> implements Comparator<E>
//{
//	public int compare(iItem i1, iItem i2)
//	{
//		return (i1.getTitle().compareTo(i2.getTitle()));
//	}	
//}

class ItemURIComparator<E extends iItem2> implements Comparator<E>, Serializable
{
	private static final long serialVersionUID = -33L;
	
	public int compare(iItem2 i1, iItem2 i2)
	{
		return (i1.getURI().compareTo(i2.getURI()));
	}
	
	/** Writing the object into the stream
	 * @param out - the output stream */
	private void writeObject(ObjectOutputStream out) throws IOException
	{
		out.defaultWriteObject();
	}
	
	/** Reading the object from the stream
	 * @param in - the imput stream */
	private void readObject(ObjectInputStream in)
		throws IOException, ClassNotFoundException
	{
		in.defaultReadObject();
	}
	
}