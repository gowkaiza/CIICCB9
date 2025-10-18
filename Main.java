public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
        Plant plant1 = new Plant();
        plant1.name="papaya"; //write
        plant1.setBranch(leaves:100);
        System.out.println(plant1.name); //read
        System.out.println(plant1.getBranch());
    }
}
/**
 * Plant class creates
 */
class Plant{
    int branch;
    String name;
    int leaves;
    void setBranch(int leaves){
        this.leaves=leaves;
    }

}