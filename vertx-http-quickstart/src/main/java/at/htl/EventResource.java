package at.htl;


import io.smallrye.mutiny.Uni;
import io.vertx.core.eventbus.Message;
import io.vertx.mutiny.core.eventbus.EventBus;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/async")
public class EventResource {

    @Inject
    EventBus bus;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{name}")
    public Uni<String> greeting(@PathParam(value = "name") String name) {
        return bus.<String>request("greeting", name)
                .onItem().transform(Message::body);
    }
}
