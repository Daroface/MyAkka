package pl.pogaduchy;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

// definiowanie Actorów przez AbstractActor do Akka 2.6; Actorzy bez typów (klasyczni), z ActorAPI
public class MyClassicActor extends AbstractActor {

    // Logger dla aktora
    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    // Tworzenie Props dla aktora
    public static Props props() {
        return Props.create(MyClassicActor.class, () -> new MyClassicActor());
    }

    // Obsługa wiadomości
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, message -> {
                    log.info(this.getContext().getSelf().toString() + " - Otrzymano wiadomość: {}", message);
                })
                .match(SumEquation.class, message -> {
                    int sum = (message.a + message.b);
                    log.info("Wynik dodawania: " + sum);
                })
                .build();
    }

    public static class SumEquation {
        int a;
        int b;

        public SumEquation(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}