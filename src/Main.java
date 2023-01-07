import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 백업을 위해 최초 학생 리스트의 크기를 저장
        int lastMark;

        // 최초 사이즈와 현재 사이즈를 비교하기 위한 변수
        int currentMark;
        
        // 성적 변경점 체크
        boolean check = false;
        
        // 교수가 접속했다면 true
        boolean prof_run = false;

        // 학생이 접속했다면 true
        boolean stud_run = false;

        // 초기 데이터를 삽입하기 위한 변수
        String data;

        // 성적을 입력할 교수
        Professor professor = null;

        // 성적을 입력받을 학생
        Student student;

        // 학생들의 배열
        ArrayList<Student> students = new ArrayList<>();

        // 메뉴 선택 번호
        int select;

        // 입력받기 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BufferedReader input = new BufferedReader(new FileReader("src\\DATA.txt"));
        while((data = input.readLine()) != null){
            student = insertData(new StringTokenizer(data, ","));
            students.add(student);
        }
        student = null;

        // 사이즈 마킹
        lastMark = students.size();
        currentMark = students.size();

        System.out.println("[국립강릉원주대학교 성적 관리 프로그램]\n");
        printNotice();
        try{
            for(int i = 0; i < 5; i++){
                Thread.sleep(500);
                System.out.println(".");
            }
            System.out.println();
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }

        System.out.println("사용자 정보를 선택하여 주십시오.");
        System.out.println("[1] 교직원 [2] 학생");
        System.out.print("-> ");
        select = Integer.parseInt(br.readLine());

        switch(select){
            case 1:
                System.out.println("* 교직원 접근 권한 필요 *");
                System.out.print("ACCESS KEY 입력 : ");
                if(Professor.access(br.readLine()) == 0){
                    System.out.println("[경고] 유효하지 않은 ACCESS KEY");
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(1);
                }

                System.out.println("\n* 접속 성공 *\n");
                professor = new Professor();
                prof_run = true;

                if(students.size() == 0){
                    System.out.println("관리할 학생 정보가 없습니다.");
                    System.exit(0);
                }

                break;
            case 2:
                System.out.println("등록 이력이 있습니까?");
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
                stud_run = true;

                break;
        }

        // 교수
        while(prof_run) {
            System.out.println("메뉴를 선택해주세요.\n");
            System.out.println("[1] 학생 성적 입력 및 변경\n"
                    + "[2] 프로그램 종료\n");

            select = Integer.parseInt(br.readLine());

            switch (select) {
                case 1:
                    printStudents(students);
                    System.out.println("성적을 입력할 학생의 이름을 입력해주세요.");
                    System.out.print("-> ");

                    student = Objects.requireNonNull(searchStudent(students, br.readLine()), "존재하지 않는 학생입니다.");

                    System.out.println();

                    professor.update(student);
                    check = true;
                    break;

                case 2:
                    prof_run = false;
            }
        }
        
        // 학생
        while(stud_run){
            System.out.println("메뉴를 선택해주세요.\n");
            System.out.println("[1] 성적 조회\n"
                        + "[2] 내 정보 보기\n"
                        + "[3] 프로그램 종료\n");
            System.out.print("-> ");
            select = Integer.parseInt(br.readLine());
            switch(select){
                case 1:
                    ArrayList<Score> score = Objects.requireNonNull(student).getScore();
                    for(Score s : score){
                        s.printScore();
                    }
                    break;

                case 2:
                    student.printProfile();
                    break;

                case 3:
                    stud_run = false;
            }
        }

        // 변경점이 존재한다면 데이터 백업
        if(lastMark < currentMark || check){
            System.out.println("데이터 백업 중");
            try{
                for(int i = 0; i < 3; i++){
                    Thread.sleep(300);
                    System.out.println(".");
                }
            }catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
            backup(students);
        }
        
        System.out.println("\n프로그램을 종료합니다.\n좋은 하루 보내세요.\n");
    }

    public static void printNotice(){
        System.out.println("본 프로그램은 국립강릉원주대학교(원주캠퍼스) 컴퓨터공학과의 성적 관리 프로그램입니다.\n" +
                "부당한 방법으로 개인 성적의 위,변조를 시도할 경우 학칙에 의거하여 징계처리될 수 있습니다.");
    }

    public static void printStudents(ArrayList<Student> students){
        System.out.println("[학생 목록]");
        for(int i = 0; i < students.size(); i++){
            System.out.print(students.get(i).getName() +"  ");
            if((i + 1) % 5 == 0) System.out.println();
        }
        System.out.println("\n---------------------");
    }

    public static Student searchStudent(ArrayList<Student> students, String name){
        for (Student student : students) {
            if (student.getName().equals(name))
                return student;
        }
        return null;
    }

    public static Student insertData(StringTokenizer st){
        Student student = new Student();

        student.setStudentNum(st.nextToken());
        student.setName(st.nextToken());
        student.setYear(st.nextToken());

        while(st.hasMoreTokens()){
            student.getScore().add(new Score(st.nextToken(), st.nextToken(), st.nextToken()));
        }
        return student;
    }

    public static void backup(ArrayList<Student> students) throws IOException{
        // DATA.txt 에 백업
        BufferedWriter bw = new BufferedWriter(new FileWriter("src\\DATA.txt"));

        StringBuilder sb = new StringBuilder();
        Student student;

        for(Student value : students) {
            student = value;
            sb.append(student.getStudentNum()).append(",");
            sb.append(student.getName()).append(",");
            sb.append(student.getYear());

            for (Score s : student.getScore()) {
                sb.append(",");
                sb.append(s.getSubject());
                sb.append(",");
                sb.append(s.getScore());
                sb.append(",");
                sb.append(s.getGrade());
            }
            bw.write(sb.toString());
            bw.flush();
            sb.setLength(0);
            bw.newLine();
        }
        bw.close();
    }
}