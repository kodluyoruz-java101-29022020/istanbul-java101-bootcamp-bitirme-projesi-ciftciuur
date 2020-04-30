package kodluyoruz.graduation.project.ui.view;

import java.time.LocalDate;
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
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.themes.ValoTheme;

import kodluyoruz.graduation.project.model.Book;
import kodluyoruz.graduation.project.service.AuthorService;
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

	@Autowired
	private AuthorService authorService;

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

		grid.addColumn(Book::getBookId).setCaption("Kitap ID");

		grid.addColumn(Book::getBookName).setCaption("Kitap Adı");

		grid.addColumn(Book::getBookNote).setCaption("Kitap Notu");

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

			form.getTxtBookId().setValue(book.getBookId().toString());

			form.getTxtBookName().setValue(book.getBookName());

			// form.getCmbBookAuthor().setValue(book.getAuthor());

			form.getTxtBookNote().setValue(book.getBookNote());

			form.getTxtBookDescription().setValue(book.getBookDescription());

			form.getTxtBookPageCount().setValue(book.getBookPageCount());

			form.getTxtBookPublisher().setValue(book.getPublisher());

			form.getCmbBookCategory().setValue(book.getBookCategory());

			form.getDfBookPublishingDate()
					.setValue(book.getPublishingYear().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

			form.getBtnUpdate().addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					Book tempBook = new Book();
					// tempBook.setAuthor(form.getCmbBookAuthor().getValue());
					tempBook.setBookCategory(form.getCmbBookCategory().getValue());
					tempBook.setBookDescription(form.getTxtBookDescription().getValue());
					tempBook.setBookName(form.getTxtBookName().getValue());
					tempBook.setBookNote(form.getTxtBookNote().getValue());
					tempBook.setBookPageCount(form.getTxtBookPageCount().getValue());
					tempBook.setPublisher(form.getTxtBookPublisher().getValue());
					tempBook.setPublishingYear(java.util.Date.from(form.getDfBookPublishingDate().getValue()
							.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
					tempBook.setDeleted(true);
					Notification.show(update(tempBook));
				}
			});

			winForm.setContent(form);

		} else {

			// TODO : enumların ısımlerı gelmeli -> vaadin'de bakılıcak
			form.getDfBookPublishingDate().setValue(LocalDate.now());
			form.getCmbBookCategory().setItems(bookService.getAllBookCategories());
			form.getCmbBookAuthor().setItems(authorService.getAllUnDeletedAuthors());
			form.getBtnDelete().setEnabled(false);
			form.getBtnUpdate().setEnabled(false);

			form.getBtnSave().addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					Book tempBook = new Book();
					// tempBook.setAuthor(form.getCmbBookAuthor().getValue());
					tempBook.setBookCategory(form.getCmbBookCategory().getValue());
					tempBook.setBookDescription(form.getTxtBookDescription().getValue());
					tempBook.setBookName(form.getTxtBookName().getValue());
					tempBook.setBookNote(form.getTxtBookNote().getValue());
					tempBook.setBookPageCount(form.getTxtBookPageCount().getValue());
					tempBook.setPublisher(form.getTxtBookPublisher().getValue());
					tempBook.setPublishingYear(java.util.Date.from(form.getDfBookPublishingDate().getValue()
							.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
					tempBook.setDeleted(true);
					Notification.show(save(tempBook));
				}
			});

			winForm.setContent(form);

			winForm.setContent(form);
		}

		winForm.center();
		MainUI.getCurrent().addWindow(winForm);

	}

	private String save(Book book) {
		if (book != null) {
			// authorService.saveAuthor(book.getAuthor());
			bookService.saveBook(book);
			return "İşlem başarılı";
		} else {
			return "Kayıt eklerken hata oluştu";
		}
	}

	private String update(Book book) {
		return null;
	}

	private String delete(Long bookId) {
		return null;
	}
}
