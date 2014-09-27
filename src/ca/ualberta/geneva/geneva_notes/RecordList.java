// This class RECORDLIST acts as the list; it holds an arraylist of listitems and check/uncheck count of the items in the arraylist
package ca.ualberta.geneva.geneva_notes;

import java.util.ArrayList;
import java.util.Vector;

public class RecordList {
	private ArrayList<ListItem> itemArray;
	private int checkCount;
	private int uncheckCount;
	
	public RecordList() {
		itemArray = new ArrayList<ListItem>();
		checkCount = 0;
		uncheckCount = 0;
	}
	
	public void addItem(ListItem item) {
		itemArray.add(item);
		updateIncreaseCount(item.getIsCheck());
	}
	
	public void removeAll(String statement) {
		Vector<String> v = new Vector<String>(1);
		v.add(statement);
		
		for (int index = 0; index < itemArray.size(); index++) {
			if (getStatementAction(index) == null) {
				updateDecreaseCount(getIsCheckAction(index));
				itemArray.set(index, null);
			}
		}
		itemArray.removeAll(v);
	}
	
	public ArrayList<ListItem> getArray() {
		return itemArray;
	}
	
	public int getSize() {
 		assert(itemArray.size() == (checkCount + uncheckCount));
 		return itemArray.size();
	}
	
	// Getters & setters
	public int getCheckCount() {
		return checkCount;
	}
	
	public int getUncheckCount() {
		return uncheckCount;
	}
	
	public String getStatementAction(int position) {
		return itemArray.get(position).getStatement();
	}
	
	public Boolean getIsCheckAction(int position) {
		return itemArray.get(position).getIsCheck();
	}
	
	public void setIsCheckAction(int position, Boolean isChecked) {
		updateDecreaseCount(getIsCheckAction(position));
		itemArray.get(position).setIsCheck(isChecked);
		updateIncreaseCount(getIsCheckAction(position));
	}

	public void setStatementAction(int position, String statement) {
		itemArray.get(position).setStatement(statement);
	}
	

	
	// For keeping track of the # of checked & unchecked items in the list
	private void updateIncreaseCount(Boolean ischeck) {
		if (ischeck == true) {
			checkCount = checkCount + 1;
		}
		else {
			uncheckCount = uncheckCount + 1;
		}
	}
	
	private void updateDecreaseCount(Boolean ischeck) {
		if (ischeck == true) {
			checkCount = checkCount - 1;
		}
		else {
			uncheckCount = uncheckCount - 1;
		}
	}
}
