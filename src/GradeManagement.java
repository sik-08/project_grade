import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class GradeManagement{
    private static ArrayList<Student> students = new ArrayList<>();
    public static boolean check;
    private static Professor professor = null;
    private static Student student = null;

    public static void startProgram() throws IOException{
        printNotice();
        loadData();

        String[] role = {"PROFESSOR", "STUDENT"};

        switch(role[GradeManagement.selectMenu("MAIN") - 1]){
            case "PROFESSOR":
                professor = Professor.accessProfessor();
                startProfessor();
                break;

            case "STUDENT":
                student = Student.accessStudent();
                startStudent();
                break;
        }

        outputData();
    }
    public static int selectMenu(String select) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        switch(select){
            case "MAIN":
                System.out.println("사용자 정보를 선택하여 주십시오.");
                System.out.println("[1] 교직원 [2] 학생");
                System.out.print("-> ");

            case "PROFESSOR":
                System.out.println("메뉴를 선택해주세요.\n");
                System.out.println("[1] 학생 성적 입력 및 변경");
                System.out.println("[2] 프로그램 종료");
                System.out.print("-> ");

            case "STUDENT":
                System.out.println("메뉴를 선택해주세요.\n");
                System.out.println("[1] 성적 조회");
                System.out.println("[2] 내 정보 보기");
                System.out.println("[3] 프로그램 종료");
                System.out.print("-> ");
        }

        return Integer.parseInt(br.readLine());
    }

    public static void startStudent() throws IOException{
        int select;

        String[] studentMenu = {"GRADE_CHECK", "INFO_CHECK"};

        while((select = selectMenu("STUDENT")) != 3){
            switch(studentMenu[select - 1]){
                case "GRADE_CHECK":
                    for(Score score : student.getScore()){
                        score.printScore();
                    }
                    break;

                case "INFO_CHECK":
                    student.printProfile();
                    break;
            }
        }
    }

    public static boolean checkStudents(){
        if (students.size() == 0) {
            System.out.println("\n현재 조회 가능한 학생 정보가 없습니다.");

            return false;
        }
        return true;
    }

    public static String setStudent() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("성적을 입력할 학생의 이름을 입력해주세요.");
        System.out.print("-> ");

        return br.readLine();
    }

    public static void startProfessor() throws IOException{
        if(!checkStudents()) return;
        while(selectMenu("PROFESSOR") != 2) {
            printStudents();

            student = Objects.requireNonNull(searchStudent(setStudent()), "존재하지 않는 학생입니다.");

            professor.update(student);
            check = true;
        }
    }

    public static void loadData() throws IOException{
        String data;
        Student student;

        try{
            BufferedReader input = new BufferedReader(new FileReader("src\\DATA.txt"));

            while((data = input.readLine()) != null){
                student = inputData(new StringTokenizer(data, ","));
                students.add(student);
            }
        }catch(FileNotFoundException e){
            System.out.println("\n불러올 파일이 존재하지 않습니다. 파일을 새로 생성합니다.\n");
            BufferedWriter bw = new BufferedWriter(new FileWriter("src\\DATA.txt"));
            bw.newLine();

            students = new ArrayList<>();
        }
    }

    public static void printNotice(){
        System.out.println("[대학교 성적 관리 프로그램]\n");
        System.out.println("본 프로그램은 컴퓨터공학과의 성적 관리 프로그램입니다.");
        System.out.println("부당한 방법으로 개인 성적의 위,변조를 시도할 경우 학칙에 의거하여 징계처리될 수 있습니다.");
    }

    public static void printStudents(){
        System.out.println("[학생 목록]");
        for(int i = 0; i < students.size(); i++){
            System.out.print(students.get(i).getName() +"  ");
            if((i + 1) % 5 == 0) System.out.println();
        }
        System.out.println("\n---------------------");
    }

    public static Student searchStudent(final String name){
        for (Student student : students) {
            if (student.getName().equals(name))
                return student;
        }
        return null;
    }

    public static Student inputData(StringTokenizer st){
        Student student = new Student();

        student.setStudentNum(st.nextToken());
        student.setName(st.nextToken());
        student.setYear(st.nextToken());

        while(st.hasMoreTokens()){
            student.getScore().add(new Score(st.nextToken(), st.nextToken(), st.nextToken()));
        }
        return student;
    }

    public static void outputData() throws IOException{
        // DATA.txt 에 백업
        System.out.print("\n[데이터 백업 중] -> ");
        long start = System.currentTimeMillis();
        BufferedWriter bw = new BufferedWriter(new FileWriter("src\\DATA.txt"));

        StringBuilder sb = new StringBuilder();

        for(Student student : students) {
            sb.append(student.getStudentNum()).append(",")
                    .append(student.getName()).append(",")
                    .append(student.getYear());

            for (Score s : student.getScore()) {
                sb.append(",")
                        .append(s.getSubject()).append(",")
                        .append(s.getScore()).append(",")
                        .append(s.getGrade());
            }
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
            bw.newLine();
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
        bw.close();
    }

    public static void insertStudent(Student s){
        students.add(s);
    }
}
