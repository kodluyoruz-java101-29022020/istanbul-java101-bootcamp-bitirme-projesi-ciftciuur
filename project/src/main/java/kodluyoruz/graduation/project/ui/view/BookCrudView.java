package kodluyoruz.graduation.project.ui.view;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.shared.Registration;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.themes.ValoTheme;

import kodluyoruz.graduation.project.model.Book;
import kodluyoruz.graduation.project.service.BookService;
import kodluyoruz.graduation.project.ui.MainUI;
import kodluyoruz.graduation.project.ui.view.form.BookCrudPopUpForm;

@SuppressWarnings("serial")
@SpringUI

public class BookCrudView extends VerticalLayout implements GraduationView {
	protected Registration gridItemClickListener;
	protected Grid<Book> grid;
	protected Button newButton;
	@Autowired
	private BookService bookService;

	public BookCrudView() {
		init();
	}

	private void init() {

		setSizeFull();
		setMargin(false);
		setSpacing(true);

		addComponent(new Label("Kitaplarım"));
		newButton = new Button("Kitap Ekle");
		newButton.setWidth("120px");
		newButton.setDisableOnClick(false);
		newButton.setIcon(VaadinIcons.PLUS);
		newButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		newButton.addStyleName(ValoTheme.BUTTON_SMALL);
		newButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				openForm(null);
			}
		});
		addComponent(newButton);
		grid = new Grid<Book>();
		grid.setSizeFull();
		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.setColumnReorderingAllowed(true);
		grid.getEditor().setEnabled(false);
		addComponent(grid);
		setExpandRatio(grid, 1);

		/* set grid columns */

		grid.addColumn(Book::getBookName).setCaption("Kitap Adı");
		grid.addColumn(Book::getBookPageCount).setCaption("Kitap Sayfa Sayısı");

		gridItemClickListener = grid.addItemClickListener(new ItemClickListener<Book>() {
			@Override
			public void itemClick(ItemClick<Book> event) {
				if (event.getMouseEventDetails().isDoubleClick()) {
					/* open book form */
					openForm(event.getItem());
				}
			}
		});

	};

	public void retrieveData() {
		List<Book> dataList = bookService.getAllUnDeletedBooks();
		if (dataList == null) {
			return;
		}
		ListDataProvider<Book> dataProvider = new ListDataProvider<Book>((Collection<Book>) dataList);
		ConfigurableFilterDataProvider<Book, Void, SerializablePredicate<Book>> filterDataProvider = dataProvider
				.withConfigurableFilter();
		grid.setDataProvider(filterDataProvider);
		grid.getDataProvider().refreshAll();

	}

	private void openForm(Book book) {
		Window winForm = new Window();
		winForm.setWidth("75%");
		winForm.setHeight("75%");
		BookCrudPopUpForm form = new BookCrudPopUpForm();
		// eğer kitap düzenlenmek istenmişse
		if (book != null) {

			winForm.setContent(form);

		} else {
			// TODO : enumların ısımlerı gelmeli -> vaadin'de bakılıcak
			form.getDfBookPublishingDate().setValue(LocalDate.now());
			form.getCmbBookCategory().setItems(bookService.getAllBookCategories());
			form.getBtnDelete().setEnabled(false);
			form.getBtnUpdate().setEnabled(false);

			winForm.setContent(form);
		}

		winForm.center();
		MainUI.getCurrent().addWindow(winForm);

	}

	private String save() {
		return null;
	}

	private String update() {
		return null;
	}

	private String delete() {
		return null;
	}
}
