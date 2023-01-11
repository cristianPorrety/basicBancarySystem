package bankAccount.loan;

public class UserIsAvaliableToLoan {
    static Boolean isNeighborhoodRangeAvaliable(Integer range){
        return range > 3;
    }

    static boolean salaryAvaliable(Long salary){
        return salary > 3000000;
    }

    static boolean childrenNumAvaliable(int children){
        return children <= 3;
    }
}
