// This class ADDNEWITEMDIALOGFRAG sets up the alert dialog that gathers user input to add a new item to the todo list
package ca.ualberta.geneva.geneva_notes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewItemDialogFrag extends DialogFragment {

	/*
	Copyright (C) 2014 The Android Open Source Project
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	http://www.apache.org/licenses/LICENSE-2.0
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
	 */
	
	// *Start* Taken from: http://developer.android.com/guide/topics/ui/dialogs.html 2014-09-07 //
	// Only modified layout name
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	
		LayoutInflater inflater = getActivity().getLayoutInflater();
		final View view = inflater.inflate(R.layout.dialog_add_newitem, null);
		builder.setView(view);
	// *End* http://developer.android.com/guide/topics/ui/dialogs.html //	
		
		builder.setPositiveButton(R.string.dialogOk, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				MainController controller = new MainController();
				
				EditText userInputText = (EditText) view.findViewById(R.id.userInput);
				String text = userInputText.getText().toString();
				if (text != ""){
					controller.addTodoItem(new  ListItem(text));
					
					TodoFragment.notifyAdapter();
					Toast.makeText(getActivity(), R.string.item_added, Toast.LENGTH_SHORT).show();
					
					// save after changes made
					ListManager.getManager().saveList("todo");
				}
			}
		});
		
		builder.setNegativeButton(R.string.dialogCancel, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {				
			}
		});
		
		AlertDialog dialogBox = builder.create();
		
		return dialogBox;
	}
	
}
