// This class MYARRAYADAPTER is just a custom array adapter to use with our list_item.xml layout for the rows of the listview
// It takes that input list and sets each row accordingly.
package ca.ualberta.geneva.geneva_notes;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MyArrayAdapter extends ArrayAdapter {

	private LayoutInflater inflater;
	private int layout;
	protected RecordList inputList;
	
	public MyArrayAdapter(Activity activity, int layoutID, RecordList inputList) {
		super(activity, layoutID, inputList.getArray());
		inflater = activity.getLayoutInflater();
		this.layout = layoutID;
		this.inputList = inputList;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View v = inflater.inflate(this.layout, parent, false);
		
		// Set the text in the list_item layout
		TextView textViewV = (TextView) v.findViewById(R.id.item_text);
		textViewV.setText(inputList.getStatementAction(position));

		// Set the checkbox status in the list_item layout and set a checklistener
		CheckBox checkBoxV = (CheckBox) v.findViewById(R.id.checkBox);
		checkBoxV.setChecked(inputList.getIsCheckAction(position));
		checkBoxV.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				inputList.setIsCheckAction(position,isChecked);		
				
				// save after changes made
				ListManager.getManager().saveList("todo");
				ListManager.getManager().saveList("archive");
			}
		});
		
		return v;
	}
}
