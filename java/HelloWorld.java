

public class HelloWorld {
	private String name = "nobody";

	public void setName(String name) {
		this.name = name;
	}

	public void printHello() {
		System.out.println("Hello " + name + "! ");
	}
	public static void main (String[] args) {
		HelloWorld helloWorld = new HelloWorld();
		helloWorld.printHello();
		helloWorld.setName("somebody");
		helloWorld.printHello();
	}
}