package com.mipt.pavelstarosvitskiy;

import com.mipt.pavelstarosvitskiy.model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateAndGetTask() {
        Task newTask = new Task(null, "Test Task", "Test Description", false);
        ResponseEntity<Task> createResponse = restTemplate.postForEntity("/api/tasks", newTask, Task.class);

        Assertions.assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        Task createdTask = createResponse.getBody();
        Assertions.assertNotNull(createdTask);
        Assertions.assertNotNull(createdTask.getId());

        ResponseEntity<Task> getResponse = restTemplate.getForEntity("/api/tasks/" + createdTask.getId(), Task.class);
        Assertions.assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        Assertions.assertEquals("Test Task", getResponse.getBody().getTitle());
    }

    @Test
    void testGetAllTasks() {
        ResponseEntity<Task[]> response = restTemplate.getForEntity("/api/tasks", Task[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertTrue(response.getBody().length > 0);
    }


    @Test
    void testGetNonExistentTask() {
        String randomId = "non-existent-id-999";
        ResponseEntity<Task> response = restTemplate.getForEntity("/api/tasks/" + randomId, Task.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteTask() {
        Task task = new Task("delete-me", "Delete task", "Sample desc", false);
        restTemplate.postForEntity("/api/tasks", task, Task.class);

        restTemplate.delete("/api/tasks/delete-me");

        ResponseEntity<Task> checkResponse = restTemplate.getForEntity("/api/tasks/delete-me", Task.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, checkResponse.getStatusCode());
    }
}
