package com.gestorlibreria.core;

import com.gestorlibreria.core.workerqueues.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorkergestorlibreriaApplication implements CommandLineRunner {

    @Autowired
    private Worker worker;

    public static void main(String[] args) {
        SpringApplication.run(WorkergestorlibreriaApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Iniciando Worker Gestor Librerias");
        worker.proccessWork();
    }
}
