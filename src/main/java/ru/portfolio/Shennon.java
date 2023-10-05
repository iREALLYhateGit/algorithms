package ru.portfolio;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Shennon {
    static String text = "Одеяло\n" + "Убежало,\n" + "Улетела простыня,\n" +
            "И подушка,\n" + "Как лягушка,\n" + "Ускакала от меня.\n" + "Я за свечку,\n" +
            "Свечка — в печку!\n" + "Я за книжку,\n" + "Та — бежать\n" + "И вприпрыжку\n" + "Под кровать!\n" +
            "Я хочу напиться чаю,\n" + "К самовару подбегаю,\n" + "Но пузатый от меня\n" + "Убежал, как от огня.\n" +
            "Что такое?\n" + "Что случилось?\n" + "Отчего же\n" + "Всё кругом\n" + "Завертелось,\n" + "Закружилось\n" +
            "И помчалось колесом?\n" + "Утюги за сапогами,\n" + "Сапоги за пирогами,\n" + "Пироги за утюгами,\n" +
            "Кочерга за кушаком —\n" + "Всё вертится,\n" + "И кружится,\n" + "И несётся кувырком.\n" +
            "Вдруг из маминой из спальни,\n" + "Кривоногий и хромой,\n" + "Выбегает умывальник\n" +
            "И качает головой:\n" + "«Ах ты, гадкий, ах ты, грязный,\n" + "Неумытый поросёнок!\n" +
            "Ты чернее трубочиста,\n" + "Полюбуйся на себя:\n" + "У тебя на шее вакса,\n" +
            "У тебя под носом клякса,\n" + "У тебя такие руки,\n" + "Что сбежали даже брюки,\n" +
            "Даже брюки, даже брюки\n" + "Убежали от тебя.\n" + "Рано утром на рассвете\n" +
            "Умываются мышата,\n" + "И котята, и утята,\n" + "И жучки, и паучки.\n" + "Ты один не умывался\n" +
            "И грязнулею остался,\n" + "И сбежали от грязнули\n" + "И чулки и башмаки.\n" + "Я — Великий Умывальник,\n" +
            "Знаменитый Мойдодыр,\n" + "Умывальников Начальник\n" + "И мочалок Командир!\n" +
            "Если топну я ногою,\n" + "Позову моих солдат,\n" + "В эту комнату толпою\n" +
            "Умывальники влетят,\n" +
            "И залают, и завоют,\n" +
            "И ногами застучат,\n" +
            "И тебе головомойку,\n" + "Неумытому, дадут —\n" + "Прямо в Мойку,\n" +
            "Прямо в Мойку\n" + "С головою окунут!»\n" +
            "Он ударил в медный таз\n" + "И вскричал: «Кара-барас!»\n" +
            "И сейчас же щетки, щетки\n" + "Затрещали, как трещотки,\n" +
            "И давай меня тереть,\n" + "Приговаривать:\n" + "«Моем, моем трубочиста\n" +
            "Чисто, чисто, чисто, чисто!\n" + "Будет, будет трубочист\n" +
            "Чист, чист, чист, чист!»\n" + "Тут и мыло подскочило\n" + "И вцепилось в волоса,\n" + "И юлило, и мылило,\n" +
            "И кусало, как оса.\n" +
            "А от бешеной мочалки\n" + "Я помчался, как от палки,\n" +
            "А она за мной, за мной\n" +
            "По Садовой, по Сенной.\n" + "Я к Таврическому саду,\n" +
            "Перепрыгнул чрез ограду,\n" + "А она за мною мчится\n" +
            "И кусает, как волчица.\n" + "Вдруг навстречу мой хороший,\n" +
            "Мой любимый Крокодил.\n" +
            "Он с Тотошей и Кокошей\n" + "По аллее проходил\n" +
            "И мочалку, словно галку,\n" +
            "Словно галку, проглотил.\n" +
            "А потом как зарычит\n" +
            "На меня,\n" +
            "Как ногами застучит\n" +
            "На меня:\n" + "«Уходи-ка ты домой,\n" +
            "Говорит,\n" + "Да лицо своё умой,\n" +
            "Говорит,\n" + "А не то как налечу,\n" + "Говорит,\n" + "Растопчу и проглочу!»\n" + "Говорит.\n" +
            "Как пустился я по улице\n" + "бежать,\n" +
            "Прибежал я к умывальнику\n" + "опять.\n" + "Мылом, мылом\n" +
            "Мылом, мылом\n" + "Умывался без конца,\n" +
            "Смыл и ваксу\n" + "И чернила\n" + "С неумытого лица.\n" +
            "И сейчас же брюки, брюки\n" + "Так и прыгнули мне в руки.\n" +
            "А за ними пирожок:\n" + "«Ну-ка, съешь меня, дружок!»\n" +
            "А за ним и бутерброд:\n" + "Подскочил — и прямо в рот!\n" +
            "Вот и книжка воротилась,\n" + "Воротилася тетрадь,\n" +
            "И грамматика пустилась\n" + "С арифметикой плясать.\n" +
            "Тут Великий Умывальник,\n" + "Знаменитый Мойдодыр,\n" +
            "Умывальников Начальник\n" + "И мочалок Командир,\n" + "Подбежал ко мне, танцуя,\n" +
            "И, целуя, говорил:\n" + "«Вот теперь тебя люблю я,\n" + "Вот теперь тебя хвалю я!\n" +
            "Наконец-то ты, грязнуля,\n" +
            "Мойдодыру угодил!»\n" + "Надо, надо умываться\n" + "По утрам и вечерам,\n" +
            "А нечистым\n" + "Трубочистам —\n" + "Стыд и срам!\n" + "Стыд и срам!\n" + "Да здравствует мыло душистое,\n" +
            "И полотенце пушистое,\n" + "И зубной порошок,\n" +
            "И густой гребешок!\n" + "Давайте же мыться, плескаться,\n" +
            "Купаться, нырять, кувыркаться\n" +
            "В ушате, в корыте, в лохани,\n" + "В реке, в ручейке, в океане, —\n" +
            "И в ванне, и в бане,\n" +
            "Всегда и везде —\n" + "Вечная слава воде!";
    static Integer counter = 0;
    static double countLog(double x)
    {
        return x*(Math.log(x) / Math.log(2));
    }
    static double findShennon(HashMap<Character, Double> hash) {
        double sum = 0;
        for(double p : hash.values()){
            sum+= countLog(p);
        }
        return -sum;
    }
    public static void main(String[] args) {
        HashMap<Character, Double> freq = getFrequency();
        //System.out.println("Dictionary with character frequency:  " + freq); //another way to sout info
        int i = 0;
        for(Map.Entry<Character,Double> entry : freq.entrySet()){
            i++;
            System.out.print(entry.getKey() + "=" + entry.getValue() + "   ");
            if(i % 5 == 0)
                System.out.println();
        }
        i =0;
        System.out.print("\nDictionary: ");
        for(Character key : freq.keySet()){
            i++;
            if(i == 25)
                System.out.println();
            if(i == 1 || i == 25){
                System.out.print(key);
            }
            else
                System.out.print(", " + key);
        }
        System.out.print(".");
        //System.out.println("\nDictionary:  " + freq.keySet()); //another way to sout info
        System.out.println("\nDictionary size:  " + freq.keySet().size());
        System.out.println("Volume of one symbol: " + findShennon(freq));
    }

    /**
     *
     * @param mass  - String modified into character array
     * @return hashmap representing the frequency of alphabetic symbols inside the String
     */
    public static HashMap<Character, Integer> getAlphabet(char mass[]){
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for(Character current : mass){
            if(Character.isAlphabetic(current) || current == ' '){
                counter ++;
                if(hashMap.containsKey(current))
                    hashMap.replace(current, hashMap.get(current) + 1);
                else
                    hashMap.put(current, 1);
            }
        }
        return hashMap;
    }
    public static HashMap<Character, Double> getFrequency(){
        HashMap<Character, Integer> hash = getAlphabet(text.toCharArray());
        HashMap<Character, Double> freq = new HashMap<>();
        try {
            if(counter == 0)
                throw new ArithmeticException();
            for (Map.Entry<Character, Integer> map : hash.entrySet()) {
                freq.put(map.getKey(), ((double) map.getValue()) / counter);
            }
        }catch(Exception ex){
            System.out.println("division by zero");
        }
        return freq;
    }
}
