/**
 * Created by <AMAROUCHE> </> on <09/10/2020>.
 **/
package com.amine.amarouche.model;

import java.util.Collections;
import java.util.List;

public class QuestionBank {

    private List<Question> mQuestionList;
    private int mNextQuestionIndex;

    public QuestionBank(List<Question> questionList) {
        mQuestionList = questionList;

        Collections.shuffle(mQuestionList);

        mNextQuestionIndex = 0;
    }

    public Question getQuestion() {
        if (mNextQuestionIndex == mQuestionList.size()) {
            mNextQuestionIndex = 0;
        }
        return mQuestionList.get(mNextQuestionIndex++);
    }
}
