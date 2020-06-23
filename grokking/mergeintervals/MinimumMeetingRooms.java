package grokking.mergeintervals;

import grokking.models.Meeting;

import java.util.*;

class MinimumMeetingRooms {

    public static int findMinimumMeetingRooms(List<Meeting> meetings) {
        if (meetings.size() < 2) {
            return meetings.size(); // Only need size number of rooms
        }

        // Sort by start time O(NlogN)
        Collections.sort(meetings, Comparator.comparingInt(meetingA -> meetingA.start));
        List<Deque<Meeting>> result = new ArrayList<>();

        Iterator<Meeting> meetingIterator = meetings.iterator();
        Meeting meeting = meetingIterator.next();
        Deque<Meeting> room = new ArrayDeque<>();
        // Add the first room to the list of meeting rooms
        room.add(meeting);
        result.add(room);

        while(meetingIterator.hasNext()) {
            meeting = meetingIterator.next();
            boolean requiresNewRoom = true;
            for (Deque<Meeting> rooms : result) {
                Iterator<Meeting> roomIterator = rooms.iterator();
                boolean hasConflict = false;
                while(roomIterator.hasNext()) {
                    Meeting nextRoom = roomIterator.next();
                    if (meeting.start < nextRoom.end) {
                        hasConflict = true;
                        break;
                    }
                }

                if (!hasConflict) {
                    requiresNewRoom = false;
                    break;
                }
            }

            if (requiresNewRoom) {
                Deque<Meeting> newRoom = new ArrayDeque<>();
                newRoom.add(meeting);
                result.add(newRoom);
            } else {
                room.add(meeting);
            }
        }

        return result.size();
    }

    public static void main(String[] args) {
        List<Meeting> input = new ArrayList<Meeting>() {
            {
                add(new Meeting(4, 5));
                add(new Meeting(2, 3));
                add(new Meeting(2, 4));
                add(new Meeting(3, 5));
            }
        };
        int result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("1. Minimum meeting rooms required: " + result + " expected: 2");

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 5));
                add(new Meeting(7, 9));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("2. Minimum meeting rooms required: " + result + " expected: 2");

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(6, 7));
                add(new Meeting(2, 4));
                add(new Meeting(8, 12));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("3. Minimum meeting rooms required: " + result + " expected: 1");

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 3));
                add(new Meeting(3, 6));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("4. Minimum meeting rooms required: " + result + " expected: 2");

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(4, 5));
                add(new Meeting(2, 3));
                add(new Meeting(2, 4));
                add(new Meeting(3, 5));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("5. Minimum meeting rooms required: " + result + " expected: 2");
    }
}

