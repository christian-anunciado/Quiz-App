package com.codecademy.quizapp;

import java.util.ArrayList;
import java.util.List;

public class QuestionList {

    List<Questions> historyQuestions = new ArrayList<>();
    List<Questions> foodQuestions = new ArrayList<>();
    List<Questions> bugtongQuestions = new ArrayList<>();
    List<Questions> geographyQuestions = new ArrayList<>();
    List<Questions> randomQuestions = new ArrayList<>();

    public List<Questions> getQuestions(int category) {

        if (category == 1) {
            addBugtongQuestions();
            return bugtongQuestions;
        }
        if (category == 2) {
            addFoodQuestions();
            return foodQuestions;
        }
        if (category == 3) {
            addHistoryQuestions();
            return historyQuestions;
        }
        if (category == 4) {
            addGeographyQuestions();
            return geographyQuestions;
        }
        if (category == 5) {
            addRandomQuestions();
            return randomQuestions;
        }
        return null;
    }

    void addBugtongQuestions() {
        bugtongQuestions.add(new Questions(1,"Kung kailan mo pinatay, saka pa humaba ang buhay",
                "Kandila",
                "Anino",
                "Apoy",
                "Langgam",
                1));

        bugtongQuestions.add(new Questions(1,"Isang butil ng palay, sakot ang buong buhay",
                "Mata",
                "Kuliglig",
                "Ilaw",
                "Kidlat",
                3));

        bugtongQuestions.add(new Questions(1,"Ako ay may kaibigan, kasama ko kahit saan",
                "Multo",
                "Anino",
                "Hangin",
                "Selpon",
                2));

        bugtongQuestions.add(new Questions(1,"Dumaan ang hari, nagkagatan ang mga pari",
                "Sundalo",
                "Ibon",
                "Langgam",
                "Zipper",
                4));

        bugtongQuestions.add(new Questions(1,"Dalawang batong itim, malayo ang nararating",
                "Mata",
                "Bato",
                "Holen o Marbles",
                "Wala sa nabanggit",
                1));

        bugtongQuestions.add(new Questions(1,"Kay lapit-lapit na sa mata, di mo pa rin makita",
                "Ilong",
                "Bibig",
                "Tenga",
                "Buhok",
                3));

        bugtongQuestions.add(new Questions(1,"Sa maling kalabit, may buhay na kapalit",
                "Bato",
                "Baril",
                "Paputok",
                "Wala sa nabanggit",
                2));

        bugtongQuestions.add(new Questions(1,"Sa buhatan ay may silbi, sa igiban ay walang sinabi",
                "Bag",
                "Pitaka",
                "Bayong o Basket",
                "Wala sa nabanggit",
                3));

        bugtongQuestions.add(new Questions(1,"Hinila ko ang baging, sumigaw ang matsing",
                "Kampana",
                "Paputok",
                "Orasan",
                "Wala sa nabanggit",
                1));

        bugtongQuestions.add(new Questions(1,"Yumuko man ang reyna, di malalaglag ang korona",
                "Bayabas",
                "Kamote",
                "Saging",
                "Wala sa nabanggit",
                1));

        bugtongQuestions.add(new Questions(1,"Kung tawagin nila ay “Santo” pero hindi naman ito milagroso",
                "Saging",
                "Kalabasa",
                "Santol",
                "Wala sa nabanggit",
                3));

        bugtongQuestions.add(new Questions(1,"Maliit na bahay, puno ng mga patay",
                "Kabaong",
                "Puntod",
                "Posporo",
                "Wala sa nabanggit",
                3));

        bugtongQuestions.add(new Questions(1,"Tubig na nagiging bato, batong nagiging tubig",
                "Asin",
                "Toyo",
                "Asukal",
                "Suka",
                1));

        bugtongQuestions.add(new Questions(1,"Bumili ako ng alipin, mas mataas pa sa akin",
                "Bayong",
                "Bisikleta",
                "Sombrero",
                "Wala sa nabanggit",
                3));

        bugtongQuestions.add(new Questions(1,"May balbas ngunit walang mukha",
                "Saging",
                "Mais",
                "Niyog",
                "Wala sa nabanggit",
                2));

        bugtongQuestions.add(new Questions(1,"Isang hukbo ng sundalo, dikit-dikit ang mga ulo",
                "Lampaso",
                "Dahon ng niyog",
                "Walis",
                "Wala sa nabanggit",
                3));

        bugtongQuestions.add(new Questions(1,"Kaisa-isang plato, kita sa buong Mundo",
                "Buwan",
                "Orasan",
                "Ilaw",
                "Wala sa nabanggit",
                1));

        bugtongQuestions.add(new Questions(1,"Ihagis mo man kahit saan, sadyang babalik at babalik sa pinanggalingan",
                "Bola",
                "Yoyo",
                "Saranggola",
                "Wala sa nabanggit",
                2));

        bugtongQuestions.add(new Questions(1,"Isa ang pasukan, tatlo ang labasan",
                "Bintana",
                "Bahay",
                "Kamiseta",
                "Wala sa nabanggit",
                3));

    }

