import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GradeManagement{
    public static boolean check = false;
    private static ArrayList<Student> students = new ArrayList<>();
    private Professor professor = null;
    private Student student = null;
    private static final String[] role = {"PROFESSOR", "STUDENT"};
    private static final String[] studentMenu = {"GRADE_CHECK", "INFO_CHECK"};
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void startProgram() throws IOException{
        GradeManagement gradeManagement = new GradeManagement();

        gradeManagement.printNotice();
        gradeManagement.loadData();

        switch(role[gradeManagement.selectMenuMain() - 1]){
            case "PROFESSOR":
                gradeManagement.professor = Professor.accessProfessor();
                gradeManagement.startProfessor();
                break;

            case "STUDENT":
                gradeManagement.student = Student.accessStudent();
                gradeManagement.startStudent();
                break;
        }

        if(check) gradeManagement.outputData();
    }
    private int selectMenuMain() throws IOException {
        System.out.println("사용자 정보를 선택하여 주십시오.");
        System.out.println("[1] 교직원 [2] 학생");
        System.out.print("-> ");

        return Integer.parseInt(br.readLine());
    }

    private int selectMenuStudent() throws IOException{
        System.out.println("메뉴를 선택해주세요.\n");
        System.out.println("[1] 성적 조회");
        System.out.println("[2] 내 정보 보기");
        System.out.println("[3] 프로그램 종료");
        System.out.print("-> ");

        return Integer.parseInt(br.readLine());
    }

    private int selectMenuProfessor() throws IOException{
        System.out.println("메뉴를 선택해주세요.\n");
        System.out.println("[1] 학생 성적 입력 및 변경");
        System.out.println("[2] 프로그램 종료");
        System.out.print("-> ");

        return Integer.parseInt(br.readLine());
    }

    private void startStudent() throws IOException{
        int select;

        while((select = selectMenuStudent()) != 3){
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

    private void startProfessor() throws IOException{
        if(!checkStudents()) return;

        while(selectMenuProfessor() != 2) {
            printStudents();

            professor.update();
            check = true;
        }
    }

    private boolean checkStudents(){
        if (students.size() == 0) {
            System.out.println("\n현재 조회 가능한 학생 정보가 없습니다.");

            return false;
        }
        return true;
    }

    private void loadData() throws IOException{
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
            BufferedWriter output = new BufferedWriter(new FileWriter("src\\DATA.txt"));
            output.newLine();

            students = new ArrayList<>();
        }
    }

    private void printNotice(){
        System.out.println("[대학교 성적 관리 프로그램]\n");
        System.out.println("본 프로그램은 컴퓨터공학과의 성적 관리 프로그램입니다.");
        System.out.println("부당한 방법으로 개인 성적의 위,변조를 시도할 경우 학칙에 의거하여 징계처리될 수 있습니다.");
    }

    private void printStudents(){
        System.out.println("[학생 목록]");
        for(int i = 0; i < students.size(); i++){
            System.out.print(students.get(i).getName() +"  ");
            if((i + 1) % 5 == 0) System.out.println();
        }
        System.out.println("\n---------------------------------");
    }

    public static Student searchStudent(final String name){
        for (Student student : students) {
            if (student.getName().equals(name))
                return student;
        }
        return null;
    }

    private Student inputData(StringTokenizer st){
        Student student = new Student();

        student.setStudentNum(st.nextToken());
        student.setName(st.nextToken());
        student.setYear(st.nextToken());

        while(st.hasMoreTokens()){
            student.getScore().add(new Score(st.nextToken(), st.nextToken(), st.nextToken()));
        }
        return student;
    }

    private void outputData() throws IOException{
        // DATA.txt 에 백업
        System.out.print("\n[데이터 백업 중. . .]\n");

        BufferedWriter bw = new BufferedWriter(new FileWriter("src\\DATA.txt"));

        StringBuilder sb = new StringBuilder();

        for(Student student : students) {
            sb.append(student.toString());
            for (Score score : student.getScore()) {
                sb.append(",").append(score.toString());
            }
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
            bw.newLine();
        }

        System.out.println("[백업 완료]");
        bw.close();
    }

    public static void insertStudent(Student student){
        students.add(student);
    }
}
