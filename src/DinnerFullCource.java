public class DinnerFullCource {
    private Dish[] list = new Dish[5];// [0]-[4]の計5個

    DinnerFullCource() {
        for(int i = 0; i < list.length; i ++) {
            list[i] = new Dish();
        }
        //1個目
        list[0].setName("特選シーザサラダ");
        list[0].setValune(10);
        //2個目
        list[1].setName("銀しゃり");
        list[1].setValune(2);
        //3個目
        list[2].setName("納豆");
        list[2].setValune(20);
        //4個目
        list[3].setName("豚の生姜焼き");
        list[3].setValune(80);
        //5個目
        list[4].setName("じゃがいもと玉ねぎの味噌汁");
        list[4].setValune(40);
    }

    public void eatAll() {
        String str = "";
        for(Dish dish : list) {
            str += dish.getName() +"="+ dish.getValune() + ",";
        }
        str = str.replaceFirst(".$", "");
        System.out.println("たかしへ、ママです。今日の晩御飯は" + str + "よ");
    }

    public static void main(String[] args) {
        DinnerFullCource fullcourse = new DinnerFullCource();
        fullcourse.eatAll();
    }
}
