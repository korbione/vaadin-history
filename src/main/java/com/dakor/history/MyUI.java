package com.dakor.history;

import com.dakor.history.view.View1;
import com.dakor.history.view.View2;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.dakor.history.MyAppWidgetset")
public class MyUI extends UI {
	public static NavigationManager manager;

	private boolean isNavigationEnabled = true;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		manager = new NavigationManager();
		setContent(manager);

		Navigator navigator = new Navigator(this, manager);
		navigator.addView("view1", new View1());
		navigator.addView("view2", new View2());
		navigator.addViewChangeListener(new ViewChangeListener() {
			@Override
			public boolean beforeViewChange(ViewChangeEvent viewChangeEvent) {
				try {
					if (isNavigationEnabled) {
						manager.navigateTo((Component) viewChangeEvent.getNewView());
					}

					return isNavigationEnabled;
				} finally {
					isNavigationEnabled = true;
				}
			}

			@Override
			public void afterViewChange(ViewChangeEvent viewChangeEvent) {
				// nothing
			}
		});

		navigator.navigateTo("view1");

		JavaScript.getCurrent().addFunction("disable", jsonArray -> isNavigationEnabled = false);
		JavaScript.getCurrent().addFunction("navigateForward", jsonArray -> manager.navigateTo(manager.getNextComponent()));
		JavaScript.getCurrent().addFunction("navigateBack", jsonArray -> {
			manager.navigateBack();
			// to prevent forward in the future, remove the next component
			if (jsonArray.getBoolean(0)) {
				manager.setNextComponent(null);
			}
		});
		JavaScript.getCurrent().execute("window.addEventListener('popstate', function(e) {" +
				"if (e.state == '_new') {" +
				// going back without possibility to forward again
				"    state = null;" +
				"    var url = window.location;" +
				"    disable();" +
				"    window.history.back();" +
				"    window.history.pushState(null, null, url);" +
				"    navigateBack(false);" +
				"} else if (e.state !=  null && e.state.indexOf(state) > -1) {" +
				"    state = e.state;" +
				"    navigateForward();" +
				"} else if (e.state != null && e.state.indexOf('_new') > -1) {" +
				"    state = e.state;" +
				"    navigateBack(true);" +
				"}" +
				"}, false);");
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
		// nothing
	}
}
