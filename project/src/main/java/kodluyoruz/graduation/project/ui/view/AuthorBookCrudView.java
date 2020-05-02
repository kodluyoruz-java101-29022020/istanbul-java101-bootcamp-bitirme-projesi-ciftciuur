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
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.themes.ValoTheme;

import kodluyoruz.graduation.project.model.Author;
import kodluyoruz.graduation.project.service.AuthorService;
import kodluyoruz.graduation.project.ui.MainUI;
import kodluyoruz.graduation.project.ui.view.form.AuthorCrudPopUpForm;

@SuppressWarnings("serial")
@SpringUI
public class AuthorBookCrudView extends VerticalLayout implements GraduationView {

	protected Registration gridItemClickListener;
	protected Grid<Author> grid;
	protected Button newButton;
	@Autowired
	private AuthorService authorService;

	public AuthorBookCrudView() {
		init();
	}

	private void init() {

		setSizeFull();
		setMargin(false);
		setSpacing(true);

		addComponent(new Label("Kitaplarım"));
		newButton = new Button("Yeni Kayıt");
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
		grid = new Grid<Author>();
		grid.setSizeFull();
		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.setColumnReorderingAllowed(true);
		grid.getEditor().setEnabled(false);
		addComponent(grid);
		setExpandRatio(grid, 1);

		/* set grid columns */

		grid.addColumn(Author::getAuthorId).setCaption("Yazar ID");

		grid.addColumn(Author::getAuthorName).setCaption("Yazar adı");

		grid.addColumn(Author::getAuthorSurName).setCaption("Yazar soyadı");

		gridItemClickListener = grid.addItemClickListener(new ItemClickListener<Author>() {
			@Override
			public void itemClick(ItemClick<Author> event) {
				if (event.getMouseEventDetails().isDoubleClick()) {
					/* open author form */
					openForm(event.getItem());
				}
			}
		});

	};

	@Override
	public void retrieveData() {
		List<Author> dataList = authorService.getAll();
		if (dataList == null) {
			return;
		}
		ListDataProvider<Author> dataProvider = new ListDataProvider<Author>((Collection<Author>) dataList);
		ConfigurableFilterDataProvider<Author, Void, SerializablePredicate<Author>> filterDataProvider = dataProvider
				.withConfigurableFilter();
		grid.setDataProvider(filterDataProvider);
		grid.getDataProvider().refreshAll();

	}

	private void openForm(Author author) {
		Window winForm = new Window();
		winForm.setWidth("75%");
		winForm.setHeight("75%");
		AuthorCrudPopUpForm form = new AuthorCrudPopUpForm();
		// eğer kitap düzenlenmek istenmişse
		if (author != null) {

			form.getTxtAuthId().setValue(author.getAuthorId().toString());

			form.getTxtAuthName().setValue(author.getAuthorName().toString());

			form.getTxtAuthSurName().setValue(author.getAuthorSurName().toString());

			form.getBtnSave().setEnabled(false);

			form.getBtnUpdate().addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					Author tempAuthor = new Author();
					tempAuthor.setAuthorId(Long.parseLong(form.getTxtAuthId().getValue()));
					tempAuthor.setAuthorName(form.getTxtAuthName().getValue());
					tempAuthor.setAuthorSurName(form.getTxtAuthSurName().getValue());

					Notification.show(update(tempAuthor));
				}
			});

			form.getBtnDelete().addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					Notification.show(delete(Long.parseLong(form.getTxtAuthId().getValue())));
				}
			});
			winForm.setContent(form);

		} else {

			form.getTxtAuthId().setValue(author.getAuthorId().toString());

			form.getTxtAuthName().setValue(author.getAuthorName().toString());

			form.getTxtAuthSurName().setValue(author.getAuthorSurName().toString());

			form.getBtnDelete().setEnabled(false);
			form.getBtnUpdate().setEnabled(false);

			form.getBtnSave().addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					Author tempAuthor = new Author();
					tempAuthor.setAuthorName(form.getTxtAuthName().getValue());
					tempAuthor.setAuthorSurName(form.getTxtAuthSurName().getValue());
					Notification.show(save(tempAuthor));
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

	public String save(Author author) {
		if (author != null) {
			authorService.save(author);
			return "İşlem başarılı";
		} else {
			return "Kayıt eklerken hata oluştu";
		}
	}

	public String update(Author author) {
		if (author != null) {
			authorService.save(author);
			return "Güncelleme başarılı";
		} else {
			return "Güncelleme yaparken hata oluştu";
		}
	}

	public String delete(Long authorId) {
		return authorService.delete(authorId);
	}

}
