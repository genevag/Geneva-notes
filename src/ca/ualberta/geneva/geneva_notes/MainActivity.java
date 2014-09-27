/* 
Geneva-notes : A simple TODO list application.

Copyright (C) 2014 Geneva Giang

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
package ca.ualberta.geneva.geneva_notes;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setUpActionBar();
		ListManager.initializeManager(getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	// methods that are called on by menu items
	public void addNewItem(MenuItem item) {
		DialogFragment addItemDialog = new AddNewItemDialogFrag();
		addItemDialog.show(getFragmentManager(), null);
		return;
	}

	
	public void emailAction(MenuItem item) {
		DialogFragment sendListDialog = new SendListDialogFrag();
		Bundle args = new Bundle();
		args.putInt("menu_item_id", item.getItemId());
		sendListDialog.setArguments(args);
		sendListDialog.show(getFragmentManager(), null);
	}

	
	// Create/set-up the actionbar
	private void setUpActionBar() {	
		ActionBar actionbar = getActionBar();
		actionbar.setDisplayShowTitleEnabled(false);
		actionbar.setDisplayShowHomeEnabled(false);
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		ActionBar.Tab todoTab = actionbar.newTab().setText(R.string.tab_todo);
		Fragment todoFrag = new TodoFragment();
		todoTab.setTabListener(new TabListener(todoFrag));
		actionbar.addTab(todoTab);
		
		ActionBar.Tab archiveTab = actionbar.newTab().setText(R.string.tab_archive);
		Fragment archiveFrag = new ArchiveFragment();
		archiveTab.setTabListener(new TabListener(archiveFrag));
		actionbar.addTab(archiveTab);
		
		ActionBar.Tab summaryTab = actionbar.newTab().setText(R.string.tab_summary);
		Fragment summaryFrag = new SummaryFragment();
		summaryTab.setTabListener(new TabListener(summaryFrag));
		actionbar.addTab(summaryTab);
	}
}
