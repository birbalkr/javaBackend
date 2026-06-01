import java.util.function.BiFunction;

@FunctionalInterface
interface Operation{
    int add(int a, int b);
}


public class Day5 {


    static BiFunction<Integer, Integer, Integer> addFunction = Integer::sum;

    static BiFunction<Integer, Integer, Integer> subFunction = (a,b) ->a-b;

    static void main() {
        Operation Op =(a,b) -> {
            return  a + b;
        };
        System.out.println(Op.add(5,5));

        System.out.println(addFunction.apply(10,20));
        System.out.println(subFunction.apply(10,20));

//        try {
////            int result = 10 / 0;
////            System.out.println(result);
//            int[] a={1,2,3};
//            System.out.println(a[6]);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    }
}
