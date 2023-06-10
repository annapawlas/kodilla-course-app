package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {

    private TaskMapper taskMapper = new TaskMapper();

    @Test
    public void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test", "test_content");
        Task task = new Task(1L, "test", "test_content");

        //When
        Task theTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals("test", theTask.getTitle());
    }

    @Test
    public void mapToTaskDtoTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test", "test_content");
        Task task = new Task(1L, "test", "test_content");

        //When
        TaskDto theTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals("test", theTaskDto.getTitle());
    }

    @Test
    public void mapToTaskDtoListTest() {
        //Given
        List<TaskDto> dtoList = new ArrayList<>();
        dtoList.add(new TaskDto(1L, "test", "test_content"));
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "test", "test_content"));

        //When
        List<TaskDto> theTaskDto = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(1, theTaskDto.size());
    }
}
