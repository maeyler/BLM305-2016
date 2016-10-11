enum Coin {
    quarter(25), dime(10), nickel(5), penny(1);
    
    final int cents; 
    Coin(int v) { cents = v; }
    public int value() { return cents; }
}

class CashRegister {

    int cash = 0; //in cents
    Coin[] coins = Coin.values();
    
    public void pay(int a) {
        if (cash < a) {
            System.out.printf("cannot pay %s \n", a);
            return;
        }
        for (Coin c : coins) 
            while  (a >= c.value()) {
                cash = cash - c.value();
                a = a - c.value();
                System.out.printf("pay %s \n", c);
            }
        report();
    }
    public void receive(Coin c) {
        cash = cash + c.value();
        report();
    }
    public void report() {	
        System.out.printf("cash: %s \n", cash);
    }
}

class Cash {
    public static void main(String[] args) {
   
	 CashRegister cr = new CashRegister();

	 cr.receive(Coin.quarter);
	 cr.receive(Coin.quarter);
	 cr.receive(Coin.dime);
	 cr.receive(Coin.dime);
	 cr.receive(Coin.nickel);
	 cr.receive(Coin.penny);
	 cr.receive(Coin.penny);
	 cr.receive(Coin.dime);
	 cr.receive(Coin.quarter);
	 cr.receive(Coin.quarter);

	 cr.pay(87);
	 cr.pay(55);

    }
}
