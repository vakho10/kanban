package ge.edu.sangu.kanban.db;

import java.util.List;

public class Column {

    private Long id;
    private String color;
    private String title;
    private String description;
    private List<Card> cards;

    public Column() {
    }

    public Column(Long id, String color, String title, String description, List<Card> cards) {
        this.id = id;
        this.color = color;
        this.title = title;
        this.description = description;
        this.cards = cards;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
