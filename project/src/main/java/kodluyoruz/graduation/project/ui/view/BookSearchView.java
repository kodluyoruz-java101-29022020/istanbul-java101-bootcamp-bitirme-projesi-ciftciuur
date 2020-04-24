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
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.ItemClickListener;

import kodluyoruz.graduation.project.dto.BookSearchDto;

@SuppressWarnings("serial")
@SpringUI
public class BookSearchView extends VerticalLayout implements GraduationView {

	protected Registration gridItemClickListener;
	protected Grid<BookSearchDto> grid;

	public BookSearchView() {
		init();
	}

	private void init() {

		setSizeFull();
		setMargin(false);
		setSpacing(true);

		addComponent(new Label("Kitap Arama Sayfası"));

		grid = new Grid<BookSearchDto>();
		grid.setSizeFull();
		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.setColumnReorderingAllowed(true);
		grid.getEditor().setEnabled(false);
		addComponent(grid);
		setExpandRatio(grid, 1);

		/* set grid columns */

		grid.addColumn(BookSearchDto::getBookName).setCaption("Kitap Adı");
		grid.addColumn(BookSearchDto::getBookPageCount).setCaption("Kitap Sayfa Sayısı");
		grid.addColumn(BookSearchDto::getIsbn).setCaption("Kitap ISBN");
		// TODO : kitap baskı detayı acılıcak poup-up'ta gösterilebilir
		// grid.addColumn(BookSearchDto::getPrinting).setCaption("Kitap Baskı");
		grid.addColumn(BookSearchDto::getBookCategory).setCaption("Kitap Kategorisi");
		// TODO : kitap yazar detayı açılan poup-up'ta gösterilebilir

		gridItemClickListener = grid.addItemClickListener(new ItemClickListener<BookSearchDto>() {
			@Override
			public void itemClick(ItemClick<BookSearchDto> event) {
				if (event.getMouseEventDetails().isDoubleClick()) {
					/* open task form */
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
		List<BookSearchDto> dataList = new ArrayList<>();

		ListDataProvider<BookSearchDto> dataProvider = new ListDataProvider<BookSearchDto>(
				(Collection<BookSearchDto>) dataList);
		ConfigurableFilterDataProvider<BookSearchDto, Void, SerializablePredicate<BookSearchDto>> filterDataProvider = dataProvider
				.withConfigurableFilter();
		grid.setDataProvider(filterDataProvider);
		grid.getDataProvider().refreshAll();
	}

	private void openForm(BookSearchDto bookSearchDto) {
		// TODO : added poup-up for book detail
	}

}
