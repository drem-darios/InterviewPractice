import java.util.*;

public class InformEmployees {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        // Build map of manager to list of subordinates
        Map<Integer, List<Integer>> subordinateMap = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            int managerId = manager[i];
            if (managerId >= 0){
                List<Integer> subordinates = subordinateMap.get(managerId);
                if (subordinates == null) {
                    subordinates = new ArrayList<>();
                }
                subordinates.add(i);
                subordinateMap.put(managerId, subordinates);
            }
        }

        Queue<Integer> managerQueue = new LinkedList<>();
        managerQueue.add(headID);
        int totalTime = 0;

        while(!managerQueue.isEmpty()) {
            Integer managerId = managerQueue.poll();
            // Sum the inform time of each subordinates's manager
            if (manager[managerId] >= 0) {
                informTime[managerId] += informTime[manager[managerId]];
            }
            totalTime = Math.max(totalTime, informTime[managerId]);
            List<Integer> subordinates = subordinateMap.get(managerId);
            if (subordinates != null) {
                for (Integer sub: subordinates) {
                    // Managers are not leaves
                    if (subordinateMap.get(sub) != null) {
                        managerQueue.add(sub);
                    }
                }
            }
        }

        return totalTime;
    }

    public static void main(String[] args) {
//        int n = 6, headID = 2;
//        int[]  manager = new int[]{2,2,-1,2,2,2}, informTime = new int[]{0,0,1,0,0,0};

//        int n = 1, headID = 0;
//        int[] manager = new int[]{-1}, informTime = new int[]{0};
//        int n = 7, headID = 6;
//        int[] manager = new int[]{1,2,3,4,5,6,-1}, informTime = new int[]{0,6,5,4,3,2,1};
        int n = 15 , headID = 0;
        int[] manager = new int[]{-1,0,0,1,1,2,2,3,3,4,4,5,5,6,6},
        informTime = new int[] {1,1,1,1,1,1,1,0,0,0,0,0,0,0,0};
        InformEmployees informEmployees = new InformEmployees();
        System.out.println(informEmployees.numOfMinutes(n, headID, manager, informTime));
    }
}
