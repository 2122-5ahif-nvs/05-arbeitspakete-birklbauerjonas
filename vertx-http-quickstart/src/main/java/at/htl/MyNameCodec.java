package at.htl;

import io.vertx.core.eventbus.MessageCodec;

public abstract class MyNameCodec {

    private String name;

    public MyNameCodec(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
