/* This class MAINCONTROLLER delegates actions that are called upon by the activity & its fragments */
package ca.ualberta.geneva.geneva_notes;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.http.protocol.HTTP;

import android.content.Intent;
import android.os.Bundle;

public class MainController {
	
	static private TodoController todo = null;
	static private ArchiveController archive = null;
	static private ArrayList<Integer> helper_array = new ArrayList<Integer>();
	
	private static final String EMAIL_ADDRESS = "email_address";
	private static final String EMAIL_SUBJECT = "email_subject";
	
	// *Start* Initializing todo & archive through ListManager, and checking if it is null (and initalizing if so) before returning.
	// Inspired by https://github.com/abramhindle/student-picker/blob/master/src/ca/softwareprocess/studentpicker/StudentListController.java 2014-09-19 // Modified
	public MainController() {
		getTodoController();
		getArchiveController();
	}
	
	static public TodoController getTodoController() {
		if (todo == null) {
			todo = new TodoController();
			todo.setList(ListManager.getManager().loadList("todo"));
		}
		return todo;
	}
	
	static public ArchiveController getArchiveController() {
		if (archive == null) {
			archive = new ArchiveController();
			archive.setList(ListManager.getManager().loadList("archive"));
		}
		return archive;
	}
	// *End* https://github.com/abramhindle/student-picker/blob/master/src/ca/softwareprocess/studentpicker/StudentListController.java //
	
    
	//  For setting up the adapters in TodoFragment & ArchiveFragment
	static public RecordList getTodoList() {
		return getTodoController().getList();
	}
	
	static public RecordList getArchiveList() {
		return getArchiveController().getList();
	}
	//
	
	public void addTodoItem(ListItem item) {
		getTodoController().addItem(item);
	}
	
	public void removeTodoItems(ArrayList<Integer> positionList) {
		getTodoController().removeItems(positionList);
	}
	
	public void removeArchiveItems(ArrayList<Integer> positionList) {
		getArchiveController().removeItems(positionList);
	}
	
	public void archiveItems(ArrayList<Integer> positionList) {
		RecordList items = getTodoController().archiveActionRemove(positionList);
		getArchiveController().archiveActionAdd(items);
	}
	
	public void unarchiveItems(ArrayList<Integer> positionList) {
		RecordList items = getArchiveController().archiveActionRemove(positionList);
		getTodoController().archiveActionAdd(items);
	}
	
	
	// Getters for Todo & Archive check/uncheck counts
	public int getTodoCheckCount() {
		return getTodoController().getCheckCount();
	}
	
	public int getTodoUncheckCount() {
		return getTodoController().getUncheckCount();
	}
	
	public int getArchiveCheckCount() {
		return getArchiveController().getCheckCount();
	}
	
	public int getArchiveUncheckCount() {
		return getArchiveController().getUncheckCount();
	}
	//
	
	public void setHelperArray(ArrayList<Integer> helper_array1) {
		Collections.sort(helper_array1);
		for (int i = 0; i < helper_array1.size(); i++) {
			helper_array.add(helper_array1.get(i));
		}
	}
	
	public Intent emailAction(Bundle args) {
		String content = emailContentTriage(args);
		helper_array.clear();

		Intent intent = new Intent(Intent.ACTION_SEND);
		
		// This line was taken from Stackoverflow - 2014-09-26
		// Original Question : http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application
		// Author of the answer : fixedd - http://stackoverflow.com/users/76835/fixedd
		intent.setType("message/rfc822");
		
		intent.putExtra(Intent.EXTRA_EMAIL, new String[] {args.getString(EMAIL_ADDRESS)});
		intent.putExtra(Intent.EXTRA_SUBJECT, args.getString(EMAIL_SUBJECT));
		intent.putExtra(Intent.EXTRA_TEXT, content);
		
		return intent;
	}
    
	// Determines which information to get dependent upon which menu item was clicked
	private String emailContentTriage(Bundle args) {
		String content = null;
		int menu_item_id = args.getInt("menu_item_id");
		if (menu_item_id == R.id.popupTodo) {
			content = getTodoController().getEmailContent();
		}
		else if (menu_item_id == R.id.popupAll) {
			content = getTodoController().getEmailContent() +  '\n' + getArchiveController().getEmailContent();
		}
		else if (menu_item_id == R.id.action_email_todo) {
			content = getTodoController().getSelectedEmailContent(helper_array);
		}
		else if (menu_item_id == R.id.action_email_archive) {
			content = getArchiveController().getSelectedEmailContent(helper_array);
		}
		
		return content;
	}
}
