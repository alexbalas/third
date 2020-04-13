package org.example;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class IntegrationTesting {
//lab 4 folder




    @Test
    public void addValidAssignmentIntegration() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema(UUID.randomUUID().toString(), "Descriere valida", 13, 10);
        int assCount = 0;
        for (Tema ignored : service.getAllTeme()) {
            assCount++;
        }
        service.addTema(tema);

        int resultCount = 0;
        for (Tema ignored : service.getAllTeme()) {
            resultCount++;
        }
        assert resultCount == assCount + 1;
    }

    @Test
    public void addValidStudentIntegration() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student(UUID.randomUUID().toString(), "Balas Alexandru", 931, "hey.alexbalas@gmail.com");
        int studentCount = 0;
        for (Student ignored : service.getAllStudenti()) {
            studentCount++;
        }
        service.addStudent(student);

        int resultCount = 0;
        for (Student ignored : service.getAllStudenti()) {
            resultCount++;
        }
        assert resultCount == studentCount + 1;
    }

    @Test
    public void addValidGradeIntegration() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Nota nota = new Nota(UUID.randomUUID().toString(), "1002", "6", 8, LocalDate.of(2020, 4, 20));
        int gradeCount = 0;
        for (Nota ignored : service.getAllNote()) {
            gradeCount++;
        }

        service.addNota(nota, "feedback");

        int resultCount = 0;
        for (Nota ignored : service.getAllNote()) {
            resultCount++;
        }
        assert resultCount == gradeCount + 1;
    }

    @Test
    public void BigBangIntegration() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Tema tema = new Tema(UUID.randomUUID().toString(), "Descriere valida", 13, 10);
        int assCount = 0;
        for (Tema ignored : service.getAllTeme()) {
            assCount++;
        }

        Student student = new Student(UUID.randomUUID().toString(), "Balas Alexandru", 931, "hey.alexbalas@gmail.com");
        int studentCount = 0;
        for (Student ignored : service.getAllStudenti()) {
            studentCount++;
        }

        Nota nota = new Nota(UUID.randomUUID().toString(), student.getID(), tema.getID(), 8, LocalDate.of(2020, 4, 20));
        int gradeCount = 0;
        for (Nota ignored : service.getAllNote()) {
            gradeCount++;
        }

        service.addStudent(student);
        service.addTema(tema);
        service.addNota(nota, "early");

        long resultCountNota = 0;
        long resultCountStudent = 0;
        long resultCountTema = 0;

        for (Nota ignored : service.getAllNote()) {
            resultCountNota++;
        }
        for (Tema ignored : service.getAllTeme()) {
            resultCountTema++;
        }
        for (Student ignored : service.getAllStudenti()) {
            resultCountStudent++;
        }

        assert resultCountNota == gradeCount + 1;
        assert resultCountStudent == studentCount + 1;
        assert resultCountTema == assCount + 1;

    }
}
