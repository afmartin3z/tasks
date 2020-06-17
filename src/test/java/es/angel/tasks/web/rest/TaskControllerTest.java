package es.angel.tasks.web.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import es.angel.tasks.core.entity.Task;
import es.angel.tasks.core.service.TaskService;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TaskService taskService;

    List<Task> tasks;

    private final String URL = "/api/task/";

    @Before
    public void before() {
        tasks = buildTasks();
    }

    @Test
    public void testFindAllTasks() throws Exception {
        when(taskService.findAll()).thenReturn(tasks);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
        // verify status
        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

        // verify that service method was called once
        verify(taskService).findAll();

    }

    @Test
    public void testFindAOneTasks() throws Exception {
        Optional<Task> optional = Optional.of(tasks.get(0));
        when(taskService.findById(tasks.get(0).getId())).thenReturn(optional);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(URL + tasks.get(0).getId()).accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
        // verify status
        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

        // verify that service method was called once
        verify(taskService).findById(tasks.get(0).getId());

    }

    @Test
    public void testFindAOneTaskNotExist() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(URL + "{id}", tasks.get(0).getId()).accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
        // verify status
        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND.value(), status);

        // verify that service method was called once
        verify(taskService).findById(tasks.get(0).getId());

    }

    @Test
    public void testDeleteTask() throws Exception {

        // execute
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(URL + "{id}", new Long(1))).andReturn();

        // verify
        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT.value(), status);

        // verify that service method was called once
        verify(taskService).delete(any(Long.class));

    }

    private List<Task> buildTasks() {
        Task e1 = new Task();
        e1.setId(1L);
        e1.setName("first task");
        e1.setFinished(false);

        Task e2 = new Task();
        e2.setId(1L);
        e2.setName("second task");
        e2.setDescription("description of task");
        e2.setFinished(false);

        List<Task> taskList = Arrays.asList(e1, e2);

        return taskList;
    }

}
