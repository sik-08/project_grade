import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean run = true;
        boolean prof_run = false;
        boolean stud_run = false;
        String data;
        Professor professor = null;
        Student student = null;
        ArrayList<Student> students = new ArrayList<>();
        // 메뉴 선택 번호
        int select = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader input = new BufferedReader(new FileReader("src\\DATA.txt"));
        while((data = input.readLine()) != null){
            student = Student.insertData(new StringTokenizer(data, ","));
            students.add(student);
        }
        student = null;

        System.out.println("[국립강릉원주대학교 성적 산출 프로그램]\n");
        printNotice();
        try{
            for(int i = 0; i < 3; i++){
                Thread.sleep(1000);
                System.out.println(".");
            }
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }

        System.out.println("사용자 정보를 선택하여 주십시오.");
        System.out.println("[1] 교직원 [2] 학생");
        System.out.println("-> ");
        select = Integer.parseInt(br.readLine());

        switch(select){
            case 1:
                System.out.println("* 교직원 접근 권한 필요 *");
                System.out.println("ACCESS KEY 입력 : ");
                if(Professor.access(br.readLine()) == 0){
                    System.out.println("[경고] 유효하지 않은 ACCESS KEY");
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(1);
                }
                else
                    System.out.println("\n* 접속 성공 *\n");

                professor = new Professor();
                prof_run = true;

                if(students.size() == 0){
                    System.out.println("관리할 학생 정보가 없습니다.");
                    System.exit(0);
                }
                else{

                }
            case 2:
                System.out.println("등록 이력이 있습니까?");
                System.out.println("[1] 예 [2] 아니오");
                System.out.print("-> ");
                if(Integer.parseInt(br.readLine()) == 2){
                    student = new Student(2)
                    students.add(student);
                }
                else{
                    System.out.println("이름을 입력해주세요.");
                    System.out.print("-> ");
                    if((student = searchStudent(students, br.readLine())) == null){
                        System.out.println("잘못된 접근입니다.");
                        System.out.println("프로그램을 종료합니다.");
                        System.exit(1);
                    }
                    System.out.println("*** " + student.getName() + "님 환영합니다. ***");
                }
                stud_run = true;
        }
        while(prof_run) {
            String studentName;

            System.out.println("메뉴를 선택해주세요.\n");
            System.out.println("[1] 학생 성적 입력\n"
                    + "[2] 학생 성적 수정\n"
                    + "[3] 프로그램 종료\n");
            select = Integer.parseInt(br.readLine());
            switch (select) {
                case 1:
                    printStudents(students);
                    System.out.println("성적을 입력할 학생의 이름을 입력해주세요.");
                    System.out.print("-> ");

                    studentName = br.readLine();
                    student = searchStudent(students, studentName);

                    if (student == null) {
                        System.out.println("잘못된 접근입니다.");
                        break;
                    }
                    System.out.println();

                    professor.update(student);
                    break;

                case 2:
                    printStudents(students);
                    System.out.println("성적을 수정할 학생의 이름을 입력해주세요.");
                    System.out.print("-> ");

                    studentName = br.readLine();
                    student = searchStudent(students, studentName);

                    if (student == null) {
                        System.out.println("잘못된 접근입니다.");
                        break;
                    }
                    System.out.println();

                    professor.modify(student);
                    break;

                case 3:
                    prof_run = false;
            }
        }
        while(stud_run){
            System.out.println("메뉴를 선택해주세요.\n");
            System.out.println("[1] 성적 조회\n"
                        + "[2] 프로그램 종료\n");

            select = Integer.parseInt(br.readLine());
            switch(select){
                case 1:
                    if(student.score)
                    ArrayList<Score> score = student.getScore();
            }
        }
    }

    public static void printNotice(){
        System.out.println("본 프로그램은 국립강릉원주대학교(원주캠퍼스) 컴퓨터공학과의 성적 관리 프로그램입니다.\n" +
                "부당한 방법으로 개인 성적의 위,변조를 시도할 경우 학칙에 의거하여 징계처리될 수 있습니다.");
    }

    public static void printStudents(ArrayList<Student> students){
        System.out.println("[학생 목록]");
        for(int i = 0; i < students.size(); i++){
            System.out.print(students.get(i).getName() +"  ");
            if(i % 5 == 0) System.out.println();
        }
        System.out.println();
    }

    public static Student searchStudent(ArrayList<Student> students, String name){
        for (Student student : students) {
            if (student.getName().equals(name))
                return student;
        }
        return null;
    }
}
