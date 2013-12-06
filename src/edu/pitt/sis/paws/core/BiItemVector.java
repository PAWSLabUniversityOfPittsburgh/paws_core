package edu.pitt.sis.paws.core;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class BiItemVector<E extends iItem, F extends iItem> extends Vector<iBiItem<E, F>>
{
    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = -2767605614048989439L;

	private boolean id1_sorted;
	private boolean title1_sorted;

	private boolean id2_sorted;
	private boolean title2_sorted;
	
	private Vector<iBiItem<E, F>> sorted_by_id1;
	private Vector<iBiItem<E, F>> sorted_by_title1;

	private Vector<iBiItem<E, F>> sorted_by_id2;
	private Vector<iBiItem<E, F>> sorted_by_title2;
	
	private Comparator<iBiItem<E, F>> cmpId1;
	private Comparator<iBiItem<E, F>> cmpTitle1;

	private Comparator<iBiItem<E, F>> cmpId2;
	private Comparator<iBiItem<E, F>> cmpTitle2;

	public BiItemVector()
	{
		super(0, 0);
		
		id1_sorted = false;
		title1_sorted = false;
		
		id2_sorted = false;
		title2_sorted = false;

		sorted_by_id1 = new Vector<iBiItem<E, F>>(0, 0);
		sorted_by_title1 = new Vector<iBiItem<E, F>>(0, 0);
		
		sorted_by_id2 = new Vector<iBiItem<E, F>>(0, 0);
		sorted_by_title2 = new Vector<iBiItem<E, F>>(0, 0);
		
		cmpId1 = new BiItem1IdComparator<iBiItem<E, F>>();
		cmpTitle1 = new BiItem1TitleComparator<iBiItem<E, F>>();

		cmpId2 = new BiItem2IdComparator<iBiItem<E, F>>();
		cmpTitle2 = new BiItem2TitleComparator<iBiItem<E, F>>();
	}
	

	public boolean add(iBiItem<E, F> _e)
	{
		boolean res = super.add(_e);
		if(res)
		{
			sorted_by_id1.add(_e);
			sorted_by_title1.add(_e);
			id1_sorted = false;
			title1_sorted = false;

			sorted_by_id2.add(_e);
			sorted_by_title2.add(_e);
			id2_sorted = false;
			title2_sorted = false;
		}
		return res;
	}
	
	public void add(int _idx, iBiItem<E, F> _e) throws ArrayIndexOutOfBoundsException
	{
		super.add(_idx,_e);
		
		sorted_by_id1.add(_e);
		sorted_by_title1.add(_e);
		id1_sorted = false;
		title1_sorted = false;

		sorted_by_id2.add(_e);
		sorted_by_title2.add(_e);
		id2_sorted = false;
		title2_sorted = false;
	}
	
	public synchronized boolean addAll(Collection<? extends iBiItem<E, F>> c)
	{
		boolean res = super.addAll(c);
		if(res)
		{
			sorted_by_id1.addAll(c);
			sorted_by_title1.addAll(c);
			id1_sorted = false;
			title1_sorted = false;

			sorted_by_id2.addAll(c);
			sorted_by_title2.addAll(c);
			id2_sorted = false;
			title2_sorted = false;
		}
		return res;
	}
	
	public synchronized boolean addAll(int index, Collection<? extends iBiItem<E, F>> c) 
		throws ArrayIndexOutOfBoundsException
	{
		boolean res = super.addAll(index, c);
		if(res)
		{
			sorted_by_id1.addAll(c);
			sorted_by_title1.addAll(c);
			id1_sorted = false;
			title1_sorted = false;

			sorted_by_id2.addAll(c);
			sorted_by_title2.addAll(c);
			id2_sorted = false;
			title2_sorted = false;
		}
		return res;
	}

	public synchronized void addElement(iBiItem<E, F> _e) { add(_e); }
	
	public void clear() { removeAllElements(); }
	
	public Object clone()
	{	
		Object copy = (Object)super.clone();
		return copy;
	}

	public synchronized void insertElementAt(iBiItem<E, F> _e, int idx) { add(idx, _e); }
	
	public synchronized iBiItem<E, F> remove(int idx)
	{
		iBiItem<E, F> e = this.get(idx);
		this.remove(e);
		
		sorted_by_id1.remove(e);
		sorted_by_title1.remove(e);
		
		sorted_by_id2.remove(e);
		sorted_by_title2.remove(e);
		
		return e;
	}
	
	public synchronized boolean removeAll(Collection<?> c)
	{
		boolean res = super.removeAll(c);
		if(res)
		{
			sorted_by_id1.removeAll(c);
			sorted_by_title1.removeAll(c);

			sorted_by_id2.removeAll(c);
			sorted_by_title2.removeAll(c);
		}
		return  res;
	}
	
	public synchronized void removeAllElements()
	{
		super.removeAllElements();
		
		sorted_by_id1.removeAllElements();
		sorted_by_title1.removeAllElements();

		sorted_by_id2.removeAllElements();
		sorted_by_title2.removeAllElements();
	}

	public synchronized void removeElementAt(int _idx)
	{
		iBiItem<E, F> e = this.get(_idx);
		super.removeElementAt(_idx);

		sorted_by_id1.remove(e);
		sorted_by_title1.remove(e);
	
		sorted_by_id2.remove(e);
		sorted_by_title2.remove(e);
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
			sorted_by_id1.retainAll(c);
			sorted_by_title1.retainAll(c);

			sorted_by_id2.retainAll(c);
			sorted_by_title2.retainAll(c);
		}
		return res;
	}
	
	public synchronized iBiItem<E, F> set(int index, iBiItem<E, F> element)
	{
		iBiItem<E, F> e = super.set(index, element);

		sorted_by_id1.remove(e);
		sorted_by_title1.remove(e);
		sorted_by_id1.add(element);
		sorted_by_title1.add(element);
		id1_sorted = false;
		title1_sorted = false;

		sorted_by_id2.remove(e);
		sorted_by_title2.remove(e);
		sorted_by_id2.add(element);
		sorted_by_title2.add(element);
		id2_sorted = false;
		title2_sorted = false;
		
		return e;
	}
	
	public synchronized void setElementAt(iBiItem<E, F> element, int index)
	{
		set(index, element);
	}
	
	
	
	// ---- new methods
	public boolean addNew(iBiItem<E, F> _e)
	{
		boolean res = false;
		if( (_e.getItem1() != null) && (_e.getItem2() != null) &&
				(findById1(_e.getItem1().getId()) == null) &&
				(findById2(_e.getItem2().getId()) == null) )
		{
			res = add(_e);
		}
		return res;
	}

	
	public void sortIds1()
	{
		if(!id1_sorted)
		{
			Collections.sort(sorted_by_id1, cmpId1);
			id1_sorted = true;
		}
//		System.out.println("sorting id 1");
	}

	public void sortIds2()
	{
		if(!id2_sorted)
		{
			Collections.sort(sorted_by_id2, cmpId2);
			id2_sorted = true;
		}
//		System.out.println("sorting id 2");
	}

	
	public void sortTitles1()
	{
		if(!title1_sorted)
		{
			Collections.sort(sorted_by_title1, cmpTitle1);
			title1_sorted = true;
		}
//		System.out.println("sorting title 1");
	}

	public void sortTitles2()
	{
		if(!title2_sorted)
		{
			Collections.sort(sorted_by_title2, cmpTitle2);
			title2_sorted = true;
		}
//		System.out.println("sorting title 2");
	}


