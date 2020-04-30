package kodluyoruz.graduation.project.ui.view.form;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;

import kodluyoruz.graduation.project.ui.view.design.BookCrudPopUpDesign;

@SuppressWarnings("serial")
public class BookCrudPopUpForm extends BookCrudPopUpDesign {

	public BookCrudPopUpForm() {
		super();
	}

	/*
	 * TODO : burada tekrar ust classtan gelen tum textleri ekledim fakat burada
	 * yanlıs giden birşey var , üst classta zaten eklemıstım tekrar neden ekledım ?
	 * kalıtımda hata yapıyorum
	 * 
	 * TODO : gereksiz eklediğim tüm alanları tekrar sıldım zaten ata classta bu
	 * componentlerın birer örneği(instance) oluşturuluyor
	 */

	public TextField getTxtBookId() {
		return super.txtBookId;
	}

	public void setTxtBookId(TextField txtBookId) {
		super.txtBookId = txtBookId;
	}

	public TextField getTxtBookName() {
		return super.txtBookName;
	}

	public void setTxtBookName(TextField txtBookName) {
		super.txtBookName = txtBookName;
	}

	public TextField getTxtBookDescription() {
		return super.txtBookDescription;
	}

	public void setTxtBookDescription(TextField txtBookDescription) {
		super.txtBookDescription = txtBookDescription;
	}

	public TextField getTxtBookPageCount() {
		return super.txtBookPageCount;
	}

	public void setTxtBookPageCount(TextField txtBookPageCount) {
		super.txtBookPageCount = txtBookPageCount;
	}

	public TextField getTxtBookPublisher() {
		return super.txtBookPublisher;
	}

	public void setTxtBookPublisher(TextField txtBookPublisher) {
		super.txtBookPublisher = txtBookPublisher;
	}

	public DateField getDfBookPublishingDate() {
		return super.dfBookPublishingDate;
	}

	public void setDfBookPublishingDate(DateField dfBookPublishingDate) {
		super.dfBookPublishingDate = dfBookPublishingDate;
	}

	public ComboBox<kodluyoruz.graduation.project.model.Author> getCmbBookAuthor() {
		return super.cmbBookAuthor;
	}

	public void setCmbBookAuthor(ComboBox<kodluyoruz.graduation.project.model.Author> cmbBookAuthor) {
		super.cmbBookAuthor = cmbBookAuthor;
	}

	public ComboBox<kodluyoruz.graduation.project.enums.BookCategory> getCmbBookCategory() {
		return super.cmbBookCategory;
	}

	public void setCmbBookCategory(ComboBox<kodluyoruz.graduation.project.enums.BookCategory> cmbBookCategory) {
		super.cmbBookCategory = cmbBookCategory;
	}

	public TextField getTxtBookNote() {
		return super.txtBookNote;
	}

	public void setTxtBookNote(TextField txtBookNote) {
		super.txtBookNote = txtBookNote;
	}

	public Button getBtnSave() {
		return super.btnSave;
	}

	public void setBtnSave(Button btnSave) {
		super.btnSave = btnSave;
	}

	public Button getBtnUpdate() {
		return super.btnUpdate;
	}

	public void setBtnUpdate(Button btnUpdate) {
		super.btnUpdate = btnUpdate;
	}

	public Button getBtnDelete() {
		return super.btnDelete;
	}

	public void setBtnDelete(Button btnDelete) {
		super.btnDelete = btnDelete;
	}

}
