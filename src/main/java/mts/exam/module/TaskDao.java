package mts.exam.module;

import mts.exam.model.State;
import mts.exam.model.Task;
import mts.exam.model.Tasks;

import java.util.List;
import java.util.stream.Collectors;

public class TaskDao {

    public boolean create(String owner, String name) {
        if (!Tasks.hasName(name)) {
            Task task = new Task(owner, name);
            Tasks.addTask(task);
            return true;
        } else {
            return false;
        }
    }

    public boolean close(String owner, String name) {
        Task task = Tasks.getTask(name);
        if (task.getOwner().equals(owner) && task.getState() == State.CREATED) {
            task.setState(State.CLOSED);
            return true;
        } else {
            return false;
        }
    }

    public boolean reopen(String owner, String name) {
        Task task = Tasks.getTask(name);
        if (task.getOwner().equals(owner) && task.getState() == State.CLOSED) {
            task.setState(State.CREATED);
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(String owner, String name) {
        Task task = Tasks.getTask(name);
        if (task.getOwner().equals(owner) && task.getState() == State.CLOSED) {
            task.setState(State.DELETED);
            Tasks.deleteName(name);
            return true;
        } else {
            return false;
        }
    }

    public List<String> findAll(String owner) {
        return Tasks.getTasks(owner).stream().map(Task::getName).collect(Collectors.toList());
    }
}
