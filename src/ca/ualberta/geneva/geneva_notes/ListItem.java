// This class LISTITEM is a model of an item in the list
package ca.ualberta.geneva.geneva_notes;

public class ListItem {
	protected Boolean isCheck;
	protected String statement;
	
	public ListItem(String statement) {
		this.statement = statement;
		this.isCheck = false;
	}
	
	public String getStatement() {
		return this.statement;
	}
	
	public Boolean getIsCheck() {
		return this.isCheck;
	}
	
	public void setIsCheck(Boolean isChecked) {
		this.isCheck = isChecked;
	}
	
	public void setStatement(String statement) {
		this.statement = statement;
	}
}
