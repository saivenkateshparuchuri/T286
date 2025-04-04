import java.util.*;

public class CareerCounsellingSystem {
    private static Map<String, Career> careers = new HashMap<>();
    private static List<Student> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initCareers();
        System.out.println("School Career Counselling System");
        while(true) {
            System.out.print("\n1. Register\n2. Assessment\n3. Explore\n4. Recommendations\n5. Exit\nChoice: ");
            switch(sc.nextLine()) {
                case "1": register(); break;
                case "2": assess(); break;
                case "3": explore(); break;
                case "4": recommend(); break;
                case "5": System.out.println("Goodbye!"); System.exit(0);
                default: System.out.println("Invalid choice");
            }
        }
    }

    private static void initCareers() {
        careers.put("CS", new Career("Computer Science", "Tech fields", "Math/logic", "CS degree"));
        careers.put("MED", new Career("Medicine", "Healthcare", "Biology/empathy", "Medical degree"));
        careers.put("DES", new Career("Design", "Creative fields", "Creativity", "Design degree"));
        careers.put("TEACH", new Career("Teaching", "Education", "Communication", "B.Ed"));
    }

    private static void register() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Grade: ");
        students.add(new Student(name, Integer.parseInt(sc.nextLine())));
        System.out.println("Registered!");
    }

    private static void assess() {
        if(students.isEmpty()) { System.out.println("Register first!"); return; }
        System.out.println("Answer (y/n):");
        int[] scores = new int[4];
        String[] questions = {
            "Enjoy math problems?", "Like art/design?", 
            "Interested in computers?", "Enjoy helping others?"
        };
        for(int i=0; i<4; i++) {
            System.out.print(questions[i]+" ");
            if(sc.nextLine().equalsIgnoreCase("y")) scores[i] = 2;
        }
        students.get(students.size()-1).setScores(scores);
        System.out.println("Assessment done!");
    }

    private static void explore() {
        careers.values().forEach(c -> System.out.printf(
            "\n%s\nDescription: %s\nSkills: %s\nEducation: %s\n",
            c.name, c.description, c.skills, c.education));
    }

    private static void recommend() {
        if(students.isEmpty()) { System.out.println("No students!"); return; }
        Student s = students.get(students.size()-1);
        if(s.science == -1) { System.out.println("Take assessment first!"); return; }
        System.out.println("\nRecommendations:");
        if(s.tech >= 2) System.out.println("- " + careers.get("CS").name);
        if(s.science >= 2) System.out.println("- " + careers.get("MED").name);
        if(s.arts >= 2) System.out.println("- " + careers.get("DES").name);
        if(s.social >= 2) System.out.println("- " + careers.get("TEACH").name);
    }
}

class Student {
    String name; int grade, science=-1, arts=-1, tech=-1, social=-1;
    Student(String n, int g) { name=n; grade=g; }
    void setScores(int[] s) { science=s[0]; arts=s[1]; tech=s[2]; social=s[3]; }
}

class Career {
    String name, description, skills, education;
    Career(String n, String d, String s, String e) { name=n; description=d; skills=s; education=e; }
}