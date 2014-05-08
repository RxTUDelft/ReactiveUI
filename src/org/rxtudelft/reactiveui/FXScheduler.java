package org.rxtudelft.reactiveui;

import javafx.application.Platform;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Created by ferdy on 5/8/14.
 */
public class FXScheduler extends Scheduler {

    public class FXWorker extends Worker {

        private Worker immediateWorker;

        public FXWorker() {
            this.immediateWorker = Schedulers.immediate().createWorker();
        }

        @Override
        public Subscription schedule(Action0 action) {
            Platform.runLater(() -> {
                action.call();
            });
            return null;
        }

        @Override
        public Subscription schedule(Action0 action, long l, TimeUnit timeUnit) {
            this.immediateWorker.schedule(() ->
            {
                Platform.runLater(() -> {
                    action.call();
                });
            }, l, timeUnit);
            return null;
        }

        @Override
        public void unsubscribe() {

        }

        @Override
        public boolean isUnsubscribed() {
            return false;
        }
    }

    @Override
    public Worker createWorker() {
        return new FXWorker();
    }
}
