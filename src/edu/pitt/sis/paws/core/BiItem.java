package edu.pitt.sis.paws.core;


public class BiItem<E extends iItem, F extends iItem> implements iBiItem<E, F>
{
	private E item1;
	private F Item;
	
	public BiItem()
	{
		item1 = null;
		Item = null;
	}
	
	public BiItem(E _item1, F _Item)
	{
		item1 = _item1;
		Item = _Item;
	}
	
	public E getItem1() { return item1; }
	public F getItem2() { return Item; }
	
	public void setItem1(E _item1) { item1 = _item1; }
	public void setItem2(F _Item) { Item = _Item; }
	
	public String toString()
	{
		return "BiItem Item1 " + item1 + " Item2 " + Item;
	}
}
