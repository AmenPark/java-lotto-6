package lotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class Calculator {
    private Map<Prize, Integer> count;
    private final List<Integer> winner;
    private final int bonusNum;

    public Calculator(List<Integer> winner, int bonus) {
        count = new HashMap<>();
        this.winner=winner;
        this.bonusNum=bonus;
    }


    public void checkLotto(Lotto lotto){
        int containCount=0;
        for (int num:winner){
            if(lotto.hasNum(num)){
                containCount+=2;
            }
        }
        if (lotto.hasNum(bonusNum)){
            containCount++;
        }
        update(containCount);
    }
    private void update(int containCount){
        int temp;
        for(Prize p: Prize.values()){
            if(p.getSame()<=containCount){
                temp=count.get(p) + 1;
                count.put(p,temp);
                return;
            }
        }
    }
    public long prizeSum(){
        long sum=0;
        for(Prize p:Prize.values()){
            sum+=p.getMoney() * count.get(p);
        }
        return sum;
    }

    public String statistics(){
        StringJoiner sj = new StringJoiner("\n");
        for(Prize p:Prize.values()){
            sj.add(String.format("%s%d개", p.getUI(), count.get(p)));
        }
        return sj.toString();
    }
}
