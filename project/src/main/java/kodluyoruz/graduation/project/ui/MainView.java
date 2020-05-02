package kodluyoruz.graduation.project.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import kodluyoruz.graduation.project.ui.component.MenuItemComponent;
import kodluyoruz.graduation.project.ui.component.MenuItemComponent.MenuItemClickListener;
import kodluyoruz.graduation.project.ui.view.AuthorBookCrudView;
import kodluyoruz.graduation.project.ui.view.BookCrudView;
import kodluyoruz.graduation.project.ui.view.BookSearchView;
import kodluyoruz.graduation.project.ui.view.GraduationView;

@SpringUI
public class MainView extends HorizontalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VerticalLayout vlMenu;
	private VerticalLayout vlMain;
	private GraduationView activeView;

	@Autowired
	private BookCrudView bookCrudView;
	@Autowired
	private BookSearchView bookSearchView;
	@Autowired
	private AuthorBookCrudView authorBookCrudView;

	public MainView() {
		init();
	}

	private void init() {

		this.setSizeFull();
		Panel panelMenu = new Panel();
		panelMenu.setWidth("250px");
		panelMenu.setHeight("100%");
		addComponent(panelMenu);

		vlMenu = new VerticalLayout();
		panelMenu.setContent(vlMenu);

		MenuItemComponent menuItem_1 = new MenuItemComponent("Kitap Ekle/Sil/Güncelle", new MenuItemClickListener() {
			@Override
			public void clicked() {
				if (activeView != null) {
					vlMain.removeComponent(activeView);

				}
				vlMain.addComponent(bookCrudView);
				activeView = bookCrudView;
				bookCrudView.retrieveData();

			}
		});
		MenuItemComponent menuItem_2 = new MenuItemComponent("Kitap Ara", new MenuItemClickListener() {
			@Override
			public void clicked() {
				if (activeView != null) {
					vlMain.removeComponent(activeView);
				}
				vlMain.addComponent(bookSearchView);
				activeView = bookSearchView;
				bookSearchView.retrieveData();
			}
		});

		MenuItemComponent menuItem_3 = new MenuItemComponent("Yazar Ekle/Sil/Güncelle", new MenuItemClickListener() {
			@Override
			public void clicked() {
				if (activeView != null) {
					vlMain.removeComponent(activeView);
				}
				vlMain.addComponent(authorBookCrudView);
				activeView = authorBookCrudView;
				authorBookCrudView.retrieveData();
			}
		});

		MenuItemComponent menuItem_4 = new MenuItemComponent("Api Listesi", new MenuItemClickListener() {
			@Override
			public void clicked() {
				// TODO : Call swagger page

			}
		});
		vlMenu.addComponent(menuItem_1);
		vlMenu.addComponent(menuItem_2);
		vlMenu.addComponent(menuItem_3);
		vlMenu.addComponent(menuItem_4);

		vlMain = new VerticalLayout();
		vlMain.setSizeFull();
		addComponent(vlMain);
		setExpandRatio(vlMain, 1);

	}

}
