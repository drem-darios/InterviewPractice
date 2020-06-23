import java.util.ArrayList;

/**
 * Makes an immutable array.
 */
public class Snapshot {

    private ArrayList<Integer> data;

    public Snapshot(ArrayList<Integer> data) {
        this.data = new ArrayList<>();
        this.data.addAll(data);
    }

    public ArrayList<Integer> restore() {
        return new ArrayList(this.data);
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        Snapshot snap = new Snapshot(list);
        list.set(0, 3);
        list = snap.restore();
        System.out.println(list); //It should log "[1,2]"
        list.add(4);
        list = snap.restore();
        System.out.println(list); //It should log "[1,2]"
    }
}