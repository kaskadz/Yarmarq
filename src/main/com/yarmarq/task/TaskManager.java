package com.yarmarq.task;

import java.util.LinkedList;
import java.util.List;

/**
 * A class for managing tasks (implementation of command design pattern).
 */
public class TaskManager {
    /**
     * List of tasks, to accomplish.
     */
    private List<ITask> taskList = new LinkedList<>();

    /**
     * Adds a task to a task manager (task list).
     *
     * @param task task to add
     */
    public void addTask(ITask task) {
        taskList.add(task);
    }

    /**
     * Adds a task to a task manager (task list) and accomplishes all of them.
     *
     * @param task task to add
     */
    public void addTaskAndAccomplishAll(ITask task) {
        addTask(task);
        accomplishTasks();
    }

    /**
     * Accomplished all of the tasks in the task manager (task list).
     */
    public void accomplishTasks() {
        taskList.forEach(ITask::accomplish);
    }
}
