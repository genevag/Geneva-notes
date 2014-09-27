/* This class LISTCONTROLLER carries out the commands of REMOVING ITEMS, RETRIEVING THE CONTENT TO EMAIL, ARCHIVING/UNARCHING ITEMS
 * It's an ABSTRACT CLASS that needs to be instantiated from a subclass which sets up the listHeader */
package ca.ualberta.geneva.geneva_notes;

import java.util.ArrayList;
import java.util.Collections;

public abstract class ListController {
	protected RecordList itemList;
	protected String listHeader;
	
	public ListController() {
		getList();
	}
	
	public RecordList getList() {
		if (itemList == null) {
			itemList = ListManager.getManager().loadList("todo");
		}
		return itemList;
	}

	// For set-up from initializing after loadList
	protected void setList(RecordList list) {
		itemList = new RecordList();
		ListItem item = null;
		for (int i = 0; i < list.getSize(); i++) {
			item = new ListItem(list.getStatementAction(i));
			item.setIsCheck(list.getIsCheckAction(i));
			itemList.addItem(item);
		}
	}
	
	public String getListHeader() {
		return listHeader;
	}
	
	public void removeItems(ArrayList<Integer> positionList) {
		// Precaution
		if (positionList.isEmpty()) {
			return;
		}
		
		// Remove the corresponding items in the positionList from the itemList(RecordList)
		Collections.sort(positionList);
		for (int i : positionList) {
			getList().setStatementAction(i, null);
		}
		getList().removeAll(null);
	}
	
	// Part 1 of archiving & unarchiving items.
	// Removes corresponding items (given by positionList) from the list and returns a list(RecordList) of those items
	protected RecordList archiveActionRemove(ArrayList<Integer> positionList) {
		Collections.sort(positionList);

		RecordList list = new RecordList();
		for (int i : positionList) {
			ListItem item = new ListItem(getList().getStatementAction(i));
			item.setIsCheck(getList().getIsCheckAction(i));
			list.addItem(item);
			getList().setStatementAction(i, null);
		}
		
		getList().removeAll(null);
		return list;
		
	}
	
	// Part 2 of archiving & unarchiving items.
	// Takes a list(RecordList) of the items to be archived/unarchived and inserts it into the proper list
	protected void archiveActionAdd(RecordList list) {
		for(int i = 0; i < list.getSize(); i++) {
			ListItem item = new ListItem(list.getStatementAction(i));
			item.setIsCheck(list.getIsCheckAction(i));
			getList().addItem(item);
		}
	}
	
	public int getCheckCount() {
		return getList().getCheckCount();
	}
	
	public int getUncheckCount() {
		return getList().getUncheckCount();
	}
	
	public String getEmailContent() {
		return getListHeader() + listToText(getList());
	}
	
	public String getSelectedEmailContent(ArrayList<Integer> positionArray) {
		RecordList list = getSelectedList(positionArray);
		return getListHeader() + listToText(list);
	}
	
	// Creates & returns a new list(RecordList) of the selected items
	private RecordList getSelectedList(ArrayList<Integer> positionArray) {
		System.out.println(positionArray.size());
		RecordList returnList = new RecordList();
		ListItem item = null;
		for (int i : positionArray) {
			item = new ListItem(getList().getStatementAction(i));
			item.setIsCheck(getList().getIsCheckAction(i));
			returnList.addItem(item);
		}
		return returnList;
	}
	
	private String listToText(RecordList list) {
		String text = "";
		String check = null;
		for (int i = 0; i < list.getSize(); i++) {
			check = convertCheckStatus(list.getIsCheckAction(i));
			text = text + check.concat(list.getStatementAction(i)) + '\n';
		}
		
		if (text == "") {
			text = "None!";
		}
		
		return text;
	}
	
	private String convertCheckStatus(Boolean check) {
		if (check == true) {
			return "[X] ";
		}
		else {
			return "[ ] ";
		}
	}
}
