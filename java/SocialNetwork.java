// Create an optimized search for a social network to efficiently find whether a person has a connection to another person.

import java.util.*;

public class SocialNetwork {

	public static boolean hasConnection(Person firstPerson, Person secondPerson) {
		Queue<Person> firstQueue = new LinkedList();
		Queue<Person> secondQueue = new LinkedList();

		firstQueue.add(firstPerson);
		secondQueue.add(secondPerson);

		Set<Person> firstVisited = new HashSet();
		Set<Person> secondVisited = new HashSet();

		firstVisited.add(firstPerson);
		secondVisited.add(secondPerson);

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

	private static boolean pathFinder(Queue<Person> queue, Set<Person> firstVisited, Set<Person> secondVisited) {
		if (!queue.isEmpty()) {
			Person next = queue.remove();

			List<Person> connections = next.getConnections();
			for (Person person: connections) {
			    // Check if the second person has visited any of first person's connections
				if (secondVisited.contains(person)) {
					return true;
				} else if (firstVisited.add(person)) {
				    // If no connection, mark this person as visited by the first person
					queue.add(person);
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {

        Person zerothPerson = new Person("Zeroth Person");
	    Person firstPerson = new Person("First Person");
		Person secondPerson = new Person("Second Person");
		Person thirdPerson = new Person("Third Person");
		Person fourthPerson = new Person("Fourth Person");
		Person fifthPerson = new Person("Fifth Person");
		Person sixthPerson = new Person("Sixth Person");
		Person seventhPerson = new Person("Seventh Person");
		Person eighthPerson = new Person("Eighth Person");
		Person ninthPerson = new Person("Ninth Person");
		Person tenthPerson = new Person("Tenth Person");
		Person eleventhPerson = new Person("Eleventh Person");
		Person twelfthPerson = new Person("Twelfth Person");
		Person thirteenthPerson = new Person("Thirteenth Person");
		Person fourteenthPerson = new Person("Fourteenth Person");


		fourthPerson.addConnection(zerothPerson);
        fourthPerson.addConnection(firstPerson);
        sixthPerson.addConnection(fourthPerson);
        boolean result = SocialNetwork.hasConnection(zerothPerson, sixthPerson);
        System.out.println("Zero person connected to Sixth person: " + result);

        secondPerson.addConnection(fifthPerson);
        thirdPerson.addConnection(fifthPerson);
        sixthPerson.addConnection(fifthPerson);

        seventhPerson.addConnection(sixthPerson);

        tenthPerson.addConnection(thirteenthPerson);
        tenthPerson.addConnection(fourteenthPerson);
        eighthPerson.addConnection(tenthPerson);

        eleventhPerson.addConnection(ninthPerson);
        twelfthPerson.addConnection(ninthPerson);
        eighthPerson.addConnection(ninthPerson);

//        seventhPerson.addConnection(eighthPerson);

		result = SocialNetwork.hasConnection(zerothPerson, fourteenthPerson);
		System.out.println("Zero person connected to Fourteen person: " + result);

		result = SocialNetwork.hasConnection(firstPerson, thirdPerson);
		System.out.println("First person connected to third person: " + result);

		result = SocialNetwork.hasConnection(secondPerson, thirdPerson);
		System.out.println("Second person connected to third person: " + result);
	}

	private static class Person {
		private List<Person> connections;
		private String name;

		Person(String name) {
			this.name = name;
			this.connections = new ArrayList();
		}

		public String getName() {
			return this.name;
		}

		public void addConnection(Person person) {
			this.connections.add(person);
			person.getConnections().add(this); // connections are bi-directional
		}

		public List<Person> getConnections() {
			return this.connections;
		}
	}
}
