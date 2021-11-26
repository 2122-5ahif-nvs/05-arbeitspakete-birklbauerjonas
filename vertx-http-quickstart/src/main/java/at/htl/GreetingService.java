package at.htl;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    @ConsumeEvent("greeting")
    public String greeting(String name) {
        return "Hello " + name;
    }

    @ConsumeEvent(value = "greetingCodec")
    public Uni<String> greeting(MyName name) {
        return Uni.createFrom().item(() -> "Hello " + name.getName() + " with codec");
    }

    /*@ConsumeEvent(value = "greetingCodecSet", codec = MyNameCodec.class)
    Uni<String> greetingCodecSet(MyName name) {
        return Uni.createFrom().item(() -> "Hello "+name.getName());
    }*/

}
