package afc.diarymanager.model;

import java.time.LocalDate;
import java.util.*;

public class Diary {

    List<Task> toDoList;
    List<Task> completedTasks;
    List<Entry> entries;

    public Diary(List<Entry> entries, List<Task> toDoList, List<Task> completedTasks) {
        this.toDoList = toDoList;
        this.entries = entries;
        this.completedTasks = completedTasks;
    }

    public Diary() {
        this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public void addEntry(Entry e) {
        entries.add(0, e);
        if (e instanceof ProgressReport) {
            checkAndRemoveCompletedTask((ProgressReport) e);
        }
    }

    public void addTask(Task t) throws IllegalArgumentException {
        if (toDoList.contains(t)) {
            throw new IllegalArgumentException("Duplicate tasks cannot be added to the Todo List");
        } else {
            int index = 0;
            LocalDate taskDueDate = t.getDueDate();
            while (index < toDoList.size()) {
                if (toDoList.get(index).compareTo(taskDueDate) <= 0) {
                    index++;
                } else {
                    toDoList.add(index, t);
                    return;
                }
            }
            toDoList.add(t);
        }
    }

    private void checkAndRemoveCompletedTask(ProgressReport progressReport) {
        Task parentTask = progressReport.getParentTask();
        if (parentTask.isComplete()) {
            completedTasks.add(parentTask);
            toDoList.remove(parentTask);
        }
    }



    public List<Task> getToDoList() {
        return toDoList;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public List<Task> getCompletedTasks() {
        return completedTasks;
    }

    public void clearCompletedTasks() {
        completedTasks = new ArrayList<>();
    }
}
