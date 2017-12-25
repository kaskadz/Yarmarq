package com.yarmarq.task;

import java.util.LinkedList;
import java.util.List;

public class TaskManager {
    private List<ITask> taskList = new LinkedList<>();

    public void addTask(ITask task) {
        taskList.add(task);
    }

    public void addTaskAndAcomplishAll(ITask task) {
        addTask(task);
        acomplishTasks();
    }

    public void acomplishTasks() {
        taskList.forEach(ITask::accomplish);
    }
}
