package es.angel.tasks.core.service;

import java.util.List;
import java.util.Optional;

import es.angel.tasks.core.entity.Task;

/**
 * The Interface TaskService.
 */
public interface TaskService {

    /**
     * Find all the tasks.
     *
     * @return the list
     */
    List<Task> findAll();

    /**
     * Creates the task.
     *
     * @param task the task
     * @return the task
     */
    Task create(Task task);

    /**
     * Update the task.
     *
     * @param task the task
     * @return the task
     */
    Task update(Task task);

    /**
     * Find the task by id.
     *
     * @param id the id
     * @return the optional
     */
    Optional<Task> findById(Long id);

    /**
     * Delete the task by id.
     *
     * @param id the id
     */
    void delete(Long id);

}
