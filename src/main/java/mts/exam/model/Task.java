package mts.exam.model;

public class Task {

    String owner;
    String name;
    State state;

    public Task(final String owner, final String name) {
        this.owner = owner;
        this.name = name;
        this.state = State.CREATED;
    }

    public String getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public void setState(final State state) {
        this.state = state;
    }
}
