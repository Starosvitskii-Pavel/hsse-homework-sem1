package com.mipt.pavelstarosvitskiy;

import com.mipt.pavelstarosvitskiy.model.TaskEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskEntityControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateAndGetTask() {
        TaskEntity newTask = new TaskEntity(null, "Test Task", "Test Description", false);
        ResponseEntity<TaskEntity> createResponse = restTemplate.postForEntity("/api/tasks", newTask, TaskEntity.class);

        Assertions.assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        TaskEntity createdTask = createResponse.getBody();
        Assertions.assertNotNull(createdTask);
        Assertions.assertNotNull(createdTask.getId());

        ResponseEntity<TaskEntity> getResponse = restTemplate.getForEntity("/api/tasks/" + createdTask.getId(), TaskEntity.class);
        Assertions.assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        Assertions.assertEquals("Test Task", getResponse.getBody().getTitle());
    }

    @Test
    void testGetAllTasks() {
        ResponseEntity<TaskEntity[]> response = restTemplate.getForEntity("/api/tasks", TaskEntity[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertTrue(response.getBody().length > 0);
    }


    @Test
    void testGetNonExistentTask() {
        String randomId = "non-existent-id-999";
        ResponseEntity<TaskEntity> response = restTemplate.getForEntity("/api/tasks/" + randomId, TaskEntity.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteTask() {
        TaskEntity task = new TaskEntity("delete-me", "Delete task", "Sample desc", false);
        restTemplate.postForEntity("/api/tasks", task, TaskEntity.class);

        restTemplate.delete("/api/tasks/delete-me");

        ResponseEntity<TaskEntity> checkResponse = restTemplate.getForEntity("/api/tasks/delete-me", TaskEntity.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, checkResponse.getStatusCode());
    }
}
