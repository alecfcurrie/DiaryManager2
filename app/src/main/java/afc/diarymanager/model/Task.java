package afc.diarymanager.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task implements Comparable<LocalDate> {

    private String title;
    private boolean complete;
    private List<ProgressReport> progressReports;
    private LocalDate dueDate;

    public String getTitle() {
        return title;
    }

    public Task(String title, LocalDate dueDate) {
        this.title = title;
        complete = false;
        progressReports = new ArrayList<>();
        this.dueDate = dueDate;
    }

    public void complete() {
        complete = true;
    }

    public void addProgressReport(ProgressReport pr) {
        progressReports.add(0, pr);
    }

    public boolean isComplete() {
        return complete;
    }

    public List<ProgressReport> getProgressReports() {
        return progressReports;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isOverdue() {
        return dueDate.isBefore(LocalDate.now());
    }

    @Override
    public int compareTo(LocalDate o) {
        if (dueDate.isBefore(o)) {
            return -1;
        } else if (dueDate.isAfter(o)) {
            return 1;
        } else {
            return 0;
        }
    }
}