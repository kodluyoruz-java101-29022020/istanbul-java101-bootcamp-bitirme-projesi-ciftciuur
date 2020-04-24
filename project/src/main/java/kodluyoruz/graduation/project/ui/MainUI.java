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

import kodluyoruz.graduation.project.ui.view.BookCrudView;
import kodluyoruz.graduation.project.ui.view.BookSearchView;
import kodluyoruz.graduation.project.ui.view.WelcomePageView;

@SuppressWarnings("serial")
@SpringUI
public class MainUI extends UI {
	@Autowired
	private BookSearchView bookSearchView;
	@Autowired
	private BookCrudView bookCrudView;
	@Autowired
	private WelcomePageView welcomePageView;
	@Autowired
	private SpringViewProvider springViewProvider;

	@Override
	protected void init(VaadinRequest request) {
		setSizeFull();
		Navigator navigator = new Navigator(this, this);
		navigator.addProvider(springViewProvider);
		// Adding all views for navigation

		// welcome page navigate
		navigator.addView("welcome", welcomePageView);
		// search page navigate
		navigator.addView("search", bookSearchView);
		// crud page navigates
		navigator.addView("crud", bookCrudView);
		navigator.navigateTo("welcome");

	}

	@WebServlet(urlPatterns = "/*", name = "GraduationServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
	public static class ActivitiEngineServlet extends SpringVaadinServlet {
		private static final long serialVersionUID = 1L;
	}
}
