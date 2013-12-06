/* Disclaimer:
 * 	Java code contained in this file is created as part of educational
 *    research and development. It is intended to be used by researchers of
 *    University of Pittsburgh, School of Information Sciences ONLY.
 *    You assume full responsibility and risk of lossed resulting from compiling
 *    and running this code.
 */
 
/** Interface in intended to wrap all of the entities that can be used as a node
 * in a portal tree: folders, untyped nodes, and nodes with special types.
 * @author Michael V. Yudelson
 */
 
package edu.pitt.sis.paws.core;

import java.io.Serializable;

public interface iItem extends Serializable
{
	public int getId();
	public void setId(int _id);
	public String getTitle();
	public void setTitle(String _title);
	public int getITag();
	public void setITag(int _itag);
	public String getSTag();
	public void setSTag(String _stag);
}