package com.example.data

import java.util.*

class DataSource {

    companion object {
        fun createTaskList(): ArrayList<Task> {
            val tasks = ArrayList<Task>()
            tasks.add(Task("One", true, 3))
            tasks.add(Task("Two", false, 2))
            tasks.add(Task("Three", true, 1))

            return tasks
        }
    }


}