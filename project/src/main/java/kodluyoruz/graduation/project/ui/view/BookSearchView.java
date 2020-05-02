package kodluyoruz.graduation.project.ui.view;

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
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.themes.ValoTheme;

import kodluyoruz.graduation.project.enums.BookCategory;
import kodluyoruz.graduation.project.model.Book;
import kodluyoruz.graduation.project.service.BookService;

@SuppressWarnings("serial")
@SpringUI
public class BookSearchView extends VerticalLayout implements GraduationView {

	protected Registration gridItemClickListener;
	protected Grid<Book> grid;

	@Autowired
	private BookService bookService;

	public BookSearchView() {
		init();
	}

	private void init() {

		setSizeFull();
		setMargin(false);
		setSpacing(true);

		Button searchButton = new Button("Ara");
		Label lblBookName = new Label("Kitap adı ");
		TextField txtBookName = new TextField();
		Label lblBookCategory = new Label("Kitap Kategorisi ");
		ComboBox<BookCategory> cmbBookCategory = new ComboBox<BookCategory>();
		cmbBookCategory.setItems(BookCategory.values());
		cmbBookCategory.setItemCaptionGenerator(BookCategory::getCategoryName);
		searchButton.setWidth("120px");
		searchButton.setDisableOnClick(false);
		searchButton.setIcon(VaadinIcons.SEARCH);
		searchButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		searchButton.addStyleName(ValoTheme.BUTTON_SMALL);
		addComponent(lblBookName);
		addComponent(txtBookName);
		addComponent(lblBookCategory);
		addComponent(cmbBookCategory);
		addComponent(searchButton);

		grid = new Grid<Book>();
		grid.setSizeFull();
		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.setColumnReorderingAllowed(true);
		grid.getEditor().setEnabled(false);
		addComponent(grid);
		setExpandRatio(grid, 1);

		/* set grid columns */

		grid.addColumn(Book::getBookId).setCaption("Kitap ID");

		grid.addColumn(Book::getBookName).setCaption("Kitap Adı");

		grid.addColumn(Book::getBookNote).setCaption("Kitap Notu");

		grid.addColumn(Book::getBookCategory).setCaption("Kitap Kategorisi");

		grid.addColumn(Book::getBookDescription).setCaption("Kitap Açıklaması");

		grid.addColumn(Book::getBookPageCount).setCaption("Kitap Sayfa Sayısı");

		grid.addColumn(Book::getPublisher).setCaption("Kitap Yayımevi");

		grid.addColumn(Book::getPublishingYear).setCaption("Kitap Yayım Yılı");

		gridItemClickListener = grid.addItemClickListener(new ItemClickListener<Book>() {
			@Override
			public void itemClick(ItemClick<Book> event) {
				if (event.getMouseEventDetails().isDoubleClick()) {
					/* open book form */
					openForm(event.getItem());
				}
			}
		});

	}

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

	private void openForm(Book bookSearchDto) {
		// TODO : added poup-up for book detail
	}

}
