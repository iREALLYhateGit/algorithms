package ru.portfolio;

public class Fox implements Cloneable{
    String f;
    public Fox(String f) {
        this.f = f;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();
        return clone;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Fox fox = new Fox("asd");
        Fox doxc;
        God god = new God("fame");
        God god2 = (God) god.clone();
        try {
            doxc = (Fox) fox.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(fox.f);
        System.out.println(doxc.f);
        System.out.println(god.f);
        System.out.println(god2.f);

    }
}
class God extends Fox{

    public God(String f) {
        super(f);
    }
}
