package child.wellness.app.database;

public class UserInfo {

    String parent_name;
    String parent_phone_number;
    String child_name;
    String child_phone_number;
    String login;
    String password;

    public UserInfo(String parent_name, String parent_phone_number, String child_name, String child_phone_number, String login, String password) {
        this.parent_name = parent_name;
        this.parent_phone_number = parent_phone_number;
        this.child_name = child_name;
        this.child_phone_number = child_phone_number;
        this.login = login;
        this.password = password;
    }

    public String getParent_name() {
        return parent_name;
    }

    public String getParent_phone_number() {
        return parent_phone_number;
    }

    public String getChild_name() {
        return child_name;
    }

    public String getChild_phone_number() {
        return child_phone_number;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
