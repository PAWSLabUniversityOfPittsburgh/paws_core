package edu.pitt.sis.paws.core;

public interface iBiItem<E extends iItem, F extends iItem>
{
	public E getItem1();
	public F getItem2();
	public void setItem1(E _item1);
	public void setItem2(F _item2);
}
