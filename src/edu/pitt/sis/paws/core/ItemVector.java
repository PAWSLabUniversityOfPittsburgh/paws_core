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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class ItemVector<E extends iItem> extends Vector<E>
{
    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = -2767605614048989439L;
	
	private boolean id_sorted;
	private boolean title_sorted;
	private Vector<E> sorted_by_id;
	private Vector<E> sorted_by_title;
	private Comparator<iItem> cmpId;
	private Comparator<iItem> cmpTitle;
	
	public ItemVector()
	{
		super(0, 0);
		id_sorted = false;
		title_sorted = false;
		sorted_by_id = new Vector<E>(0, 0);
		sorted_by_title = new Vector<E>(0, 0);
		cmpId = new ItemIdComparator<iItem>();
		cmpTitle = new ItemTitleComparator<iItem>();
	}

	public boolean add(E _e)
	{
		boolean res = super.add(_e);
		if(res)
		{
			sorted_by_id.add(_e);
			sorted_by_title.add(_e);
			id_sorted = false;
			title_sorted = false;
		}
		return res;
	}
	
	public void add(int _idx, E _e) throws ArrayIndexOutOfBoundsException
	{
		insertElementAt(_e, _idx);
	}
	
	public synchronized boolean addAll(Collection<? extends E> c)
	{
		boolean res = super.addAll(c);
		if(res)
		{
			sorted_by_id.addAll(c);
			sorted_by_title.addAll(c);
			id_sorted = false;
			title_sorted = false;
		}
		return res;
	}

	public synchronized boolean addAll(int index, Collection<? extends E> c) 
		throws ArrayIndexOutOfBoundsException
	{
		boolean res = super.addAll(index, c);
		if(res)
		{
			sorted_by_id.addAll(c);
			sorted_by_title.addAll(c);
			id_sorted = false;
			title_sorted = false;
		}
		return res;
	}

	public synchronized void addElement(E _e) { add(_e); }
	
	public void clear() { removeAllElements(); }
	
	public synchronized void insertElementAt(E _e, int idx)
	{
		super.insertElementAt(_e, idx);
		sorted_by_id.add(_e);
		sorted_by_title.add(_e);
		id_sorted = false;
		title_sorted = false;
	}
	
	public synchronized E remove(int idx)
	{
//		E e = this.get(idx);
//		super.remove(e);
		E e = super.remove(idx);
		
		sorted_by_id.remove(e);
		sorted_by_title.remove(e);
		return e;
	}
	
	public synchronized boolean removeAll(Collection<?> c)
	{
		boolean res = super.removeAll(c);
		if(res)
		{
			sorted_by_id.removeAll(c);
			sorted_by_title.removeAll(c);
		}
		return  res;
	}
	
	public synchronized void removeAllElements()
	{
		super.removeAllElements();
		sorted_by_id.removeAllElements();
		sorted_by_title.removeAllElements();
	}

	public synchronized void removeElementAt(int _idx)
	{
		E e = this.get(_idx);
		super.removeElementAt(_idx);
		sorted_by_id.remove(e);
		sorted_by_title.remove(e);
	}
	
	protected synchronized void removeRange(int fromIndex, int toIndex)
	{
		for(int i=fromIndex; i<=toIndex; i++)
			remove(i);
	}

	public synchronized boolean retainAll(Collection<?> c)
	{
		boolean res = super.retainAll(c);
		if(res)
		{
			sorted_by_id.retainAll(c);
			sorted_by_title.retainAll(c);
		}
		return res;
	}
	
	public synchronized E set(int index, E element)
	{
		E e = super.set(index, element);
		sorted_by_id.remove(e);
		sorted_by_title.remove(e);
		sorted_by_id.add(element);
		sorted_by_title.add(element);
		id_sorted = false;
		title_sorted = false;
		return e;
	}
	
	public synchronized void setElementAt(E element, int index)
	{
		set(index, element);
	}
	
	
	
	// ---- new methods
	public boolean addNew(E _e)
	{
		boolean res = false;
		if( this.findById(_e.getId()) == null )
		{
			res = add(_e);
		}
		return res;
	}

	
	public void sortIds()
	{
		if(!id_sorted)
		{
			Collections.sort(sorted_by_id, cmpId);
			id_sorted = true;
		}
	}
	
	public void sortTitles()
	{
		if(!title_sorted)
		{
			Collections.sort(sorted_by_title, cmpTitle);
			title_sorted = true;
		}
	}

//	public void append(ItemVector<E> appendant)
//	{
//		addAll(appendant);
//	}

	public void addAllNew(ItemVector<E> appendant)
	{
		ItemVector<E> appendant__ = new  ItemVector<E>();
		appendant__.addAll(appendant);
		appendant__.removeAll(this);
		addAll(appendant__);
	}


	public E findById(int _id)
	{
		sortIds();
		iItem target = new Item(_id, "Empty");
		int idx = Collections.binarySearch(sorted_by_id, target, cmpId);
		if(idx < 0) return null;
		else return sorted_by_id.get(idx);
	}

	public int findIndexById(int _id)
	{
		sortIds();
		iItem target = new Item(_id, "Empty");
		return Collections.binarySearch(sorted_by_id, target, cmpId);
	}

	public E findByTitle(String _title)
	{ 
		sortTitles();
		iItem target = new Item(0, _title);
		int idx = Collections.binarySearch(sorted_by_title, target, cmpTitle);
		if(idx < 0) return null;
		else return sorted_by_title.get(idx);
	}

	public int findIndexByTitle(String _title)
	{ 
		sortTitles();
		iItem target = new Item(0, _title);
		return Collections.binarySearch(sorted_by_title, target, cmpTitle);
	}
}


class ItemIdComparator<E extends iItem> implements Comparator<iItem> ,Serializable
//class ItemIdComparator<E extends iItem> implements Comparator<iItem>, Serializable
{
	private static final long serialVersionUID = -11L;
	
	public int compare(iItem i1, iItem i2)
	{
//		System.out.println("i1 " + i1 + "   i2 " + i2);
		return (i1.getId() -  i2.getId());
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

class ItemTitleComparator<E extends iItem> implements Comparator<iItem>, Serializable
{
    private static final long serialVersionUID = -22L;

    public int compare(iItem i1, iItem i2)
	{
		return (i1.getTitle().compareTo(i2.getTitle()));
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
