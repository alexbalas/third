package org.example;

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
import validation.ValidationException;

import java.util.UUID;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    //BBT folder
            //lab2
    private Service service;


    @Test
    public void addStudentCorrectGroup() {
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
        Student student = new Student(UUID.randomUUID().toString(), "Mama", 931, "hey.alexbalas@gmail.com");
        Student result = service.addStudent(student);
        assert result.getGrupa() == student.getGrupa();
    }

    @Test
    public void addStudentIncorrectGroup() {
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
        Student student = new Student(UUID.randomUUID().toString(), "Mama", -931, "hey.alexbalas@gmail.com");
        int studentCount = 0;
        for (Student value : studentXMLRepository.findAll()) {
            studentCount++;
        }
        try {
            service.addStudent(student);
        } catch (ValidationException e) {
            int studentCountResult = 0;
            for (Student value : studentXMLRepository.findAll()) {
                studentCountResult++;
            }
            assert studentCountResult == studentCount;
        }
    }

    @Test
    public void addAssignmentCorrectDeadline() {
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

        Tema tema = new Tema(UUID.randomUUID().toString(),"Tema1", 14, 10);
        int assCount = 0;
        for (Tema value : temaXMLRepository.findAll()) {
            assCount++;
        }
        service.addTema(tema);
        int assCountResult = 0;
        for (Tema value : temaXMLRepository.findAll()) {
           assCountResult++;
        }
        assert assCount + 1 == assCountResult;
        service.deleteNota("1292512322");

    }

    @Test
    public void addAssignmentIncorrectDeadline() {
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

        Tema tema = new Tema(UUID.randomUUID().toString(),"Tema2", 19, 10);
        int assCount = 0;
        for (Tema ignored : temaXMLRepository.findAll()) {
            assCount++;
        }
        try {
            service.addTema(tema);
        }
        catch (ValidationException e) {
            int assCountResult = 0;
            for (Tema ignored : temaXMLRepository.findAll()) {
                assCountResult++;
            }
            assert assCount == assCountResult;
            service.deleteNota("12923i2");
        }
    }


    @Test
    public void addValidStudentRedundant() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student(UUID.randomUUID().toString(), "Balas Alexandru", 931, "hey.alexbalas@gmail.com");
        Student res = service.addStudent(student);
        assert student == res;
    }

    @Test
    public void addValidStudent() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

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
    public void addStudentEmptyId() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student("", "Balas Alexandru", 931, "hey.alexbalas@gmail.com");
        try {
            service.addStudent(student);
            assert false;
        }
        catch (ValidationException e) {
            assert e.getMessage().equals("Id incorect!");
        }
    }

    @Test
    public void addStudentNullId() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student(null, "Balas Alexandru", 931, "hey.alexbalas@gmail.com");
        try {
            service.addStudent(student);
            assert false;
        }
        catch (ValidationException e) {
            assert e.getMessage().equals("Id incorect!");
        }
    }

    @Test
    public void addStudentNotUnique() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student("1", "Balas Alexandru", 931, "hey.alexbalas@gmail.com");
        try {
            service.addStudent(student);
            assert false;
        }
        catch (ValidationException e) {
            assert e.getMessage().equals("Id not unique");
        }
    }

    @Test
    public void addStudentEmptyName() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student(UUID.randomUUID().toString(), "", 931, "hey.alexbalas@gmail.com");
        try {
            service.addStudent(student);
            assert false;
        }
        catch (ValidationException e) {
            assert e.getMessage().equals("Nume incorect!");
        }
    }

    @Test
    public void addStudentNullName() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student(UUID.randomUUID().toString(), null, 931, "hey.alexbalas@gmail.com");
        try {
            service.addStudent(student);
            assert false;
        }
        catch (ValidationException e) {
            assert e.getMessage().equals("Nume incorect!");
        }
    }

    @Test
    public void addStudentNullEmail() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student(UUID.randomUUID().toString(), "Balas Alexandru", 931, null);
        try {
            service.addStudent(student);
            assert false;
        }
        catch (ValidationException e) {
            assert e.getMessage().equals("Email incorect!");
        }
    }

    @Test
    public void addStudentEmptyEmail() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student(UUID.randomUUID().toString(), "Balas Alexandru", 931, "");
        try {
            service.addStudent(student);
            assert false;
        }
        catch (ValidationException e) {
            assert e.getMessage().equals("Email incorect!");
        }
    }

    @Test
    public void addStudentInvalidEmail() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student(UUID.randomUUID().toString(), "Balas Alexandru", 931, "invalid.com");
        try {
            service.addStudent(student);
            assert false;
        }
        catch (ValidationException e) {
            assert e.getMessage().equals("Email is of incorrect form!");
        }
    }

    @Test
    public void addStudentValidIdLengthLowerLimit() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        String id = UUID.randomUUID().toString() + "123";
        Student student = new Student(id, "Balas Alexandru", 931, "hey.alexbalas@gmail.com");
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
    public void addStudentValidIdLengthFix() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        String id = UUID.randomUUID().toString() + "1234";
        Student student = new Student(id, "Balas Alexandru", 931, "hey.alexbalas@gmail.com");
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
    public void addStudentInvalidIdLengthUpper() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        String id = UUID.randomUUID().toString() + "12345";
        Student student = new Student(id, "Balas Alexandru", 931, "hey.alexbalas@gmail.com");
        try {
            service.addStudent(student);
            assert false;
        }
        catch (ValidationException e) {
            assert e.getMessage().equals("Id incorect!");
        }
    }

    @Test
    public void addStudentInvalidNameNumbers() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        String id = UUID.randomUUID().toString();
        Student student = new Student(id, "Balas Alexandru 12", 931, "hey.alexbalas@gmail.com");
        try {
            service.addStudent(student);
            assert false;
        }
        catch (ValidationException e) {
            assert e.getMessage().equals("Nume incorect!");
        }
    }

    @Test
    public void addStudentInvalidNameCharacters() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        String id = UUID.randomUUID().toString();
        Student student = new Student(id, "Alex @#^!", 931, "hey.alexbalas@gmail.com");
        try {
            service.addStudent(student);
            assert false;
        }
        catch (ValidationException e) {
            assert e.getMessage().equals("Nume incorect!");
        }
    }
}
