package edu.pitt.sis.paws.core;

public class HierarchicalItem2 extends Item2 implements iHierarchicalItem2
{
	static final long serialVersionUID = 5L;
	
	private Item2Vector<iHierarchicalItem2> subordinates;
	
	public Item2Vector<iHierarchicalItem2> getSubs() { return subordinates; }
	
	public HierarchicalItem2()
	{
		super();
		subordinates = new Item2Vector<iHierarchicalItem2>();
	}

	public HierarchicalItem2(int _id, String _title)
	{
		super();
		subordinates = new Item2Vector<iHierarchicalItem2>();
	}

	public HierarchicalItem2(int _id, String _title, String _uri)
	{
		super(_id, _title, _uri);
		subordinates = new Item2Vector<iHierarchicalItem2>();
	}

	public String toString()
	{
		return super.toString() + " no_subs=" + subordinates.size();
	}

}
