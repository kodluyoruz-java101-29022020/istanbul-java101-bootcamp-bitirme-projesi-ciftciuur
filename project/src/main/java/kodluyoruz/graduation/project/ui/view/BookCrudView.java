package kodluyoruz.graduation.project.ui.view;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.shared.Registration;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.grid.ItemClickListener;

import kodluyoruz.graduation.project.model.Book;
import kodluyoruz.graduation.project.service.BookService;
import kodluyoruz.graduation.project.ui.MainUI;
import kodluyoruz.graduation.project.ui.view.form.BookCrudPopUpForm;

@SuppressWarnings("serial")
@SpringUI

public class BookCrudView extends VerticalLayout implements GraduationView {
	protected Registration gridItemClickListener;
	protected Grid<Book> grid;
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
		// eğer kitap düzenlenmek istenmişse
		if (book != null) {
			Window winForm = new Window();
			BookCrudPopUpForm form = new BookCrudPopUpForm();

			winForm.setContent(form);
			winForm.center();
			MainUI.getCurrent().addWindow(winForm);
		} else {
			// yeni kitap eklemesi yapılacak
		}

	}
}
