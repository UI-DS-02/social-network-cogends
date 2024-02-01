package connection;

import java.util.HashMap;
import java.util.Map;

public class Scale {
    private int scaleOfSimilarSkill;
    private int scaleOfWorkPlace;
    private int scaleOfField;
    private int scaleOfUniversity;
    private int degree;

    public Scale(int scaleOfSimilarSkill, int scaleOfWorkPlace, int scaleOfField, int scaleOfUniversity, int degree) {
        this.scaleOfSimilarSkill = scaleOfSimilarSkill;
        this.scaleOfWorkPlace = scaleOfWorkPlace;
        this.scaleOfField = scaleOfField;
        this.scaleOfUniversity = scaleOfUniversity;
        this.degree = degree;
    }

    public Scale() {
        this.scaleOfSimilarSkill =20;
        this.scaleOfWorkPlace =15;
        this.scaleOfField = 10;
        this.scaleOfUniversity = 7;
        this.degree =2;
    }
    public Scale(String priority1, String priority2, String priority3, String priority4) {
        Map<String,Integer> map= new HashMap<>();
        map.put(priority1,4);
        map.put(priority2,3);
        map.put(priority3,2);
        map.put(priority4,1);
        if (map.containsKey("scaleOfSimilarSkill")){
            this.scaleOfSimilarSkill=map.get("scaleOfSimilarSkill")*5;
        }else {
            this.scaleOfSimilarSkill=0;
        }
        if (map.containsKey("scaleOfWorkPlace")){
            this.scaleOfSimilarSkill=map.get("scaleOfWorkPlace")*5;
        }else {
            this.scaleOfSimilarSkill=0;
        }
        if (map.containsKey("scaleOfField")){
            this.scaleOfSimilarSkill=map.get("scaleOfField")*5;
        }else {
            this.scaleOfSimilarSkill=0;
        }
        if (map.containsKey("scaleOfUniversity")){
            this.scaleOfSimilarSkill=map.get("scaleOfUniversity")*5;
        }else {
            this.scaleOfSimilarSkill=0;
        }
        if (map.containsKey("degree")){
            this.scaleOfSimilarSkill=map.get("degree")*5;
        }else {
            this.scaleOfSimilarSkill=0;
        }
    }

    public int getScaleOfSimilarSkill() {
        return scaleOfSimilarSkill;
    }

    public void setScaleOfSimilarSkill(int scaleOfSimilarSkill) {
        this.scaleOfSimilarSkill = scaleOfSimilarSkill;
    }

    public int getScaleOfWorkPlace() {
        return scaleOfWorkPlace;
    }

    public void setScaleOfWorkPlace(int scaleOfWorkPlace) {
        this.scaleOfWorkPlace = scaleOfWorkPlace;
    }

    public int getScaleOfField() {
        return scaleOfField;
    }

    public void setScaleOfField(int scaleOfField) {
        this.scaleOfField = scaleOfField;
    }

    public int getScaleOfUniversity() {
        return scaleOfUniversity;
    }

    public void setScaleOfUniversity(int scaleOfUniversity) {
        this.scaleOfUniversity = scaleOfUniversity;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}
