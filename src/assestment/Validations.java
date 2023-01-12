package assestment;

public class Validations {
    public static Long secureReduction(Long toReduce, Long reducer){
        if (toReduce < 1){
            System.out.println("no tienes dinero");
            return toReduce;
        }else if(toReduce < reducer){
            System.out.println("suma demasiado alta");
            return toReduce;
        }else if (reducer < 1){
            System.out.println("invalido, es demasiado bajo");
            return toReduce;
        }
        return toReduce - reducer;
    }

    public static Long secureAdition(Long toAdd, Long adiotioner){
        if (adiotioner < 1){
            System.out.println("invalido, es demasiado bajo");
            return toAdd;
        }
        return toAdd + adiotioner;
    }
}
