package ca.ualberta.geneva.geneva_notes;

public class TodoController extends ListController {
	
	// Sets up the proper listHeader
	public TodoController() {
		super();
		listHeader = "TODO items :" + '\n';
	}
	
	// Adds an item directly to the TODO list
	public void addItem(ListItem item) {
		itemList.addItem(item);
	}
}
