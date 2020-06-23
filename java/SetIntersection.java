import java.util.HashSet;
import java.util.Set;

/**
 * This class tests the ability of set intersects. Both methods should be equivalent. The 
 * only difference is one returns a result object and does not modify the existing sets and
 * the other is a void method that manipulates one of the sets. The same can be done with
 * union and difference:
 * 
 * Set<Type> union = new HashSet<Type>(s1);
 * union.addAll(s2);
 * 
 * Set<Type> difference = new HashSet<Type>(s1);
 * difference.removeAll(s2);
 * 
 * @author drem
 *
 */
public class SetIntersection {
	public static void main (String[] args) {
		Set<String> s1 = new HashSet<String>();
		Set<String> s2 = new HashSet<String>();
		
		s1.add("1");
		s1.add("2");
		s1.add("3");

		s2.add("3");
		s2.add("4");
		s2.add("5");
		
		Set<String> intersectResult = intersect(s1, s2);
		for (String s : intersectResult) {
			System.out.println("intersectResult: "+s);
		}
		for (String s : s1) {
			System.out.println("intersect s1: "+s);
		}
		for (String s : s2) {
			System.out.println("intersect s2: "+s);
		}
		
		intersectAndOverride(s1, s2);
		
		for (String s : s1) {
			System.out.println("intersectAndOverride s1: "+s);
		}
		for (String s : s2) {
			System.out.println("intersectAndOverride s2: "+s);
		}
			
	}
	
	public static Set<String> intersect(Set<String> s1, Set<String> s2) {
		Set<String> result = new HashSet<String>();
		result.addAll(s1);
		boolean merged = result.retainAll(s2);
		System.out.println("intersect: "+merged);
		return result;
	}
	
	public static void intersectAndOverride(Set<String> s1, Set<String> s2) {
		boolean result = s1.retainAll(s2);
		System.out.println("intersect and override: "+result);
	}
}


