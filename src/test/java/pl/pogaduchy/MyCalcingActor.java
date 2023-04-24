package pl.pogaduchy;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class MyCalcingActor extends AbstractBehavior<MyCalcingActor.CalcSomething> {


    static class CalcSomething {

        int a;

        int b;

        ActorRef<Result> replyTo;

        public CalcSomething(int a, int b, ActorRef<Result> replyTo) {
            this.a = a;
            this.b = b;
            this.replyTo = replyTo;
        }
    }

    // wiadomość wysyłana przez aktora w odpowiedzi na zapytanie Sum
    public static class Result {
        public final int sum;

        public Result(int sum) {
            this.sum = sum;
        }
    }

    // metoda tworząca zachowanie aktora
    public static Behavior<CalcSomething> create() {
        return Behaviors.setup(MyCalcingActor::new);
    }

    // konstruktor aktora
    private MyCalcingActor(ActorContext<CalcSomething> context) {
        super(context);
    }

    // metoda obsługująca wiadomości akceptowane przez aktora
    @Override
    public Receive<CalcSomething> createReceive() {
        return newReceiveBuilder()
                .onMessage(CalcSomething.class, this::onSum)
                .build();
    }

    // metoda obsługująca wiadomość CalcSomething
    private Behavior<CalcSomething> onSum(CalcSomething calcSomething) {
        int result = calcSomething.a + calcSomething.b;
        calcSomething.replyTo.tell(new Result(result));
        return this;
    }
}
