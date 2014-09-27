Geneva-notes
==========

CMPUT 301 - Assignment 1 - Simple Todo List application where users can todo items, archive them, or email a todo list.

Application name is : Geneva-notes

Icons in the drawable folders used for the menu bar icons are from : https://developer.android.com/design/downloads/index.html#action-bar-icon-pack 2014-09-06

Attributions:
Used the idea of using controllers for the lists and a listmanager from Abram Hindle’s student-picker app, https://github.com/abramhindle/student-picker and https://www.youtube.com/watch?v=5PPD0ncJU1g&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O
Also used code the the student-picker app, specified parts are noted in the class files in the package.

Used code from http://developer.android.com/training/index.html, more specifically,
http://developer.android.com/guide/topics/ui/dialogs.html
http://developer.android.com/guide/topics/ui/menus.html#CAB
Specific links and the specified lines of code are noted in the class files as well.

Took a line regarding setting layout xml background from : 2014-09-25
https://android.googlesource.com/platform/frameworks/base/+/refs/heads/master/core/res/res/layout/simple_list_item_activated_1.xml

The use of fragments through the action bar and using custom tab listener was inspired by http://www.cs.dartmouth.edu/~campbell/cs65/lecture08/lecture08.html 2014-09-07

Custom array adapter was inspired by http://www.codelearn.org/android-tutorial/twitter/5/listview-custom-adapter-example 2014-09-14

Stackoverflow: For setting the intent type to email only ( .setType("message/rfc822")) API line. 2014-09-26
- Original Question : http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application
- Author of the answer : fixedd - http://stackoverflow.com/users/76835/fixedd


Notes:
Long click on an item to bring up the contextual action bar to get the options of discarding, archiving/unarchiving, and emailing a selection of items.
