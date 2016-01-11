package de.karbach.superapp.data;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;

/**
 * Created by Carsten on 27.12.2015.
 */
public class Card implements Serializable{

    /**
     * Vocabulary in first language
     */
    private String lang1;
    /**
     * Vocabulary in second language
     */
    private String lang2;

    public int getBox() {
        return box;
    }

    public void setBox(int box) {
        this.box = box;
    }

    /**
     * Box level, starting from 1 going up
     */
    private int box = 1;
    /**
     * The type of the word, e.g. adjective, verb, noun
     */
    private String type = null;

    /**
     * Name of the lesson, to which this card belongs to
     */
    private String lesson = null;

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLang2() {
        return lang2;
    }

    public void setLang2(String lang2) {
        this.lang2 = lang2;
    }

    public String getLang1() {
        return lang1;
    }

    public void setLang1(String lang1) {
        this.lang1 = lang1;
    }

    public Card(String lang1, String lang2){
        this.lang1 = lang1;
        this.lang2 = lang2;
    }

    public boolean boxUp(){
        box++;
        if(box <= 5){
            return true;
        }
        else{
            box = 5;
            return false;
        }
    }

    public boolean boxDown(){
        box--;
        if(box < 1){
            box = 1;
            return false;
        }
        else{
            return true;
        }
    }

    public String export(){
        JSONArray array = new JSONArray();
        String[] values = new String[]{lang1, lang2, type, lesson, String.valueOf(box)};

        for(String value : values){
            array.put(value);
        }

        return array.toString();
    }

    public static Card loadImported(String jsonString, boolean loadAll){
        try {
            JSONArray array = new JSONArray(jsonString);

            String lang1 = null;
            if(array.length() >  0){
                lang1 = array.getString(0);
            }
            String lang2 = null;
            if(array.length() >  1){
                lang2 = array.getString(1);
            }
            String type = null;
            if(array.length() >  2){
                type = array.getString(2);
            }
            String lesson = null;
            if(array.length() >  3){
                lesson = array.getString(3);
            }

            Card result = new Card(lang1, lang2);
            result.setType(type);
            result.setLesson(lesson);

            if(loadAll) {
                int box = 1;
                if (array.length() > 4) {
                    box = Integer.parseInt(array.getString(4));
                }
                result.setBox(box);
            }

            return result;

        } catch (JSONException e) {
            return null;
        }
    }
}