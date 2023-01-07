package afc.diarymanager.model;

public class ProgressReport extends Entry{

    private Task parentTask;

    public ProgressReport(String desc, Task parentTask, boolean completedParentTask) {
        super(parentTask.getTitle(), desc);
        this.parentTask = parentTask;
        parentTask.addProgressReport(this);
        if (completedParentTask) parentTask.complete();
    }

    public Task getParentTask() {
        return parentTask;
    }

}
