package model.bo;

import java.sql.Timestamp;
import java.util.List;

import model.bean.PDF2WORD;

public class Data2 {
    model.dao.Data2 data2 = new model.dao.Data2();

    public List<PDF2WORD> getStatusFromUser(String user) {
        return data2.getStatusFromUser(user);
    }

    public void addStatus(PDF2WORD word) {
        data2.addStatus(word);
    }

    public PDF2WORD getStatusByID(Integer ID) {
        return data2.getStatusByID(ID);
    }

    public void setStatusResult(String sourcePath, Timestamp ts, Integer result) {
        data2.setStatusResult(sourcePath, ts, result);
    }
}
