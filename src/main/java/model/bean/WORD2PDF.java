package model.bean;

import java.sql.Timestamp;

public class WORD2PDF {
    private Integer ID;
    private String User;
    private String SourceName;
    private String SourcePath;
    private String TargetPath;
    private Timestamp DateStart;
    private Timestamp DateCompleted;
    private Integer Result;
    public Integer getID() {
        return ID;
    }
    public Timestamp getDateCompleted() {
        return DateCompleted;
    }
    public void setDateCompleted(Timestamp dateCompleted) {
        this.DateCompleted = dateCompleted;
    }
    public Timestamp getDateStart() {
        return DateStart;
    }
    public void setDateStart(Timestamp dateStart) {
        this.DateStart = dateStart;
    }
    public Integer getResult() {
        return Result;
    }
    public String getResultString() {
        switch (getResult()) {
            default:
                return "Unknown";
            case -1:
                return "Error";
            case 0:
                return "Pending";
            case 1:
                return "Converting";
            case 2:
                return "Successful";
        }
    }
    public void setResult(Integer result) {
        this.Result = result;
    }
    public String getTargetPath() {
        return TargetPath;
    }
    public void setTargetPath(String targetPath) {
        this.TargetPath = targetPath;
    }
    public String getSourcePath() {
        return SourcePath;
    }
    public void setSourcePath(String sourcePath) {
        this.SourcePath = sourcePath;
    }
    public String getSourceName() {
        return SourceName;
    }
    public void setSourceName(String sourceName) {
        this.SourceName = sourceName;
    }
    public String getUser() {
        return User;
    }
    public void setUser(String user) {
        this.User = user;
    }
    public void setID(Integer iD) {
        this.ID = iD;
    }
}
