package kodluyoruz.graduation.project.ui;

import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.UI;

@SpringUI
public class MainUI extends UI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SpringViewProvider springViewProvider;
	@Autowired
	private MainView mainView;

	@Override
	protected void init(VaadinRequest request) {
		setSizeFull();
		Navigator navigator = new Navigator(this, this);
		navigator.addProvider(springViewProvider);
		// Adding all views for navigation
		navigator.addView("main", mainView);
		navigator.navigateTo("main");

	}

	@WebServlet(urlPatterns = "/*", name = "GraduationServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
	public static class ActivitiEngineServlet extends SpringVaadinServlet {
		private static final long serialVersionUID = 1L;
	}
}
