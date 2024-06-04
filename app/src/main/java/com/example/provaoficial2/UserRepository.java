package com.example.provaoficial2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
    }

    LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    void insert(User user) {
        databaseWriteExecutor.execute(() -> userDao.insert(user));
    }

    void delete(User user) {
        databaseWriteExecutor.execute(() -> userDao.delete(user));
    }

    void deleteAll() {
        databaseWriteExecutor.execute(userDao::deleteAll);
    }
}
