package pl.pogaduchy;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * Unit test for simple App.
 */
public class CreateActorByOfTest {

        public static void main(String[] args) {
            // Utworzenie systemu aktorów
            final ActorSystem system = ActorSystem.create("MySystem");

            // Utworzenie aktora
            final ActorRef myActor = system.actorOf(MyClassicActor.props(), "myActor");

            // Wysłanie wiadomości do aktora
            // noSender - wysłanie wiadomości bez zwrotki do nadawcy
            myActor.tell("Hello, World!", ActorRef.noSender());
            myActor.tell(new MyClassicActor.SumEquation(3, 5), ActorRef.noSender());
        }


}