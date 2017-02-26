package com.dakor.history.view;

import com.dakor.history.MyUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;

/**
 * .
 *
 * @author dkor
 */
public class View2 extends CssLayout implements View {
	public View2() {
		setSizeFull();
		addComponent(new Label("view_2"));
		addComponent(new NativeButton("forward", clickEvent -> MyUI.manager.navigateTo(new View3())));
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
		// nothing
	}
}
