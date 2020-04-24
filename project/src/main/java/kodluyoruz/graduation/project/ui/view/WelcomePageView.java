package kodluyoruz.graduation.project.ui.view;

import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import kodluyoruz.graduation.project.ui.GraduationView;
import kodluyoruz.graduation.project.ui.view.design.WelcomePageDesign;

@SuppressWarnings("serial")
@SpringUI
public class WelcomePageView extends WelcomePageDesign implements GraduationView {
	public WelcomePageView() {
		init();
	}

	private void init() {
		btnCreateBook.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo("crud");
			}
		});

		btnDeleteBook.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo("crud");
			}
		});

		btnUpdateBook.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo("crud");

			}
		});
		btnSearchBook.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo("search");

			}
		});
		btnApiList.addClickListener(new ClickListener() {

			/*
			 * TODO: created api page for all apis owned by the project
			 * 
			 */
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo("api");

			}
		});
	};

	@Override
	public void retrieveData() {
		// TODO Auto-generated method stub

	}

	/*
	 * TODO : create welcome page for vaadin 8 designer and desing page
	 * 
	 * 
	 */

}
