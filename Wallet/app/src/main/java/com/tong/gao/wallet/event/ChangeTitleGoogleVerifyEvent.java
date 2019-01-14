package com.tong.gao.wallet.event;

public class ChangeTitleGoogleVerifyEvent {

    private int indexStep;

    public ChangeTitleGoogleVerifyEvent(int indexStep) {
        this.indexStep = indexStep;
    }

    public int getIndexStep() {
        return indexStep;
    }

    public void setIndexStep(int indexStep) {
        this.indexStep = indexStep;
    }
}
