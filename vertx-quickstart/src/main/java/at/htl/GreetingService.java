package at.htl;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import io.vertx.core.eventbus.Message;
import org.eclipse.microprofile.context.ManagedExecutor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class GreetingService {

    @Inject
    ManagedExecutor executor;

    /* Completion Stage => Async
    @ConsumeEvent
    public CompletionStage<String> consume(String name) {
        //return name.toUpperCase();
    }*/

    /* Fire and Forget
    @ConsumeEvent
    public void consume(String name) {

    }*/


    // set address to greetingSync
    // blocking
    @ConsumeEvent("greetingSync")
    public String consume(String name) {
        return name.toUpperCase();
    }

    // set address to greetingAsync
    // non-blocking
    @ConsumeEvent("greetingAsync")
    public Uni<String> consume2(String name) {
        return Uni.createFrom().item(name::toUpperCase).emitOn(executor);
    }

    // Fire and Forget
    @ConsumeEvent("greetingMessage")
    public void consume(Message<String> msg) {
        System.out.println(msg.address());
        System.out.println(msg.body());
    }

    @ConsumeEvent(value = "blocking-consumer")
    @Blocking
    void consumeBlocking(String message) {
        // Something blocking
    }

}
