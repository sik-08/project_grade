import java.io.*;
import java.util.*;

public class Main {
    private static boolean check; // 데이터의 변경점이 있는지 체크
    public static void main(String[] args) throws IOException {
        // 최초 사이즈와 현재 사이즈를 비교하기 위한 변수
        int lastMark, currentMark;

        // 성적을 입력할 교수
        Professor professor = null;

        // 성적을 입력받을 학생
        Student student = null;

        // 데이터 불러오기
        ArrayList<Student> students = loadData();

        // 입력받기 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 프로그램 시작, 학생 배열의 사이즈 마킹
        lastMark = students.size();
        currentMark = students.size();

        printNotice();

        String[] role = {"PROFESSOR", "STUDENT"};

        switch(role[selectMenu() - 1]){
            case "PROFESSOR":
                System.out.println("* 교직원 접근 권한 필요 *");
                System.out.print("ACCESS KEY 입력 : ");
                if(Professor.access(br.readLine()) == 0){
                    System.out.println("[경고] 유효하지 않은 ACCESS KEY");
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(1);
                }

                System.out.println("\n* 접속 성공 *\n");
                professor = new Professor();

                if(students.size() == 0){
                    System.out.println("관리할 학생 정보가 없습니다.");
                    System.exit(0);
                }

                break;

            case "STUDENT":
                System.out.println("\n등록 이력이 있습니까?");
                System.out.println("[1] 예 [2] 아니오");
                System.out.print("-> ");

                if(Integer.parseInt(br.readLine()) == 2){
                    student = new Student(2);
                    students.add(student);
                    currentMark++;
                }
                else{
                    System.out.println("이름을 입력해주세요.");
                    System.out.print("-> ");

                    student = Objects.requireNonNull(searchStudent(students, br.readLine()));
                }

                System.out.println("조회 중입니다.");
                try{
                    for(int i = 0; i < 3; i++){
                        Thread.sleep(300);
                        System.out.println(".");
                    }
                }catch(InterruptedException e){
                    System.out.println(e.getMessage());
                }

                System.out.println("\n*** " + student.getName() + "님 환영합니다. ***\n");

                break;
        }

        if(Objects.nonNull(professor))  startProfessor(professor, students);
        else                            startStudent(student);

        // 변경점이 존재한다면 데이터 백업
        if(lastMark < currentMark || check) outputData(students);
        
        System.out.println("\n프로그램을 종료합니다. 좋은 하루 보내세요.\n");
    }

    public static int selectMenu() throws IOException{
        int select;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("사용자 정보를 선택하여 주십시오.");
        System.out.println("[1] 교직원 [2] 학생");
        System.out.print("-> ");
        select = Integer.parseInt(br.readLine());

        return select;
    }

    public static void startStudent(Student s) throws IOException{
        int select;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        loop:while(true){
            System.out.println("메뉴를 선택해주세요.\n");
            System.out.println("[1] 성적 조회\n"
                    + "[2] 내 정보 보기\n"
                    + "[3] 프로그램 종료\n");
            System.out.print("-> ");
            select = Integer.parseInt(br.readLine());
            switch(select){
                case 1:
                    ArrayList<Score> score = s.getScore();
                    for(Score sc : score){
                        sc.printScore();
                    }
                    break;

                case 2:
                    s.printProfile();
                    break;

                case 3:
                    break loop;
            }
        }
    }

    public static void startProfessor(Professor p, ArrayList<Student> students) throws IOException{
        int select;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Student student;

        loop:while(true) {
            System.out.println("메뉴를 선택해주세요.\n");
            System.out.println("[1] 학생 성적 입력 및 변경\n"
                    + "[2] 프로그램 종료\n");
            System.out.print("-> ");

            select = Integer.parseInt(br.readLine());

            switch (select) {
                case 1:
                    printStudents(students);
                    System.out.println("성적을 입력할 학생의 이름을 입력해주세요.");
                    System.out.print("-> ");

                    student = Objects.requireNonNull(searchStudent(students, br.readLine()), "존재하지 않는 학생입니다.");

                    System.out.println();

                    p.update(student);
                    check = true;
                    break;

                case 2:
                    break loop;
            }
        }
    }
    public static ArrayList<Student> loadData() throws IOException{
        String data;
        ArrayList<Student> students = new ArrayList<>();
        Student student;

        try{
            BufferedReader input = new BufferedReader(new FileReader("src\\DATA.txt"));
            while((data = input.readLine()) != null){
                student = inputData(new StringTokenizer(data, ","));
                students.add(student);
            }
        }catch(FileNotFoundException e){
            System.out.println("불러올 파일이 존재하지 않습니다. 파일을 새로 생성합니다.");
            BufferedWriter bw = new BufferedWriter(new FileWriter("src\\DATA.txt"));
            bw.newLine();
        }
        return students;
    }
    public static void printNotice(){
        System.out.println("[대학교 성적 관리 프로그램]\n");
        System.out.println("본 프로그램은 컴퓨터공학과의 성적 관리 프로그램입니다.\n" +
                "부당한 방법으로 개인 성적의 위,변조를 시도할 경우 학칙에 의거하여 징계처리될 수 있습니다.");
    }

    public static void printStudents(final ArrayList<Student> students){
        System.out.println("[학생 목록]");
        for(int i = 0; i < students.size(); i++){
            System.out.print(students.get(i).getName() +"  ");
            if((i + 1) % 5 == 0) System.out.println();
        }
        System.out.println("\n---------------------");
    }

    public static Student searchStudent(final ArrayList<Student> students, final String name){
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

    public static void outputData(final ArrayList<Student> students) throws IOException{
        // DATA.txt 에 백업
        BufferedWriter bw = new BufferedWriter(new FileWriter("src\\DATA.txt"));

        StringBuilder sb = new StringBuilder();
        Student student;

        for(Student value : students) {
            student = value;
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
        bw.close();
    }
}