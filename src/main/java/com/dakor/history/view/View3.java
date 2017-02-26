package com.dakor.history.view;

import com.dakor.history.MyUI;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;

/**
 * Dynamic view with hash. This vew should be in separate history cell, but when going back from this view we should
 * prevent possibility to go forward again. It's like dirty view of the parent one.
 *
 * @author dkor
 */
class View3 extends CssLayout {
	private static int i = 1;

	private boolean isInit = false;

	View3() {
		addComponent(new Label("test_" + (i++)));
		addComponent(new NativeButton("forward", clickEvent -> MyUI.manager.navigateTo(new View3())));

		addAttachListener(attachEvent -> {
					if (!isInit) {
						isInit = false;
						JavaScript.getCurrent().execute("var newState = '_new';" +
								"if (window.history.state == null) {" +
								"    window.history.replaceState(newState, null);" +
								"}" +
								"newState = window.history.state + newState;" +
								"window.history.pushState(newState, null);" +
								"state = newState;");
					}
				}
		);
	}
}
