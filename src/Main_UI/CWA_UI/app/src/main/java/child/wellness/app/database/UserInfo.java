package child.wellness.app.database;

public class UserInfo {

    String name;
    String phone_number;
    Boolean parent;

    public UserInfo(String name, String phone_number, Boolean parent) {
        this.name = name;
        this.phone_number = phone_number;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public Boolean getParent() {
        return parent;
    }
}
