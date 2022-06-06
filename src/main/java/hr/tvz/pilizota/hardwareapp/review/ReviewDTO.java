package hr.tvz.pilizota.hardwareapp.review;

public class ReviewDTO {

    private final String title;

    private final String text;

    private final Integer rating;

    public ReviewDTO(String title, String text, Integer score) {
        this.title = title;
        this.text = text;
        this.rating = score;
    }

    public String getText() {
        return text;
    }

    public Integer getRating() {
        return rating;
    }

    public String getTitle() { return title; }
}
