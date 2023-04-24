package pl.pogaduchy;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class MySayingActor extends AbstractBehavior<MySayingActor.SaySomething> {

    static class SaySomething {

        String something;

        public SaySomething(String something) {
            this.something = something;
        }
    }
    public static Behavior<SaySomething> create() {
        return Behaviors.setup(MySayingActor::new);
    }

    private MySayingActor(ActorContext<SaySomething> context) {
        super(context);
    }

    @Override
    public Receive<SaySomething> createReceive() {
        return newReceiveBuilder().onMessage(SaySomething.class, (message) -> {
            System.out.println("Said something like '" + message.something + "'");
            return this;
        }).build();
    }
}