    void addFoodQuestions() {
        foodQuestions.add(new Questions(2,"Bacolod is famous for their round, flat, flaky unleavened delicacy filled with brown sugar and is called?",
                "Biko",
                "Kalamay",
                "Piaya",
                "Binagol",
                3));

        foodQuestions.add(new Questions(2,"Leyte is known for their delicacy that is packed into half of a coconut shell then wrapped in banana leaf, the savory treat inside is made of sweetened mashed taro. This delicacy is called?",
                "Binagol",
                "Sapin-sapin",
                "Maja Blanca",
                "Kalamay",
                1));

        foodQuestions.add(new Questions(2,"This sweet delicacy is found all over the Philippines, it is usually eaten as a snack or dessert. Its a rice cake made from sticky rice, coconut milk and brown sugar, and it is called?",
                "Kalamay",
                "Biko",
                "Turon",
                "Piaya",
                2));

        foodQuestions.add(new Questions(2,"A delicacy famous in Leyte which is made of sticky rice cake and chocolate, wrapped in banana leaves in the form of a stick. It has a lot of names but in Leyte, they call this treat as?",
                "Biko",
                "Kalamay",
                "Piaya",
                "Binagol",
                3));

        foodQuestions.add(new Questions(2,"This delicacy from Bohol is made from coconut milk, brown sugar, ground glutinous rice and stored in a coconut shell. Some prefer to scoop this treat with a spoon and some spread it onto a piece of bread, this sweet delicacy is known as?",
                "Kalamay",
                "Biko",
                "Piaya",
                "Moron",
                1));

        foodQuestions.add(new Questions(2,"This exotic delicacy is sold by age of the chick, found almost anywhere in the Philippines. It is basically a boiled undeveloped duck egg. These are usually sold at night and is called?",
                "Buwad",
                "Balut",
                "Boiled egg",
                "Binagol",
                2));

        foodQuestions.add(new Questions(2,"Carcar City in Cebu is famous for this crunchy delicacy made of fried pork skin. It comes in two varieties: purely skin and with meat. This crunchy treat is best eaten with vinegar and it is called?",
                "Siomai",
                "Lechon",
                "Piaya",
                "Chicharon",
                4));

        foodQuestions.add(new Questions(2,"This famous snack in the Philippines is made of fried banana, jack fruit(langka) and sugar wrapped in lumpia wrapper. It is a staple snack found anywhere in the Philippines and is called?",
                "Turon",
                "Lumpia Shanghai",
                "Banana cue",
                "Camote cue",
                1));

        foodQuestions.add(new Questions(2,"This popular Filipino cold dessert which is made up of crushed ice, evaporated milk and various ingredients, including but not limited to: ube, sweetened beans, banana, tapioca pearls, jack fruit, coconut strips, fruit slices and etc., is called?",
                "Sapin-sapin",
                "Pinipig",
                "Halo-halo",
                "Ice buko",
                3));

        foodQuestions.add(new Questions(2,"This delicacy is a layered colorful glutinous rice found anywhere in the Philippines. It is made from rice flour, coconut milk, sugar, water, flavoring and coloring. It is usually sprinkled with latik or toasted desiccated coconut flakes. It is called as?",
                "Binagol",
                "Sapin-sapin",
                "Leche flan",
                "Maja Blanca",
                2));

        foodQuestions.add(new Questions(2,"It is a Filipino dessert made primarily from coconut milk. It is usually topped with peanuts and served  during fiestas and holidays. Also known as Coconut pudding but is called as?",
                "Maja Blanca",
                "Kalamay",
                "Leche flan",
                "Cassava Cake",
                1));
    }

