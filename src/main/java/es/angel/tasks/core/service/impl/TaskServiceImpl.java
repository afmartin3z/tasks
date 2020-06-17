package es.angel.tasks.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.angel.tasks.core.entity.Task;
import es.angel.tasks.core.repository.TaskRepository;
import es.angel.tasks.core.service.TaskService;

/**
 * The Class TaskServiceImpl.
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    /** The log. */
    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    /** The task repository. */
    @Autowired
    private TaskRepository taskRepository;

    /*
     * (non-Javadoc)
     *
     * @see es.angel.tasks.core.service.TaskService#findAll()
     */
    @Override
    public List<Task> findAll() {
        log.debug("Request to find all Tasks");
        return taskRepository.findAll();
    }

    /*
     * (non-Javadoc)
     *
     * @see es.angel.tasks.core.service.TaskService#findById(java.lang.Long)
     */
    @Override
    public Optional<Task> findById(Long id) {
        log.debug("Request to find Task with id: {}", id);
        return taskRepository.findById(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see es.angel.tasks.core.service.TaskService#delete(java.lang.Long)
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Task with id: {}", id);
        taskRepository.deleteById(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * es.angel.tasks.core.service.TaskService#create(es.angel.tasks.core.entity.
     * Task)
     */
    @Override
    public Task create(Task task) {
        log.debug("Request to create Task : {}", task);
        return taskRepository.saveAndFlush(task);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * es.angel.tasks.core.service.TaskService#update(es.angel.tasks.core.entity.
     * Task)
     */
    @Override
    public Task update(Task task) {
        log.debug("Request to update Task : {}", task);

        Task oldTask = taskRepository.findById(task.getId()).orElse(null);
        Task updatedTask = null;

        if (oldTask != null) {
            updatedTask = taskRepository.saveAndFlush(task);
        }

        return updatedTask;
    }

}
