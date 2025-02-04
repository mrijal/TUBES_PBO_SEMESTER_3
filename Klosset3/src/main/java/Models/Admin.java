package Models;

public class Admin extends User {
    public Admin(int id, String username) {
        super(id, username, "admin");
    }

    public void approveAsset(int assetId) {
        System.out.println("Admin menyetujui aset dengan ID: " + assetId);
    }
}
