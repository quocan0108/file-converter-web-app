package model.bo;

import java.sql.Timestamp;
import java.util.List;

import model.bean.WORD2PDF;

public class Data {
    model.dao.Data data = new model.dao.Data();

    public List<WORD2PDF> getStatusFromUser(String user) {
        return data.getStatusFromUser(user);
    }

    public void addStatus(WORD2PDF pdf) {
        data.addStatus(pdf);
    }

    public WORD2PDF getStatusByID(Integer ID) {
        return data.getStatusByID(ID);
    }

    public void setStatusResult(String sourcePath, Timestamp ts, Integer result) {
        data.setStatusResult(sourcePath, ts, result);
    }
}
