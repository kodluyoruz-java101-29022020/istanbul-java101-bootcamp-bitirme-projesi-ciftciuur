package kodluyoruz.graduation.project.ui.view;

import java.time.ZoneId;
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
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.themes.ValoTheme;

import kodluyoruz.graduation.project.enums.BookCategory;
import kodluyoruz.graduation.project.model.Author;
import kodluyoruz.graduation.project.model.Book;
import kodluyoruz.graduation.project.service.AuthorService;
import kodluyoruz.graduation.project.service.BookService;
import kodluyoruz.graduation.project.ui.MainUI;
import kodluyoruz.graduation.project.ui.view.form.BookCrudPopUpForm;

@SuppressWarnings("serial")
@SpringUI
public class BookSearchView extends VerticalLayout implements GraduationView {

	protected Registration gridItemClickListener;
	protected Grid<Book> grid;

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

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
		searchButton.setWidth("120px");
		searchButton.setDisableOnClick(false);
		searchButton.setIcon(VaadinIcons.SEARCH);
		searchButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		searchButton.addStyleName(ValoTheme.BUTTON_SMALL);
		addComponent(lblBookName);
		addComponent(txtBookName);
		addComponent(searchButton);
		searchButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (!"".equals(txtBookName.getValue())) {
					retrieveData(txtBookName.getValue());
				} else {
					retrieveData();
				}
			}
		});

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

	public void retrieveData(String bookName) {

		List<Book> dataList = bookService.findByBookName(bookName);
		if (dataList == null) {
			return;
		}
		ListDataProvider<Book> dataProvider = new ListDataProvider<Book>((Collection<Book>) dataList);
		ConfigurableFilterDataProvider<Book, Void, SerializablePredicate<Book>> filterDataProvider = dataProvider
				.withConfigurableFilter();
		grid.setDataProvider(filterDataProvider);
		grid.getDataProvider().refreshAll();
	}

	public void retrieveData() {

		List<Book> dataList = bookService.getAll();
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

			form.getTxtBookId().setValue(book.getBookId().toString());

			form.getTxtBookName().setValue(book.getBookName());

			form.getCmbBookAuthor().setItems(authorService.getAll());
			form.getCmbBookAuthor().setSelectedItem(authorService.findByAuthorId(book.getAuthor().getAuthorId()));
			form.getCmbBookAuthor().setItemCaptionGenerator(Author::getAuthorName);

			form.getTxtBookNote().setValue(book.getBookNote());

			form.getTxtBookDescription().setValue(book.getBookDescription());

			form.getTxtBookPageCount().setValue(book.getBookPageCount());

			form.getTxtBookPublisher().setValue(book.getPublisher());

			form.getCmbBookCategory().setItems(BookCategory.values());
			form.getCmbBookCategory().setSelectedItem(book.getBookCategory());
			form.getCmbBookCategory().setItemCaptionGenerator(BookCategory::getCategoryName);

			form.getDfBookPublishingDate()
					.setValue(book.getPublishingYear().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

			form.getBtnSave().setEnabled(false);

			form.getBtnUpdate().addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					Book tempBook = new Book();
					tempBook.setBookId(Long.parseLong(form.getTxtBookId().getValue()));
					tempBook.setAuthor(form.getCmbBookAuthor().getValue());
					tempBook.setBookCategory(form.getCmbBookCategory().getValue());
					tempBook.setBookDescription(form.getTxtBookDescription().getValue());
					tempBook.setBookName(form.getTxtBookName().getValue());
					tempBook.setBookNote(form.getTxtBookNote().getValue());
					tempBook.setBookPageCount(form.getTxtBookPageCount().getValue());
					tempBook.setPublisher(form.getTxtBookPublisher().getValue());
					tempBook.setPublishingYear(java.util.Date.from(form.getDfBookPublishingDate().getValue()
							.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
					Notification.show(update(tempBook));
				}
			});

			form.getBtnDelete().addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					Notification.show(delete(Long.parseLong(form.getTxtBookName().getValue())));
				}
			});
			winForm.setContent(form);

		}
		winForm.center();
		MainUI.getCurrent().addWindow(winForm);
		winForm.addCloseListener(new CloseListener() {

			@Override
			public void windowClose(CloseEvent e) {
				retrieveData();
			}
		});

	}

	public String save(Book book) {
		return null;
	}

	public String update(Book book) {
		if (book != null) {
			bookService.save(book);
			return "Güncelleme başarılı";
		} else {
			return "Güncelleme yaparken hata oluştu";
		}
	}

	public String delete(Long bookId) {
		return bookService.delete(bookId);
	}

}
