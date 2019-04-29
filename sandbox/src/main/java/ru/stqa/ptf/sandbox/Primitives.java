public class Primitives {
    public static void main(String[] args) {
        System.out.println(getWeight(888));
    }

    public static double getWeight(int earthWeight) {
        //напишите тут ваш код
        double a = (earthWeight/100) * 83;
        double moonWeight = earthWeight - a;
        return moonWeight;
    }
}