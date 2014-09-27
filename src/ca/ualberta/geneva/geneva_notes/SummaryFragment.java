/* This class SUMMARYFRAGMENT calculates & shows the check/uncheck count and total count for items in the TODO & ARCHIVE list*/
package ca.ualberta.geneva.geneva_notes;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SummaryFragment extends Fragment {
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_summary, container, false);
		
		MainController controller = new MainController();
		
		TextView totalTodo = (TextView) view.findViewById(R.id.totalTodo);
		int totalTodoCount = controller.getTodoCheckCount() + controller.getTodoUncheckCount();
		totalTodo.setText(totalTodo.getText() + Integer.toString(totalTodoCount));
		
		TextView checkTodo = (TextView) view.findViewById(R.id.checkedTodo);
		checkTodo.setText(checkTodo.getText() + Integer.toString(controller.getTodoCheckCount()));
		
		TextView uncheckTodo = (TextView) view.findViewById(R.id.uncheckedTodo);
		uncheckTodo.setText(uncheckTodo.getText() + Integer.toString(controller.getTodoUncheckCount()));
		
		TextView totalArchive = (TextView) view.findViewById(R.id.totalArchive);
		int totalArchiveCount = controller.getArchiveCheckCount() + controller.getArchiveUncheckCount();
		totalArchive.setText(totalArchive.getText() + Integer.toString(totalArchiveCount));
		
		TextView checkArchive = (TextView) view.findViewById(R.id.checkedArchive);
		checkArchive.setText(checkArchive.getText() + Integer.toString(controller.getArchiveCheckCount()));
		
		TextView uncheckArchive = (TextView) view.findViewById(R.id.uncheckedArchive);
		uncheckArchive.setText(uncheckArchive.getText() + Integer.toString(controller.getArchiveUncheckCount()));
		
		return view;
	}
}
