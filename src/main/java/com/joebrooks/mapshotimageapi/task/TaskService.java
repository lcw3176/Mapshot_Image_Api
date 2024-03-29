package com.joebrooks.mapshotimageapi.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskQueue taskQueue;

    public void addTask(TaskRequest taskRequest){
        taskQueue.add(taskRequest);
    }

    public boolean isTaskEmpty(){
        return taskQueue.isEmpty();
    }

    public TaskRequest getUserRequestTask(){
        return taskQueue.poll();
    }
}
