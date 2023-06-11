package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DbService dbService;
    @MockBean
    private TaskMapper taskMapper;

    @Test
    void testGetTasks() throws Exception {
        //Given
        TaskDto taskDto1 = new TaskDto(1L, "Test_title1", "Test_content1");
        TaskDto taskDto2 = new TaskDto(2L, "Test_title2", "Test_content2");
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(taskDto1);
        taskDtoList.add(taskDto2);

        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test_title1")))
                .andExpect(jsonPath("$[0].content", is("Test_content1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("Test_title2")))
                .andExpect(jsonPath("$[1].content", is("Test_content2")));
    }

    @Test
    void testGetTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test_title1", "Test_content1");
        Task task = new Task(1L, "Test_title1", "Test_content1");

        when(dbService.getTask(any())).thenReturn(task);
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test_title1")))
                .andExpect(jsonPath("$.content", is("Test_content1")));
    }

    @Test
    void testDeleteTask() throws Exception {
        // Given
        Task task = new Task(1L, "Test_title1", "Test_content1");

        when(dbService.getTask(task.getId())).thenReturn(task);

        //When & Then
        mockMvc.perform(delete("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateTask() throws Exception {
        // Given
        Task task = new Task(1L, "task", "content");
        Task updatedTask = new Task(2L, "updatedTask", "updatedContent");
        TaskDto taskDto = new TaskDto(1L, "task", "content");
        TaskDto updatedTaskDto = new TaskDto(2L, "updatedTask", "updatedContent");

        when(dbService.saveTask(task)).thenReturn(updatedTask);
        when(taskMapper.mapToTaskDto(any())).thenReturn(updatedTaskDto);
        when(taskMapper.mapToTask(any())).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.title", is("updatedTask")))
                .andExpect(jsonPath("$.content", is("updatedContent")))
                .andExpect(status().is(200));
    }

    @Test
    void testCreateTask() throws Exception {
        Task task = new Task(1L, "task", "content");
        TaskDto taskDto = new TaskDto(1L, "task", "content");

        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTask(any())).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        mockMvc.perform(post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                 .andExpect(status().isOk());
    }
}
