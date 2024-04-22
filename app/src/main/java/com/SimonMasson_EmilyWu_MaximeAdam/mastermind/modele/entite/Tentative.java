package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite;

import androidx.annotation.NonNull;

import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Code;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Feedback;

public class Tentative {
    private Code code;
    private Feedback feedback;

    public Tentative(Code code, Feedback feedback) {
        this.code = code;
        this.feedback = feedback;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    @NonNull
    @Override
    public String toString() {
        return "Tentative{" +
                "code=" + code +
                ", feedback=" + feedback +
                '}';
    }
}
