package ca.ualberta.geneva.geneva_notes;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;

public class TabListener implements ActionBar.TabListener {
	Fragment fragment;
	
	public TabListener(Fragment fragment) {
		this.fragment = fragment;
	}
	
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		ft.replace(R.id.tabs_container, this.fragment);
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

	}
}
