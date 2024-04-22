package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite;

public class Feedback {
    private int correctPosition;
    private int correctColor;

    public Feedback(int correctPosition, int correctColor) {
        this.correctPosition = correctPosition;
        this.correctColor = correctColor;
    }

    public int getCorrectPosition() {
        return correctPosition;
    }

    public void setCorrectPosition(int correctPosition) {
        this.correctPosition = correctPosition;
    }

    public int getCorrectColor() {
        return correctColor;
    }

    public void setCorrectColor(int correctColor) {
        this.correctColor = correctColor;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "correctPosition=" + correctPosition +
                ", correctColor=" + correctColor +
                '}';
    }
}
