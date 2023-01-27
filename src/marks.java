public class marks {
    Integer roll,ct1,ct2,ct3,ct4,sum = 0;
    public marks(Integer roll,Integer ct1,Integer ct2,Integer ct3,Integer ct4){
        Integer full_roll;
        if(roll == 1910022){
            full_roll = 1910022;
        }
        else{
            full_roll = 2010000 + roll;
        }
        this.roll = full_roll;
        this.ct1 = ct1;
        this.ct2 = ct2;
        this.ct3 = ct3;
        this.ct4 = ct4;
    }
    public Integer getRoll(){
        return roll;
    }
    public void setRoll(Integer roll){
        this.roll = roll;
    }
    public Integer getCt2(){
        return ct2;
    }
    public void setCt2(Integer ct2){
        this.ct2 = ct2;
    }
    public Integer getCt3(){
        return ct3;
    }
    public void setCt3(Integer ct3){
        this.ct3 = ct3;
    }
    public Integer getCt4(){
        return ct4;
    }
    public void setCt4(Integer ct4){
        this.ct4 = ct4;
    }
    public Integer getCt1(){
        return ct1;
    }
    public void setCt1(Integer ct1){
        this.ct1 = ct1;
    }
    public Integer getSum(){
        Integer[] marks = {ct1,ct2,ct3,ct4};
        for(int i = 0; i < 4-1; ++i){
            for(int j = 0; j < 4-i-1; ++j){
                if(marks[j] < marks[j+1]){
                    Integer tmp = marks[j];
                    marks[j] = marks[j+1];
                    marks[j+1] = tmp;
                }
            }
        }
        sum = marks[0] + marks[1] + marks[2];
        Double temp = ((sum*1.0)/3);
        Integer k = sum/3;
        if(temp > (float)k){
            return k+1;
        }
        else{
            return k;
        }
    }
}
