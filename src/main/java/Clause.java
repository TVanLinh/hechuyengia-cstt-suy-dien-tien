/**
 * Created by linhtran on 08/11/17.
 */
public class Clause {
    private String name;
    private String title;
    private int level;
    public Clause() {
    }

    public Clause(String name, String title, int level) {
        this.name = name;
        this.title = title;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    @Override
    public String toString() {
        return "Clause{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", level=" + level +
                '}';
    }
}
