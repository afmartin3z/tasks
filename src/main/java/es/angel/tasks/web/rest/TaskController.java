package es.angel.tasks.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import es.angel.tasks.core.entity.Task;
import es.angel.tasks.core.service.TaskService;
import es.angel.tasks.core.service.util.BeanMapper;
import es.angel.tasks.web.domain.TaskDTO;
import es.angel.tasks.web.util.HeaderUtil;

/**
 * The Class TaskController.
 */
@Controller
@RequestMapping("/api/task")
public class TaskController {

    /** The log. */
    private final Logger log = LoggerFactory.getLogger(TaskController.class);

    /** The Constant TASK_ENTITY_NAME. */
    private static final String TASK_ENTITY_NAME = "task";

    /** The Constant CREATE_TASK_ERROR. */
    private static final String CREATE_TASK_ERROR = "The task has not been created. ";

    /** The Constant UPDATE_TASK_ERROR. */
    private static final String UPDATE_TASK_ERROR = "The task has not been updated. ";

    /** The Constant DELETE_TASK_ERROR. */
    private static final String DELETE_TASK_ERROR = "The task has not been deleted. ";

    /** The task service. */
    @Autowired
    private TaskService taskService;

    /**
     * Gets the tasks.
     *
     * @return the tasks
     */
    @GetMapping()
    public ResponseEntity<List<TaskDTO>> getTasks() {
        log.info("Looking for all the tasks");
        List<Task> tasks = taskService.findAll();
        return ResponseEntity.ok().body(BeanMapper.transform(tasks, TaskDTO.class));
    }

    /**
     * Gets the task by id.
     *
     * @param id the id
     * @return the task by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        log.info("Look for task with id: {}", id);
        ResponseEntity<TaskDTO> response;
        Task task = taskService.findById(id).orElse(null);

        if (task != null) {
            response = ResponseEntity.ok().body(BeanMapper.transform(task, TaskDTO.class));
        } else {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    /**
     * Creates the task.
     *
     * @param taskDTO the task DTO
     * @return the response entity
     */
    @PostMapping()
    public ResponseEntity createTask(@Valid @RequestBody TaskDTO taskDTO) {
        log.info("Create task: {}", taskDTO);
        ResponseEntity response;
        Task task = null;
        if (taskDTO != null) {
            task = BeanMapper.transform(taskDTO, Task.class);
            if (taskDTO.getId() == null) {
                task = taskService.create(task);
                response = ResponseEntity.status(HttpStatus.CREATED)
                        .headers(HeaderUtil.createEntityCreationAlert(TASK_ENTITY_NAME, task.getId()))
                        .body(BeanMapper.transform(task, TaskDTO.class));
                log.info("Task created with data {}", task);

            } else {
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(CREATE_TASK_ERROR + "The id should be null.");
            }
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(CREATE_TASK_ERROR + "The task cannot be void");
        }

        return response;
    }

    /**
     * Update task.
     *
     * @param taskDTO the task DTO
     * @return the response entity
     */
    @PutMapping()
    public ResponseEntity updateTask(@Valid @RequestBody TaskDTO taskDTO) {
        log.info("Update task: {}", taskDTO);
        ResponseEntity response;
        Task task = null;

        if (taskDTO != null) {
            task = BeanMapper.transform(taskDTO, Task.class);

            if (taskDTO.getId() != null) {
                task = taskService.update(task);

                if (task == null) {
                    response = ResponseEntity.notFound().build();
                    log.info("The task has not been found: {}", taskDTO);
                } else {
                    response = ResponseEntity.status(HttpStatus.ACCEPTED)
                            .headers(HeaderUtil.createEntityUpdateAlert(TASK_ENTITY_NAME, task.getId()))
                            .body(BeanMapper.transform(task, TaskDTO.class));
                    log.info("Task updated with data: {}", task);
                }

            } else {
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(UPDATE_TASK_ERROR + "The id should not be null.");
            }
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(UPDATE_TASK_ERROR + "The task cannot be void");
        }

        return response;
    }

    /**
     * Delete task by id.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTaskById(@PathVariable Long id) {
        log.info("Delete task with id: {}", id);
        ResponseEntity response;
        taskService.delete(id);
        response = ResponseEntity.status(HttpStatus.NO_CONTENT).body(id);

        return response;
    }

}
