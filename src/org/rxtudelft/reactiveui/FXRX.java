package org.rxtudelft.reactiveui;

import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;
import rx.Observable;

/**
 * Wrappers for easily transitioning from JavaFX to RxJava
 */
@SuppressWarnings({"UnusedDeclaration", "SpellCheckingInspection"})
public class FXRX {
    public static <T extends Event> Observable<T> fromNode(EventType<T> eventType, Node node) {
        return Observable.create((Observable.OnSubscribe<T>) s -> node.addEventHandler(eventType, s::onNext));
    }
    
    public static <T> Observable<T> fromObservableValue(ObservableValue<T> observableValue) {
        return Observable.create((Observable.OnSubscribe<T>) s -> {
            observableValue.addListener((obs, oldValue, newValue) -> s.onNext(newValue));
        });
    }
}
