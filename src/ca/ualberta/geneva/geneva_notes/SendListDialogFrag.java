// This class SENDLISTDIALOGFRAG sets up the alert dialog that gathers information for the intent that emails items in the list
package ca.ualberta.geneva.geneva_notes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class SendListDialogFrag extends DialogFragment {
	
	private static final String EMAIL_ADDRESS = "email_address";
	private static final String EMAIL_SUBJECT = "email_subject";
	
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
	// *Start* Taken from: http://developer.android.com/guide/topics/ui/dialogs.html 2014-09-19//
	// Only modified the layout name.
	@Override
	public Dialog onCreateDialog(Bundle savedInstance) {		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		final View v = inflater.inflate(R.layout.dialog_email, null);
		builder.setView(v);
	// *End* http://developer.android.com/guide/topics/ui/dialogs.html //
		
		builder.setPositiveButton(R.string.dialogSend, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				EditText email_address = (EditText) v.findViewById(R.id.email_address);
				EditText email_subject = (EditText) v.findViewById(R.id.email_subject);
				Bundle args = getArguments();
				args.putString(EMAIL_ADDRESS, email_address.getText().toString());
				args.putString(EMAIL_SUBJECT, email_subject.getText().toString());
				emailAction(args);
			}
		});
		
		builder.setNegativeButton(R.string.dialogCancel, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		
		AlertDialog emailDialogBox = builder.create();
		return emailDialogBox;
	}
	
	private void emailAction(Bundle args) {		
		MainController controller = new MainController();
		Intent intent = controller.emailAction(args);
		startActivity(Intent.createChooser(intent, null));
	}
}
