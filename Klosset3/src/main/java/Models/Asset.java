package Models;

public class Asset {
    private int id;
    private String name;
    private String category;
    private String description;
    private String status;
    private String created_at;
    private String barcodePath;
    public Asset(int id, String name, String category, String description, String status, String created_at) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.status = status;
        this.created_at = created_at;
        this.barcodePath = "src/main/resources/barcodes/asset_" + name + ".png";
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
    public String getCreated_at(){
        return created_at;
    }
    public String getBarcodePath() {
        return barcodePath;
    }
}
