package hr.tvz.pilizota.hardwareapp.review;

import hr.tvz.pilizota.hardwareapp.hardware.Hardware;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String text;

    private int score;

    @ManyToMany(targetEntity = Hardware.class, mappedBy = "reviews")
    private List<Hardware> hardware;

    public Review() {
    }

    public Review(String title, String text, int score) {
        this.title = title;
        this.text = text;
        this.score = score;
    }

    public Review(Long id, String title, String text, int score) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return id.equals(review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", score=" + score +
                '}';
    }
}
