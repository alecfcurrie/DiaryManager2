package afc.diarymanager.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import afc.diarymanager.model.Diary;
import afc.diarymanager.model.Entry;
import afc.diarymanager.model.EventEntry;
import afc.diarymanager.model.ProgressReport;
import afc.diarymanager.model.Task;

public class TestDiary {

    Task task1 = new Task("Task1", LocalDate.of(2000, 1, 1));
    Task task2 = new Task("Task2", LocalDate.of(2000, 2, 1));
    Task task3_1 = new Task("Task3_1", LocalDate.of(2000, 2, 4));
    Task task3_2 = new Task("Task3_2", LocalDate.of(2000, 2, 4));
    Task task4 = new Task("Task4", LocalDate.of(2000, 4, 1));
    Task task5 = new Task("Task5", LocalDate.of(2000, 6, 1));

    ProgressReport completeTask1 = new ProgressReport("Completed task 1", task1, true);
    ProgressReport completeTask2 = new ProgressReport("Completed task 2", task2, true);
    ProgressReport workOnTask4 = new ProgressReport("Worked on task 4", task4, false);
    EventEntry eventEntry1 = new EventEntry("Act1", "Act1", null);
    EventEntry eventEntry2 = new EventEntry("Act2", "Act2", null);
    EventEntry eventEntry3 = new EventEntry("Act3", "Act3", null);

    Diary testDiary;

    @BeforeEach
    void setup() {
        testDiary = new Diary();
    }

    @Test
    void testConstructorOne() {
        assertEquals(0, testDiary.getToDoList().size());
        assertEquals(0, testDiary.getEntries().size());
    }

    @Test
    void testConstructorTwo() {
        List<Entry> entries = new ArrayList<>();
        entries.add(eventEntry1);
        entries.add(eventEntry2);
        entries.add(eventEntry3);
        entries.add(completeTask1);
        entries.add(completeTask2);
        List<Task> todoList = new ArrayList<>();
        todoList.add(task3_1);
        todoList.add(task3_2);
        todoList.add(task4);
        todoList.add(task5);
        List<Task> completedTasks = new ArrayList<>();
        completedTasks.add(task1);
        completedTasks.add(task2);
        Diary testDiaryTwo = new Diary(entries, todoList, completedTasks);
        assertEquals(todoList, testDiaryTwo.getToDoList());
        assertEquals(entries, testDiaryTwo.getEntries());
        assertEquals(completedTasks, testDiaryTwo.getCompletedTasks());
    }

    @Test
    void testAddEntry() {
        testDiary.addEntry(eventEntry1);
        List<Entry> entries = testDiary.getEntries();
        assertEquals(1, entries.size());
        assertTrue(entries.contains(eventEntry1));

        testDiary.addEntry(workOnTask4);
        testDiary.addEntry(completeTask1);
        entries = testDiary.getEntries();
        assertEquals(3,entries.size());
        assertEquals(completeTask1, entries.get(0));
        assertEquals(workOnTask4, entries.get(1));
        assertEquals(eventEntry1, entries.get(2));
    }

    @Test
    void testAddTask() {
        testDiary.addTask(task1);
        List<Task> taskList = testDiary.getToDoList();
        assertEquals(1, taskList.size());
        assertTrue(taskList.contains(task1));

        testDiary.addTask(task2);
        testDiary.addTask(task3_1);
        taskList = testDiary.getToDoList();
        assertEquals(3, taskList.size());
        assertEquals(task1, taskList.get(0));
        assertEquals(task2, taskList.get(1));
        assertEquals(task3_1, taskList.get(2));
    }

    @Test
    void testToDoOrdering1() {
        addTasksInOrder();
        assertTaskOrder(true);
    }

    private void addTasksInOrder() {
        testDiary.addTask(task1);
        testDiary.addTask(task2);
        testDiary.addTask(task3_1);
        testDiary.addTask(task3_2);
        testDiary.addTask(task4);
        testDiary.addTask(task5);
    }

    @Test
    void testToDoOrdering2() {
        testDiary.addTask(task2);
        testDiary.addTask(task5);
        testDiary.addTask(task3_2);
        testDiary.addTask(task1);
        testDiary.addTask(task4);
        testDiary.addTask(task3_1);
        assertTaskOrder(false);
    }

    @Test
    void testToDoOrdering3() {
        testDiary.addTask(task3_1);
        testDiary.addTask(task5);
        testDiary.addTask(task2);
        testDiary.addTask(task4);
        testDiary.addTask(task1);
        testDiary.addTask(task3_2);
        assertTaskOrder(true);
    }

    @Test
    void testToDoOrdering4() {
        testDiary.addTask(task5);
        testDiary.addTask(task4);
        testDiary.addTask(task3_2);
        testDiary.addTask(task3_1);
        testDiary.addTask(task2);
        testDiary.addTask(task1);
        assertTaskOrder(false);
    }

    /**
     * Asserts the ordering of tasks in the list.
     *
     * @param oneFirst true if task3_1 was added first, false otherwise.
     */
    private void assertTaskOrder(boolean oneFirst) {
        List<Task> taskList = testDiary.getToDoList();
        assertEquals(6, taskList.size());
        assertEquals(task1, taskList.get(0));
        assertEquals(task2, taskList.get(1));
        if (oneFirst) {
            assertEquals(task3_1, taskList.get(2));
            assertEquals(task3_2, taskList.get(3));
        } else {
            assertEquals(task3_1, taskList.get(3));
            assertEquals(task3_2, taskList.get(2));
        }
        assertEquals(task4, taskList.get(4));
        assertEquals(task5, taskList.get(5));
    }

    @Test
    void testRemovalOfCompletedTasks() {
        addTasksInOrder();
        testDiary.addEntry(completeTask1);
        testDiary.addEntry(completeTask2);
        testDiary.addEntry(workOnTask4); //Should have no impact
        testDiary.addEntry(eventEntry1); //Should have no impact
        List<Task> todoList = testDiary.getToDoList();
        assertTrue(todoList.contains(task3_1));
        assertTrue(todoList.contains(task3_2));
        assertTrue(todoList.contains(task4));
        assertTrue(todoList.contains(task5));
        assertFalse(todoList.contains(task1));
        assertFalse(todoList.contains(task2));
        List<Task> completedList = testDiary.getCompletedTasks();
        assertFalse(completedList.contains(task3_1));
        assertFalse(completedList.contains(task3_2));
        assertFalse(completedList.contains(task4));
        assertFalse(completedList.contains(task5));
        assertTrue(completedList.contains(task1));
        assertTrue(completedList.contains(task2));
    }

    @Test
    void testClearCompletedTasks() {
        addTasksInOrder();
        testDiary.addEntry(completeTask1);
        testDiary.clearCompletedTasks();
        assertEquals(0, testDiary.getCompletedTasks().size());
    }
}