//	public void append(ItemVector<E> appendant)
//	{
//		addAll(appendant);
//	}

	public void addAllNew(BiItemVector<E, F> appendant) 
	{
		BiItemVector<E, F> appendant__ = new BiItemVector<E, F>();
		appendant__.addAll(appendant);
		appendant__.removeAll(this);
		addAll(appendant__);
	}


	public iBiItem<E, F> findById1(int _id)
	{
		sortIds1();
		iItem target1 = new Item(_id, "Empty");
		iItem target2 = new Item(_id, "Empty");
		BiItem<E, F> bitarget = new BiItem<E, F>((E)target1, (F)target2);
		
		int idx = Collections.binarySearch(sorted_by_id1, (iBiItem<E, F>)bitarget, cmpId1);
		if(idx < 0) return null;
		else return sorted_by_id1.get(idx);
	}

	public iBiItem<E, F> findById2(int _id)
	{
		sortIds2();
		iItem target1 = new Item(_id, "Empty");
		iItem target2 = new Item(_id, "Empty");
		BiItem<E, F> bitarget = new BiItem<E, F>((E)target1, (F)target2);
		
		int idx = Collections.binarySearch(sorted_by_id2, (iBiItem<E, F>)bitarget, cmpId2);
		if(idx < 0) return null;
		else return sorted_by_id2.get(idx);
	}
	
	public int findIndexById1(int _id)
	{
		sortIds1();
		iItem target1 = new Item(_id, "Empty");
		iItem target2 = new Item(_id, "Empty");
		BiItem<E, F> bitarget = new BiItem<E, F>((E)target1, (F)target2);

		return Collections.binarySearch(sorted_by_id1, (iBiItem<E, F>)bitarget, cmpId1);
	}

	public int findIndexById2(int _id)
	{
		sortIds2();
		iItem target1 = new Item(_id, "Empty");
		iItem target2 = new Item(_id, "Empty");
		BiItem<E, F> bitarget = new BiItem<E, F>((E)target1, (F)target2);

		return Collections.binarySearch(sorted_by_id2, (iBiItem<E, F>)bitarget, cmpId2);
	}

	public iBiItem<E, F> findByTitle1(String _title)
	{ 
		sortTitles1();

		iItem target1 = new Item(0, _title);
		iItem target2 = new Item(0, _title);
		BiItem<E, F> bitarget = new BiItem<E, F>((E)target1, (F)target2);

		int idx = Collections.binarySearch(sorted_by_title1, (iBiItem<E, F>)bitarget, cmpTitle1);
		if(idx < 0) return null;
		else return sorted_by_title1.get(idx);
	}

	public iBiItem<E, F> findByTitle2(String _title)
	{ 
		sortTitles2();

		iItem target1 = new Item(0, _title);
		iItem target2 = new Item(0, _title);
		BiItem<E, F> bitarget = new BiItem<E, F>((E)target1, (F)target2);

		int idx = Collections.binarySearch(sorted_by_title2, (iBiItem<E, F>)bitarget, cmpTitle2);
		if(idx < 0) return null;
		else return sorted_by_title2.get(idx);
	}

	public int findIndexByTitle1(String _title)
	{ 
		sortTitles1();

		iItem target1 = new Item(0, _title);
		iItem target2 = new Item(0, _title);
		BiItem<E, F> bitarget = new BiItem<E, F>((E)target1, (F)target2);

		return Collections.binarySearch(sorted_by_title1, (BiItem<E, F>)bitarget, cmpTitle1);
	}	 

	public int findIndexByTitle2(String _title)
	{ 
		sortTitles2();

		iItem target1 = new Item(0, _title);
		iItem target2 = new Item(0, _title);
		BiItem<E, F> bitarget = new BiItem<E, F>((E)target1, (F)target2);

		return Collections.binarySearch(sorted_by_title2, (BiItem<E, F>)bitarget, cmpTitle2);
	}	 
	
	public static void main(String[] args)
	{
		BiItemVector<Item, Item> list = new BiItemVector<Item, Item>();
		for(int i=99; i>-1; i--)
		{
			Item a = new Item(100 + i, "A " + (100 + i));
			Item b = new Item(200 + i, "B " + (200 + i));
			BiItem<Item, Item> ab = new BiItem<Item, Item>(a, b);
			list.add(ab);
		}
		iBiItem<Item, Item> ab_hat = list.findById1(125);
		System.out.println(ab_hat);
		ab_hat = list.findByTitle2("B 278");
		System.out.println(ab_hat);
		ab_hat = list.findByTitle1("A 101");
		System.out.println(ab_hat);
		ab_hat = list.findById2(12);
		System.out.println(ab_hat);
		System.out.println(list.size());
	}

}

// Comparators
class BiItem1IdComparator<BI extends iBiItem<?,?>> implements Comparator<BI>
{
	public int compare(BI bi1, BI bi2)
	{
		return ( bi1.getItem1().getId() -  bi2.getItem1().getId() );
	}	
}

class BiItem1TitleComparator<BI extends iBiItem<?,?>> implements Comparator<BI>
{
	public int compare(BI bi1, BI bi2)
	{
		return ( bi1.getItem1().getTitle().compareTo(bi2.getItem1().getTitle()));
	}	
}

class BiItem2IdComparator<BI extends iBiItem<?,?>> implements Comparator<BI>
{
	public int compare(BI bi1, BI bi2)
	{
		return ( bi1.getItem2().getId() -  bi2.getItem2().getId() );
	}	
}

class BiItem2TitleComparator<BI extends iBiItem<?,?>> implements Comparator<BI>
{
	public int compare(BI bi1, BI bi2)
	{
		return ( bi1.getItem2().getTitle().compareTo(bi2.getItem2().getTitle()));
	}	
}
