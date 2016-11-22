enum Food { bread, soup, meat, fruit }

abstract class Pet {
    String name;
    abstract boolean eats(Food f);
    abstract String sound();
    public void talk() {
        System.out.println(name+": "+sound());
    }
    public String toString() { return name; }

    public static void main(String[] args) {
        Pet a = new Cat("Kartopu", 2010);
        Pet b = new Bird("Cingöz", 30);
        Pet c = new Bird("Safran", 45);
        Pet[] pets = { a, b, c };
        for (Pet p : pets) p.talk();
        int n = 0; Food f = Food.bread;
        for (Pet p : pets) if (p.eats(f)) n++;
        System.out.println(n+" pets eat "+f);
    }
}
class Cat extends Pet {
    int birth;
    public Cat(String s, int b) {
        name = "Kedi "+s;  birth = b;
    }
    public boolean eats(Food f) { return (f == Food.meat); }
    public String sound() { return "miyav"; }
}
class Bird extends Pet {
    float price; 
    public Bird(String s, float p) {
        name = "Kuþ "+s;  price = p;
    }
    public boolean eats(Food f) { return true; }
    public String sound() { return "cik cik"; }
}
