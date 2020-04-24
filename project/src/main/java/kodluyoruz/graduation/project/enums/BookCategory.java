package kodluyoruz.graduation.project.enums;

public enum BookCategory {
	EDUCATİON("eğitim"), ACADEMIC("akademik"), COMPUTER("bilgisayar"), SCIENCE_ENGINERRING(
			"bilim ve mühendislik"), PHILOSOPHY("felsefe"), LITERATURE("edebiyat");

	private String categoryName;

	BookCategory(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

}
