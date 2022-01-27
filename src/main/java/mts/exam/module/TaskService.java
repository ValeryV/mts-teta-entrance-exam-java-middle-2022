package mts.exam.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskService {

    private static final Logger LOG = LoggerFactory.getLogger(TaskService.class);
    private final TaskDao taskDao = new TaskDao();

    public String lineTranslate(String line) {

        String[] request = line.split(" ");
        if (request.length != 3)
            return Result.WRONG_FORMAT.name();
        String user = request[0];
        String command = request[1];
        String arg = request[2];

        try {
            LOG.debug("User " + user + " do command " + command);
            return commandRun(user, command, arg);
        } catch (AuthorizeException e) {
            LOG.error("no access", e);
            return Result.ACCESS_DENIED.name();
        }
    }

    private String commandRun(String user, String command, String arg) throws AuthorizeException {
        switch (Command.valueOf(command)) {
            case CREATE_TASK: {
                if (taskDao.create(user, arg))
                    return Result.CREATED.name();
                else
                    return Result.ERROR.name();
            }
            case CLOSE_TASK: {
                if (taskDao.close(user, arg))
                    return Result.CLOSED.name();
                else
                    return Result.ERROR.name();
            }
            case REOPEN_TASK: {
                if (taskDao.reopen(user, arg))
                    return Result.REOPENED.name();
                else
                    return Result.ERROR.name();
            }
            case DELETE_TASK: {
                if (taskDao.delete(user, arg))
                    return Result.DELETED.name();
                else
                    return Result.ERROR.name();
            }
            case LIST_TASK: {
                return Result.TASKS.name() + " [" + String.join(", ", taskDao.findAll(arg)) + "]";
            }
            default:
                return Result.ERROR.name();
        }
    }
}
