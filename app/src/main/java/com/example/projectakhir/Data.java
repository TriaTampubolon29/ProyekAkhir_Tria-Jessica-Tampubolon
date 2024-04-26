package com.example.projectakhir;

public class Data {
    private String id, topik, motivation;

    public Data(){

    }

    public Data(String id, String topik, String motivation){
        this.id = id;
        this.topik = topik;
        this.motivation = motivation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopik() {
        return topik;
    }

    public void setTopik(String topik) {
        this.topik = topik;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }
}
