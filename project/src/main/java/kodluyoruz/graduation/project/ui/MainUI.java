package kodluyoruz.graduation.project.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SpringUI
public class MainUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		Notification.show("Test");

	}

	@WebServlet(urlPatterns = "/*", name = "GraduationServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
	public static class ActivitiEngineServlet extends SpringVaadinServlet {
		private static final long serialVersionUID = 1L;
	}
}
