package model.bo;

public class Login {
    model.dao.Login dao = new model.dao.Login();

    public boolean isVaildUser(String username, String password) {
        return dao.isVaildUser(username, password);
    }
}
