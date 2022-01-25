package mts.exam.model;

import java.util.*;
import java.util.stream.Collectors;

public class Tasks {

    private static Set<String> uniqTaskNames = new HashSet<>();

    private static Map<String, Set<String>> tasksByUser = new HashMap<>();

    private static Map<String, Task> tasks = new HashMap<>();

    public static boolean hasName(String name) {
        return uniqTaskNames.contains(name);
    }

    public static boolean deleteName(String name) {
        return uniqTaskNames.remove(name);
    }

    public static void addTask(Task task) {
        uniqTaskNames.add(task.getName());
        tasksByUser.computeIfAbsent(task.getOwner(), k -> new HashSet<>()).add(task.getName());
        tasks.put(task.getName(), task);
    }

    public static Task getTask(String name) {
        return tasks.get(name);
    }

    public static List<Task> getTasks(String owner) {
        return tasksByUser.getOrDefault(owner, new HashSet<>())
                .stream().map(s -> tasks.get(s)).filter(t->!t.getState().equals(State.DELETED))
                .collect(Collectors.toList());
    }

}
