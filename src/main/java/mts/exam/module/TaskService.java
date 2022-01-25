package mts.exam.module;


public class TaskService {

    TaskDao taskDao = new TaskDao();

    public String lineTranslate(String line) {
        String[] request = line.split(" ");
        String user = request[0];
        String command = request[1];
        String arg = request[2];
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
                return Result.TASKS.name() + "[" + String.join(", ", taskDao.findAll(user)) + "]";
            }
            default: return Result.ERROR.name();
        }
    }

}
