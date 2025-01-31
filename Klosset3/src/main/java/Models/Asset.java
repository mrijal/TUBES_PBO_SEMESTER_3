package Models;

public class Asset {
    private int id;
    private String name;
    private String category;
    private String description;
    private String status;

    public Asset(int id, String name, String category, String description, String status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}
