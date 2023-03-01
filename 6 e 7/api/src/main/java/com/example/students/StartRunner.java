package com.example.students;

import com.example.students.model.Student;
import com.example.students.model.Test;
import com.example.students.repository.StudentRepository;
import com.example.students.repository.TestRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@Component
@AllArgsConstructor
public class StartRunner implements ApplicationRunner {

	public String firstMenu(){
		System.out.println("\n");
		System.out.println("1. Cadastrar aluno");
		System.out.println("2. Listar alunos");
		System.out.println("3. Ver maior nota");
		System.out.println("S. Sair");
		Scanner keyboard = new Scanner(System.in);
		return keyboard.nextLine();
	}

	public void registerStudent(){
		Student student = new Student();
		Scanner keyboard = new Scanner(System.in);
		String name = keyboard.nextLine();
		student.setName(name);
		studentRepository.save(student);
	}

	public void showStudentInfos(Student selected_student){
		String average_grade = testRepository.gradeAverageByStudentId(selected_student.getId()) != null ? Double.toString(testRepository.gradeAverageByStudentId(selected_student.getId())) : "Não há dados";
		String max_grade = testRepository.maximunGradeByStudentId(selected_student.getId()) != null ? Double.toString(testRepository.maximunGradeByStudentId(selected_student.getId())) : "Não há dados";
		String min_grade = testRepository.minimunGradeByStudentId(selected_student.getId()) != null ? Double.toString(testRepository.minimunGradeByStudentId(selected_student.getId())) : "Não há dados";

		System.out.println("\n");

		System.out.println("Aluno: "+selected_student.getName());
		System.out.println("Média: "+average_grade);
		System.out.println("Maior nota: "+max_grade);
		System.out.println("Menor nota: "+min_grade);

		System.out.println("\n");

		System.out.println("1. Cadastrar prova para este aluno");

		System.out.println("\n");

		System.out.println("V. Voltar");
		System.out.println("S. Sair");
		Scanner keyboard = new Scanner(System.in);
		switch(keyboard.nextLine()) {
			case "1":
				registerStudentTest(selected_student);
				break;
			case "V":
				listStudents();
				break;
			case "S":
				break;
			default:
				// code block
		}
	}

	public void registerStudentTest(Student student){
		Scanner keyboard = new Scanner(System.in);
		Float grade = keyboard.nextFloat();
		Test test = new Test(grade);
		test.setStudent(student);
		testRepository.save(test);
	}
	public void listStudents(){
		System.out.println("\n");
		AtomicInteger count = new AtomicInteger();
		List<Student> students = studentRepository.findAll();
		System.out.println("Alunos:");
		students.forEach(student1 -> {
			System.out.println(count+". "+student1.getName());
			count.addAndGet(1);
		});
		System.out.println("\n");
		System.out.println("V. Voltar");
		System.out.println("S. Sair");
		Scanner keyboard = new Scanner(System.in);
		try {
			int student_idx = Integer.parseInt(keyboard.nextLine());
			showStudentInfos(students.get(student_idx));
		}catch (NumberFormatException ex) {

		}
	}

	public void showMaximunGrade(){
		System.out.println("\n");
		System.out.println("Nota máxima: "+testRepository.maximunGrade());
	}
    @Autowired
    StudentRepository studentRepository;

	@Autowired
	TestRepository testRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
		/*String option = firstMenu();
		while (!option.equals("S")){
			switch(option) {
				case "1":
					registerStudent();
					break;
				case "2":
					listStudents();
					break;
				case "3":
					showMaximunGrade();
					break;
				default:
					// code block
			}
			option = firstMenu();
		}*/
    }
}