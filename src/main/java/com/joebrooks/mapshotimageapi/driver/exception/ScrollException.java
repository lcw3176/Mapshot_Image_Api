package com.joebrooks.mapshotimageapi.driver.exception;

public class ScrollException extends RuntimeException {

    public ScrollException(Throwable e){
        super(e);
    }

    public ScrollException(String s, Throwable e){
        super(s, e);
    }
}
