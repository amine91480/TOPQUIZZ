/**
 * Created by <AMAROUCHE> </> on <09/10/2020>.
 **/
package com.amine.amarouche.model;

import java.util.List;

public class Question {

    private String mQuestion;
    private List<String> mChoiceList;
    private int mAnswserIndex;


    public Question(String question, List<String> choiceList, int answserIndex) {
        this.setQuestion(question);
        this.setChoiceList(choiceList);
        this.setAnswserIndex(answserIndex);
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }

    public void setChoiceList(List<String> choiceList) {
        if (choiceList == null){
            throw new IllegalArgumentException("Array cannot be nul");
        }
        mChoiceList = choiceList;
    }

    public int getAnswserIndex() {
        return mAnswserIndex;
    }

    public void setAnswserIndex(int answserIndex) {
        if (answserIndex < 0 || answserIndex >= mChoiceList.size()){
            throw new IllegalArgumentException("Answer index is out of bound");
        }
        mAnswserIndex = answserIndex;
    }

    @Override
    public String toString() {
        return "Question{"+ "mQuestion='"+ mQuestion
                + '\''+ ", mChoiseList=" + mChoiceList
                + ",mAnswerindex=" + mAnswserIndex
                + '}';
    }
}
