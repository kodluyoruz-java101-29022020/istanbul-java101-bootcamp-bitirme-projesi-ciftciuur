package kodluyoruz.graduation.project.ui.view.form;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;

import kodluyoruz.graduation.project.ui.view.design.AuthorCrudPoupUpDesign;

public class AuthorCrudPopUpForm extends AuthorCrudPoupUpDesign {

	public AuthorCrudPopUpForm() {
		super();
	}

	public TextField getTxtAuthId() {
		return super.txtAuthId;
	}

	public void setTxtAuthId(TextField txtAuthId) {
		super.txtAuthId = txtAuthId;
	}

	public TextField getTxtAuthName() {
		return super.txtAuthName;
	}

	public void setTxtAuthName(TextField txtAuthName) {
		super.txtAuthName = txtAuthId;
	}

	public TextField getTxtAuthSurName() {
		return super.txtAuthSurName;
	}

	public void setTxtAuthSurName(TextField txtAuthSurName) {
		super.txtAuthSurName = txtAuthId;
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
