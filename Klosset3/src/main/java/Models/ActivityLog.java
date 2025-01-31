package Models;

public class ActivityLog {
    private int id;
    private String user;
    private String action;
    private String asset;
    private String timestamp;

    public ActivityLog(int id, String user, String action, String asset, String timestamp) {
        this.id = id;
        this.user = user;
        this.action = action;
        this.asset = asset;
        this.timestamp = timestamp;
    }

    public int getId() { return id; }
    public String getUser() { return user; }
    public String getAction() { return action; }
    public String getAsset() { return asset; }
    public String getTimestamp() { return timestamp; }
}
