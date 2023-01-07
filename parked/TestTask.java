package afc.diarymanager.model;

//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import afc.diarymanager.model.ProgressReport;
import afc.diarymanager.model.Task;

public class TestTask {

    private static final String TITLE = "Task";
    private static final LocalDate DUE_DATE = LocalDate.of(3022, 6, 9);
    Task task;

    @BeforeEach
    void setup() {
        task = new Task(TITLE, DUE_DATE);
    }

    @Test
    void testConstructor() {
        assertEquals(TITLE, task.getTitle());
        assertEquals(DUE_DATE, task.getDueDate());
        assertFalse(task.isComplete());
    }

    @Test
    void testIsOverdue() {
        assertFalse(task.isOverdue());
        Task overdueTask = new Task("Overdue Task", LocalDate.of(2000, 5, 5));
        assertTrue(overdueTask.isOverdue());
    }

    @Test
    void testComplete() {
        task.complete();
        assertTrue(task.isComplete());
    }

    @Test
    void testAddProgressReport() {
        ProgressReport report1 = new ProgressReport("Report 1", task, false);
        ProgressReport report2 = new ProgressReport("Report 2", task, false);
        task.addProgressReport(report1);
        task.addProgressReport(report2);
        List<ProgressReport> reports = task.getProgressReports();
        assertEquals(report1, reports.get(1));
        assertEquals(report2, reports.get(0));
    }

}
