vaadin-history
==============

The project is to show how to bind vaadin navigation with HTML5 history api.
Here I have used URL fragments and hashes to push into/pull from history.

To modulate the example just imagine we need navigation between views (view1 and view2 in the project)
and for view2 we also can have some temporary view (edit mode) which should not be handled by our application after
refresh the page, for example. In these dynamic views we can have dirty data like for edit mode.

Take a look at the following behaviours to understand:
1) navigate from view1 -> view2: url contains view2 fragment
2) navigate from view2 -> test1: url still contains view2 fragment but also some new hash
3) click on back button: url contains view2 fragment, the view2 is present again, forward browser button is inactive
4) click on back button: url contains view1 fragment, the view1 is present again, forward browser button is active
5) click on forward browser button: we are on view2, forward is inactive
