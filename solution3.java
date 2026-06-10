import java.util.*;
public class A3 {
    public static void main(String args[]){
        ArrayList<String> Names=new ArrayList<>();
        Names.add("Atomic habbits");
        Names.add("RN KAO");
        Names.add("R&AW");
        Names.add("the accidental PM");
        Names.add("Gandhi");
        System.out.print(Names);
        System.out.println();
        //SEARCHING FOR


        String key ="KAO";
        //System.out.println(Names.contains("Gandhi"));
        int i=0;
        for(String element:Names){
            if(element.contains(key)){
                System.out.println("Found at index "+ i);


            }
            i++;
        }
       
        return ;
    }




}
   





