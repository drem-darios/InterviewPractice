import java.util.*;

/**
 * Checks if two friends have friends in common using bi-directional graph search
 */
public class Friend {
    private Collection<Friend> friends;
    private String email;

    public Friend(String email) {
        this.email = email;
        this.friends = new ArrayList<Friend>();
    }

    public String getEmail() {
        return email;
    }

    public Collection<Friend> getFriends() {
        return friends;
    }

    public void addFriendship(Friend friend) {
        friends.add(friend);
        friend.getFriends().add(this);
    }

    public boolean canBeConnected(Friend friend) {
        Queue<Friend> firstQueue = new LinkedList();
        Queue<Friend> secondQueue = new LinkedList();

        firstQueue.add(this);
        secondQueue.add(friend);

        Set<Friend> firstVisited = new HashSet();
        Set<Friend> secondVisited = new HashSet();

        firstVisited.add(this);
        secondVisited.add(friend);

        while(!firstQueue.isEmpty() || !secondQueue.isEmpty()) {
            if (pathFinder(firstQueue, firstVisited, secondVisited)) {
                return true;
            }
            if (pathFinder(secondQueue, secondVisited, firstVisited)) {
                return true;
            }
        }

        return false;
    }

    private static boolean pathFinder(Queue<Friend> queue, Set<Friend> firstVisited, Set<Friend> secondVisited) {
        if (!queue.isEmpty()) {
            Friend next = queue.remove();

            Collection<Friend> friends = next.getFriends();
            for (Friend friend: friends) {
                if (secondVisited.contains(friend)) {
                    return true;
                } else if (firstVisited.add(friend)) {
                    queue.add(friend);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Friend a = new Friend("A");
        Friend b = new Friend("B");
        Friend c = new Friend("C");

        a.addFriendship(b);
        b.addFriendship(c);

        System.out.println(a.canBeConnected(c));
    }
}