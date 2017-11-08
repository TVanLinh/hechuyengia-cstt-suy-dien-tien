import java.util.*;

public class Test {
    ArrayList<String> TG = new ArrayList<String>();

    public static void main(String[] args) {
        BaiToan bai = new BaiToan();
        bai.readClause();
        Set<String> listClause = new HashSet<String>() {
        };
        String answer = "y";
        Scanner scanner = new Scanner(System.in);

        do {
            for (Clause clause : bai.clauses) {
                System.out.println(clause.getName() + " : " + clause.getTitle());
            }

            System.out.print("Nhập dấu hiêu: (nhập các chữ cái a,b,c,d ...): ");
            String clauseEnter;
            clauseEnter = scanner.nextLine();
            listClause.add(clauseEnter.toLowerCase());

            //----------------------------------------------
            bai.GT = new ArrayList<String>(listClause);

            bai.thuatToanSuyDienTien();

            ClauseService clauseService = new ClauseService();


            Set<String> con = clauseService.getConslution(bai.GT, bai.TG);

            List<Clause> clauses = new ArrayList<Clause>();
            Clause clause;
            for (String conItem : con) {
                clause = clauseService.getClause(conItem, bai.clauses);
                if (clause != null) {
                    clauses.add(clause);
                }
            }

            String msg = clauseService.getMessage(clauses);

            if (msg.equalsIgnoreCase("")) {
                System.out.println("\nKhông có trường hợp nào với dấu hiệu này ");
            } else {
                System.out.println(clauseService.getMessage(clauses));
            }

            //--------------------------------------------------

            System.out.print("\nCó thêm dấu hiệu nào không (y/n): ");
            answer = scanner.nextLine();

        } while (answer.trim().equalsIgnoreCase("y"));


    }


}
