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

import java.util.*;

public class WeightedItemVector<E extends iItem> extends ItemVector<E>
{
    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = -2767605614048989439L;

    private Vector<Double> weights;

	public WeightedItemVector()  
	{
		super();
		weights = new Vector<Double>();
	}

	public boolean add(E _e) { return add(_e, 1); }
	
	public void add(int _idx, E _e)
	{
		add(_idx,_e, 1);
	}
	
	public synchronized boolean addAll(Collection<? extends E> c)
	{
		boolean res = super.addAll(c);
		for(int i=0; i<c.size(); i++)
			weights.add(new Double(1));
		return res;
	}

	public synchronized boolean addAll(int index, Collection<? extends E> c) 
	{
		boolean res = super.addAll(index, c);
		if(res)
		{
			for(int i=0; i<c.size(); i++)
				weights.add(index, new Double(1));
		}
		return res;
	}

	public synchronized E remove(int idx)
	{
		weights.remove(idx);
		return super.remove(idx);
	}
	
	public synchronized boolean removeAll(Collection<?> c)
	{
		boolean res = false;
		for(int i=0; i<size(); i++)
		{
			if(c.contains(get(i)))
			{
				super.remove(i);
				weights.remove(i);
				res = false;
			}
		}
		return  res;
	}
	
	public synchronized void removeAllElements()
	{
		super.removeAllElements();
		weights.removeAllElements();
	}

	public synchronized void removeElementAt(int _idx)
	{
		super.removeElementAt(_idx);
		weights.removeElementAt(_idx);
	}

	protected synchronized void removeRange(int fromIndex, int toIndex)
	{
		super.removeRange(fromIndex, toIndex);
		for(int i=fromIndex; i<=fromIndex; i++)
			weights.remove(i);
	}

	public synchronized boolean retainAll(Collection<?> c)
	{
		boolean res = false;
		for(int i=0; i<size(); i++)
		{
			if(!c.contains(get(i)))
			{
				super.remove(i);
				weights.remove(i);
				res = false;
			}
		}
		return  res;
	}
	
	public synchronized E set(int index, E element)
	{
		weights.set(index, new Double(1));	
		return super.set(index, element);
	}
	
	// ---- new methods
	public Vector<Double> getWeights() { return weights; }

	public double getWeight(int _idx) { return weights.get(_idx); }
	
	public boolean add(E _e, double weight)
	{
		boolean res = super.add(_e);
		if(res) { weights.add(weight); }
		return res;
	}

	public void add(int _idx, E _e, double weight)
	{
		super.add(_idx, _e);
		weights.add(_idx, weight);
	}

	public synchronized void addElement(E _e, double weight) { add(_e, weight); }

	public synchronized void insertElementAt(E _e, int idx, double weight)
	{
		add(idx, _e, weight);
	}

	public synchronized boolean addAll(WeightedItemVector<E> c)
	{
		boolean res = super.addAll(c);
		if(res)
			res = weights.addAll(c.getWeights());
		return res;
	}

	public synchronized boolean addAll(int index, WeightedItemVector<E> c) 
	{
		boolean res = super.addAll(index, c);
		if(res)
			res = weights.addAll(index, c.getWeights());
		return res;
	}
	
	public synchronized E set(int index, E element, double weight)
	{
		weights.set(index, weight);	
		return super.set(index, element);
	}

	public boolean addNew(E _e)
	{
		boolean res = false;
		if( this.findById(_e.getId()) == null )
		{
			res = add(_e, 1);
		}
		return res;
	}

	public boolean addNew(E _e, double weight)
	{
		boolean res = false;
		if( this.findById(_e.getId()) == null )
		{
			res = add(_e, weight);
		}
		return res;
	}
	
	public void addAllNew(ItemVector<E> appendant)
	{
		ItemVector<E> appendant__ = new  ItemVector<E>();
		appendant__.addAll(appendant);
		appendant__.removeAll(this);
		addAll(appendant__);
	}

	public void addAllNew(WeightedItemVector<E> appendant)
	{
		WeightedItemVector<E> appendant__ = new  WeightedItemVector<E>();
		appendant__.addAll(appendant);
		appendant__.removeAll(this);
		addAll(appendant__);
	}
}
