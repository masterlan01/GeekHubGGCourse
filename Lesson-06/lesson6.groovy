@groovy.transform.TypeChecked

class lesson6 {

    static def accept(def quacker) {
        (quacker instanceof StrictDrake)? (quacker as StrictDrake).quack() : (quacker as FrivolousDuck).quack()
    }

    static void main(args)
    {
        println """
        ${accept(new FrivolousDuck("str",12.34,[key1:"val1",key2:"val2"]))}
        ${accept(new FrivolousDuck(12.34,"str",[key1:"val1",key2:"val2"]))}
        ${accept(new FrivolousDuck([key1:"val1",key2:"val2"],"str",12.34))}
        ${accept(new FrivolousDuck([key1:"val1",key2:"val2"],"str",null))}
        ${accept(new FrivolousDuck(null,"str",null))}

        ${accept(new StrictDrake("str_D",912.349,[key1:"val1_D",key2:"val2_D"]))}
        """
    }

}

//использование динамической типизации
class FrivolousDuck {

    def strAny
    def numbAny
    def mapAny

    FrivolousDuck(def str, def numb, def map){
        strAny = str
        numbAny = numb
        mapAny = map
    }

    def quack (){
        this.toString()+"\tquack: "+strAny+" - "+numbAny+" - "+mapAny
    }
}

//использование жесткой типизации
class StrictDrake {

    String strAny
    BigDecimal numbAny
    Map mapAny

    StrictDrake(String str, BigDecimal numb, Map map){
        strAny = str
        numbAny = numb
        mapAny = map
    }

    String quack (){
        this.toString()+"\t\tquack: "+strAny+" - "+numbAny+" - "+mapAny
    }
}
