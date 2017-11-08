import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by linhtran on 08/11/17.
 */
public class ClauseService {

    public Set<String> getConslution(ArrayList<String> suppose, ArrayList<String> tmp) {
        Set<String> list = new HashSet<String>();
        boolean contain;
        for (String temp : tmp) {
            contain = false;
            for (String tp : suppose) {
                if (temp.equalsIgnoreCase(tp)) {
                    contain = true;
                    break;
                }
            }
            if (!contain) {
                list.add(temp);
            }

        }
        return list;
    }

    public Clause getClause(String name, List<Clause> clauses) {
        for (Clause clause : clauses) {
            if (name.equalsIgnoreCase(clause.getName())) {
                return clause;
            }
        }
        return null;
    }

    public String getMessage(List<Clause> clauses) {
        if (clauses.size() == 0) {
            return "";
        }
        StringBuilder buidler = new StringBuilder("Các trường hợp có thể: ");
        for (Clause clause : clauses) {
            buidler.append(clause.getTitle()).append(", ");
        }

        if (buidler.length() > 0) {
            buidler.replace(buidler.length() - 2, buidler.length() - 1, "");
        }
        return buidler.toString();
    }
}
