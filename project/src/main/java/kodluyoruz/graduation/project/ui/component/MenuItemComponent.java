package kodluyoruz.graduation.project.ui.component;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class MenuItemComponent extends HorizontalLayout {

	public interface MenuItemClickListener {
		public void clicked();
	}

	public MenuItemComponent(String title, MenuItemClickListener listener) {
		this.setMargin(false);
		this.setSpacing(false);
		Label lbl = new Label(title);
		addComponent(lbl);
		addLayoutClickListener(new LayoutClickListener() {
			@Override
			public void layoutClick(LayoutClickEvent event) {
				listener.clicked();
			}
		});
	}

}
