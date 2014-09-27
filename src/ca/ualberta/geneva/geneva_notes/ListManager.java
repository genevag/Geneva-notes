package ca.ualberta.geneva.geneva_notes;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ListManager {
	
	private Context context;
	private static String size = "size";
	private static String checkCount = "checkCount";
	private static String uncheckCount = "uncheckCount";
	
	private static ListManager manager = null;
	
	// *Start* Taken from: https://github.com/abramhindle/student-picker/blob/master/src/ca/softwareprocess/studentpicker/StudentListManager.java 2014-09-19
	/* Modified.
	Copyright (C) 2014 Geneva Giang geneva@ualberta.ca
	Student Picker: Randomly pick students to answer questions
	Copyright (C) 2014 Abram Hindle abram.hindle@softwareprocess.ca
	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.
	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
	GNU General Public License for more details.
	You should have received a copy of the GNU General Public License
	along with this program. If not, see <http://www.gnu.org/licenses/>.
	*/
	public ListManager(Context context) {
		this.context = context;
	}
	
	public static void initializeManager(Context context) {
		if (manager == null) {
			manager = new ListManager(context);
		}
	}
	
	public static ListManager getManager() {
		if (manager == null) {
			throw new RuntimeException ("ListManager not yet initialized");
		}
		else {
			return manager;
		}
	}
	// *End* https://github.com/abramhindle/student-picker/blob/master/src/ca/softwareprocess/studentpicker/StudentListManager.java
	
	public void saveList(String listName) {
		String fileName = determineFileName(listName);
		SharedPreferences prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		
		RecordList list = determineList(listName);
		
		editor.putInt(size, list.getSize());
		
		editor.putInt(checkCount, list.getCheckCount());
		editor.putInt(uncheckCount, list.getUncheckCount());
		
		// saving each item as a string with structure "itemPOSITION" as the key
		// and the content of each item with structure "CHECKSTATUS|STATEMENT"
		for (int i = 0; i < list.getSize(); i++) {
			editor.putString("item" + Integer.toString(i), Boolean.toString(list.getIsCheckAction(i)) + "|" + list.getStatementAction(i));
		}
		
		editor.commit();
	}
	
	public RecordList loadList(String listName) {
		String fileName = determineFileName(listName);
		SharedPreferences prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		
		RecordList list = new RecordList();
		
		int listSize = prefs.getInt(size, 0);
		
		// populating list
		for (int i = 0; i < listSize; i++) {
			// extracting the data
			String data = prefs.getString("item" + Integer.toString(i), "");
			String[] splitData =  data.split("\\|");
			Boolean isCheck = determineBoolean(splitData[0]);
			
			// adding it to the list as a ListItem
			ListItem item = new ListItem(splitData[1]);
			item.setIsCheck(isCheck);
			list.addItem(item);
		}
		
		assert(prefs.getInt(checkCount,0) == list.getCheckCount());
		assert(prefs.getInt(uncheckCount,0) == list.getUncheckCount());

		return list;
	}
	
	private RecordList determineList(String listName) {
		if (listName == "todo") {
			return MainController.getTodoList();
		}
		else {
			return MainController.getArchiveList();
		}
	}
	
	private String determineFileName(String listName) {
		if (listName == "todo") {
			return "Todo_file";
		}
		else {
			return "Archive_file";
		}
	}

	private Boolean determineBoolean(String bool) {
		return Boolean.valueOf(bool);
	}
	
}
