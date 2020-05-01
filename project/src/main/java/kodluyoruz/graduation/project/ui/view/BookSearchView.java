package kodluyoruz.graduation.project.ui.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.shared.Registration;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.ItemClickListener;

import kodluyoruz.graduation.project.dto.BookSearchDto;
import kodluyoruz.graduation.project.model.Book;

@SuppressWarnings("serial")
@SpringUI
public class BookSearchView extends VerticalLayout implements GraduationView {

	protected Registration gridItemClickListener;
	protected Grid<Book> grid;

	public BookSearchView() {
		init();
	}

	private void init() {

		setSizeFull();
		setMargin(false);
		setSpacing(true);

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

		/*
		 * TODO : bu fonksiyonda aktif kitapların listelenmesi sağlayacak api
		 * çağırılıacak
		 * 
		 * örneğin -> localhost:7070/api/bookSearch/all
		 */
		List<Book> dataList = new ArrayList<>();

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
