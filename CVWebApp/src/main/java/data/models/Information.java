package data.models;

/**
 *
 * @author Andreas Vikke
 */
public class Information {
    private int id;
    private String name;
    private String text;

    public Information(int id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }    
}