    void addHistoryQuestions() {

        historyQuestions.add(new Questions(3, R.drawable.question1,
                "What continent is the Philippines in?",
                "March 15th, 1895",
                "November 12th, 1704",
                "February 16th, 1805",
                "June 12th, 1898",
                1));

        historyQuestions.add(new Questions(3, R.drawable.question2,
                "When was the Philippines founded?",
                "March 15th, 1895",
                "November 12th, 1704",
                "February 16th, 1805",
                "June 12th, 1898",
                1));

        historyQuestions.add(new Questions(3, R.drawable.question3,
                "What religion is more prominent in the Philippines?",
                "Christianity",
                "Muslim",
                "Buddhism",
                "Roman Catholic",
                1));

        historyQuestions.add(new Questions(3, R.drawable.question4,
                "How many meals a does normal person in the Philippines eat a day?",
                "2",
                "3",
                "4",
                "5",
                1));

        historyQuestions.add(new Questions(3, R.drawable.question5,
                "What food is this?",
                "Sinigang",
                "Adobo",
                "Kare-kare",
                "Kutsinta",
                1));

        historyQuestions.add(new Questions(3, R.drawable.question6,
                "What food is this?",
                "Ensaymada",
                "Mamon",
                "Kare-kare",
                "Kutsinta",
                1));

        historyQuestions.add(new Questions(3, R.drawable.question7,
                "What food is this?",
                "Cassava Cake",
                "Taisan",
                "Cornbread Bibingka",
                "Jelly Dessert",
                1));

        historyQuestions.add(new Questions(3, R.drawable.question8,
                "What food is this?",
                "Ube",
                "Leche Plan",
                "Sinigang",
                "Kutsinta",
                1));

        historyQuestions.add(new Questions(3, R.drawable.question9,
                "What utensil is palayok?",
                "A clay pot",
                "A mortar",
                "A bowl",
                "A pestle",
                1));

        historyQuestions.add(new Questions(3,"What utensil is palayok?",
                "A clay pot",
                "A mortar",
                "A bowl",
                "A pestle",
                1));

        historyQuestions.add(new Questions(3,"What utensil is palayok?",
                "A clay pot",
                "A mortar",
                1));

    }

    void addGeographyQuestions() {
        geographyQuestions.add(new Questions(4, "What city is home to the famous Rizal Park and is the capital of the Philippines as well?",
                "Manila",
                "Cebu",
                "Baguio",
                "Davao",
                1));

        geographyQuestions.add(new Questions(4, "What city is known as the \"Summer Capital of the Philippines\"?",
                "Boracay",
                "Bohol",
                "Baguio",
                "Bacolod",
                3));

        geographyQuestions.add(new Questions(4, "Oldest Philippine City",
                "Davao",
                "Cebu",
                "Manila",
                "Vigan",
                2));

        geographyQuestions.add(new Questions(4, "Dumaan ang hari, nagkagatan ang mga pari",
                "Sundalo",
                "Ibon",
                "Langgam",
                "Zipper",
                4));

        geographyQuestions.add(new Questions(4, "Dalawang batong itim, malayo ang nararating",
                "Mata",
                "Bato",
                "Holen o Marbles",
                "Wala sa nabanggit",
                1));

        geographyQuestions.add(new Questions(4, "Kay lapit-lapit na sa mata, di mo pa rin makita",
                "Ilong",
                "Bibig",
                "Tenga",
                "Buhok",
                3));

        geographyQuestions.add(new Questions(4, "Sa maling kalabit, may buhay na kapalit",
                "Bato",
                "Baril",
                "Paputok",
                "Wala sa nabanggit",
                2));

        geographyQuestions.add(new Questions(4, "Sa buhatan ay may silbi, sa igiban ay walang sinabi",
                "Bag",
                "Pitaka",
                "Bayong o Basket",
                "Wala sa nabanggit",
                3));

        geographyQuestions.add(new Questions(4, "Hinila ko ang baging, sumigaw ang matsing",
                "Kampana",
                "Paputok",
                "Orasan",
                "Wala sa nabanggit",
                1));

        geographyQuestions.add(new Questions(4, "Yumuko man ang reyna, di malalaglag ang korona",
                "Bayabas",
                "Kamote",
                "Saging",
                "Wala sa nabanggit",
                1));

        geographyQuestions.add(new Questions(4, "Kung tawagin nila ay “Santo” pero hindi naman ito milagroso",
                "Saging",
                "Kalabasa",
                "Santol",
                "Wala sa nabanggit",
                3));

        geographyQuestions.add(new Questions(4,"Maliit na bahay, puno ng mga patay",
                "Kabaong",
                "Puntod",
                "Posporo",
                "Wala sa nabanggit",
                3));

        geographyQuestions.add(new Questions(4,"Tubig na nagiging bato, batong nagiging tubig",
                "Asin",
                "Toyo",
                "Asukal",
                "Suka",
                1));
    }

    void addRandomQuestions() {
        addFoodQuestions();
        addBugtongQuestions();
        addGeographyQuestions();
        addHistoryQuestions();
        randomQuestions.addAll(geographyQuestions);
        randomQuestions.addAll(foodQuestions);
        randomQuestions.addAll(bugtongQuestions);
        randomQuestions.addAll(historyQuestions);
    }

}
