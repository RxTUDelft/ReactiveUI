package org.rxtudelft.reactiveui;

import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.Scene;
import rx.Observable;
import rx.Observer;

/**
 * Wrappers for easily transitioning from JavaFX to RxJava
 */
@SuppressWarnings({"UnusedDeclaration", "SpellCheckingInspection"})
public class FXRX {
    public static <T extends Event> Observable<T> fromNode(EventType<T> eventType, Node node) {
        return Observable.create((Observable.OnSubscribe<T>) s -> node.addEventHandler(eventType, s::onNext));
    }

    public static <T extends Event> Observable<T> fromScene(EventType<T> eventType, Scene scene) {
        return Observable.create((Observable.OnSubscribe<T>) s -> scene.addEventHandler(eventType, s::onNext));
    }
    
    public static <T> Observable<T> fromObservableValue(ObservableValue<T> observableValue) {
        return Observable.create((Observable.OnSubscribe<T>) s -> {
            observableValue.addListener((obs, oldValue, newValue) -> s.onNext(newValue));
        });
    }

    public static <T extends Event> void subscribeNode(EventType<T> eventType, Node node, Observer<? super T> observer) {
        node.addEventHandler(eventType, observer::onNext);
    }

    public static <T extends Event> void subscribeScene(EventType<T> eventType, Scene scene, Observer<? super T> observer) {
        scene.addEventHandler(eventType, observer::onNext);
    }

    public static <T> void subscribeObservableValue(ObservableValue<T> observableValue, Observer<? super T> observer) {
        observableValue.addListener((obs, oldValue, newValue) -> observer.onNext(newValue));
    }
}
