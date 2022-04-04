package mas.mp1.s19239.model;

public enum Sale {
    Not(0),
    low(0.15),
    mid(0.3),
    high(0.5);

    private double percentage;
//This a discount available on product
    Sale (double sale) {
        this.percentage = sale;
    }

    public double getPercentage() {
        return percentage;
    }
}
