package pl.pogaduchy;


import akka.actor.typed.ActorSystem;

public class CreateActorByContextTest {

    public static void main(String[] args) {
        ActorSystem<MySayingActor.SaySomething> actorSystem = ActorSystem.create(MySayingActor.create(), "typedSystem");
        actorSystem.tell(new MySayingActor.SaySomething("dla pogaduchy"));
        ActorSystem<MyCalcingActor.CalcSomething> actorSystem2 = ActorSystem.create(MyCalcingActor.create(), "typedSystem2");

        actorSystem2.tell(new MyCalcingActor.CalcSomething(3, 4, actorSystem2.ignoreRef()));

    }
}
