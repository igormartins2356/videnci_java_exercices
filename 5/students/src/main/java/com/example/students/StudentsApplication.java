package com.example.students;

import com.example.students.repository.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentsApplication {
	public static void main(String[] args) {
		SpringApplication.run(StudentsApplication.class, args);
		/*System.out.println("1. Cadastrar aluno");
		System.out.println("2. Listar alunos");
		System.out.println("3. Ver maior nota");
		System.out.println("S. Sair");*/
		/*Scanner teclado = new Scanner(System.in);
		String option = teclado.nextLine();
		switch(option) {
			case "1":
				Student student = new Student();
				student.setName("jorge");
				System.out.println(student);
				break;
			case "2":
				System.out.println("1");
				break;
			case "3":
				System.out.println("1");
				break;
			default:
				// code block
		}*/
	}

}
