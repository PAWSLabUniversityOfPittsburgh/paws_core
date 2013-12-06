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

public class OrderedWeightedItem2Vector<E extends iItem2> extends WeightedItem2Vector<E>
{
    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = -2767605614048989439L;
	
	public OrderedWeightedItem2Vector()
	{
		super();
	}
	
	public int getIndexOf(E e) { return this.findIndexById(e.getId()); }
	
	public boolean swap(int _idx1, int _idx2)
	{
		boolean res = false;
		if(	(this.size()>1) && // there are at least 2 elements
			((_idx1>=0) && (_idx1<this.size())) && // index 1 is good
			((_idx2>=0) && (_idx2<this.size())) && // index 2 is good
			(_idx1 != _idx2) // indices 1 & 2 are different
		  )
		{
			E e1 = this.get(_idx1);
			E e2 = this.get(_idx2);
			double w1 = this.getWeight(_idx1);
			double w2 = this.getWeight(_idx2);
			this.set(_idx1, e2, w2);
			this.set(_idx2, e1, w1);
			res = true;
		}
		return res;
	}

	public boolean swap(E _e1, E _e2)
	{
		int _idx1 = getIndexOf(_e1);
		int _idx2 = getIndexOf(_e2);
		if((_idx1 < 0) || (_idx2 < 0)) return false;
		else return swap(_idx1, _idx2);
	}
	
//	public void reorder(Vector<Integer> indices)
//	{
//		OrderedWeightedItem2Vector<E> copy = new OrderedWeightedItem2Vector<E>();
//		copy.addAll(this);
//
//		for(int i=0; i<indices.size(); i++)
//		{
////			E reord_item = copy.findById(ids.get(i).intValue());
//			E reord_item = copy.get(i);
//			int reord_idx = indices.get(i).intValue();
//			this.set(reord_idx, reord_item, copy.getWeight(reord_idx));
//		}
//	}
	
	public void reorder(Vector<Integer> indices)
	{
		OrderedWeightedItem2Vector<E> copy = new OrderedWeightedItem2Vector<E>();
		copy.addAll(this);

		for(int i=0; i<indices.size(); i++)
		{
//			E reord_item = copy.findById(ids.get(i).intValue());
			E reord_item = copy.get(i);
			int reord_idx = indices.get(i).intValue();
			this.set(reord_idx, reord_item, copy.getWeight(reord_idx));
		}
					
			
	}

	public void reorder(Vector<Integer> indices, Vector<Integer> ids)
	{
		OrderedWeightedItemVector<E> copy = new OrderedWeightedItemVector<E>();
		copy.addAll(this);

		for(int i=0; i<indices.size(); i++)
		{
			E reord_item = copy.findById(ids.get(i).intValue());
//			E reord_item = copy.get(i);
			int reord_idx = indices.get(i).intValue();
			this.set(reord_idx, reord_item, copy.getWeight(reord_idx));
		}
					
			
	}
	
}
