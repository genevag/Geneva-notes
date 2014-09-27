/* This class ARCHIVEFRAGMENT deals with displaying the archive list when the archive tab is selected.
 * It implements the contextual action bar following a long click on any list item. 
 */

package ca.ualberta.geneva.geneva_notes;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.DialogFragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AbsListView.MultiChoiceModeListener;

public class ArchiveFragment extends ListFragment {

	// adapter used for the archive list items
	private static MyArrayAdapter adapter;
	
	// arraylist of integers to keep track of selected items to disard/unarchive/email
	private static ArrayList<Integer> helperPositionArray;

	// to store tabs while they are temporarily removed when in CAB mode
	private static ActionBar.Tab temptab0;
	private static ActionBar.Tab temptab2;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		adapter = new MyArrayAdapter(getActivity(), R.layout.list_item, MainController.getArchiveList());
		setListAdapter(adapter);
		
		helperPositionArray = new ArrayList<Integer>();
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
		/*
		Copyright (C) 2014 Geneva Giang geneva@ualberta.ca
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
		// *Start* Taken from: http://developer.android.com/guide/topics/ui/menus.html#CAB 2014-09-14// 
		// Referenced & took the general structure outline only, ie the first 2 lines below and the method structures (doesn't include method bodies unless as specified below) Modified.
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		getListView().setMultiChoiceModeListener(new MultiChoiceModeListener() {

		    @Override
		    public void onItemCheckedStateChanged(ActionMode mode, int position,long id, boolean checked) {
		    	if (checked == true) {
		    		helperPositionArray.add(position);
		    	}
		    	else {
		    		helperPositionArray.remove((Integer)position);
		    		
		    	}
		    	
		    }

		    @Override
		    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {		    	
		    	
		    	MainController controller = new MainController();

				switch (item.getItemId()) {
					case R.id.action_unarchive:
						controller.unarchiveItems(helperPositionArray);	
						adapter.notifyDataSetChanged();
						
						Toast.makeText(getActivity(), R.string.items_unarchived, Toast.LENGTH_SHORT).show();
						
						// save after changes made
						ListManager.getManager().saveList("todo");
						ListManager.getManager().saveList("archive");
						
						mode.finish();
						return true;
						
					case R.id.action_email_archive:						
						controller.setHelperArray(helperPositionArray);
						
						DialogFragment sendListDialog = new SendListDialogFrag();
						Bundle args = new Bundle();
						args.putInt("menu_item_id", R.id.action_email_archive);
						sendListDialog.setArguments(args);
						sendListDialog.show(getFragmentManager(), null);
							
						mode.finish();
						return true;
						
					case R.id.action_discard:					
						controller.removeArchiveItems(helperPositionArray);
						adapter.notifyDataSetChanged();
					
						Toast.makeText(getActivity(), R.string.items_discard, Toast.LENGTH_SHORT).show();
						
						// save after changes made
						ListManager.getManager().saveList("archive");
						
						mode.finish();
						return true;
						
					default:
						return false;
				}
		    }

		    @Override
		    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		    	
		    	// this line was taken from http://developer.android.com/guide/topics/ui/menus.html#CAB
		        MenuInflater inflater = mode.getMenuInflater();
		        
		        // To catch any missed updates before inflating the CAB screen
		        adapter.notifyDataSetChanged();
		        
		        // Remove other tabs to only show the current tab when in CAB mode
		        temptab0 = getActivity().getActionBar().getTabAt(0);
		        temptab2 = getActivity().getActionBar().getTabAt(2);
		        getActivity().getActionBar().removeTabAt(0);
		        getActivity().getActionBar().removeTabAt(1);
		        
		        // this line was taken from http://developer.android.com/guide/topics/ui/menus.html#CAB
		        inflater.inflate(R.menu.edit_screen_archive, menu);
		        
		        return true;
		    }

		    @Override
		    public void onDestroyActionMode(ActionMode mode) {
		    	helperPositionArray.clear();
		    	
		    	// Put tabs back to show all tabs again when exiting CAB mode
		    	getActivity().getActionBar().addTab(temptab0, 0);
		    	getActivity().getActionBar().addTab(temptab2, 2);
		    }

		    @Override
		    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		        return false;
		    }
		});
		// *End* Taken from: http://developer.android.com/guide/topics/ui/menus.html#CAB //
	}
}
