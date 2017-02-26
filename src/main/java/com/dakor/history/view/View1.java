package com.dakor.history.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

/**
 * .
 *
 * @author dkor
 */
public class View1 extends CssLayout implements View {
	public View1() {
		setSizeFull();
		addComponent(new Label("view_1"));
		addComponent(new NativeButton("forward", clickEvent -> {
			UI.getCurrent().getNavigator().removeView("view2");
			UI.getCurrent().getNavigator().addView("view2", new View2());
			UI.getCurrent().getNavigator().navigateTo("view2");
		}));
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
		// nothing
	}
}
