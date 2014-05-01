package org.rxtudelft.reactiveui;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;
import rx.Observable;

/**
 * Created by ferdy on 5/1/14.
 */
public class FXRX {
    public static <T extends Event> Observable<T> fromNode(EventType<T> eventType, Node node) {
        return Observable.create((Observable.OnSubscribe<T>) s -> {
            node.addEventHandler(eventType, s::onNext);
        });
    }

    public static <T extends javafx.beans.Observable> Observable<T> fromFXObsevable(javafx.beans.Observable observable) {
        return Observable.create((Observable.OnSubscribe) s -> {
            observable.addListener(s::onNext);
        });
    }
}