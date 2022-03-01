import java.util.*;

public class dialog {
    private int label = -1;
    private final Map<String, String> map = new HashMap<>();
    private final List<String> method = new ArrayList<>();
    private final List<String> hospital = new ArrayList<>();


    public void initial(){
        Random random = new Random();
        String function = "I can judge your illness by your symptoms.\n" +
                "I can provide corresponding measures through your illness.";

        method.add("You can take some cold medicine appropriately.");
        method.add("You can take some stomach medicine appropriately.");
        method.add("You should go to the hospital at once!");

        hospital.add("Central Hospital");
        hospital.add("Municipal Hospital");
        hospital.add("Seventh Hospital");
        hospital.add("The Third Hospital");

        map.put("hi","Hello, can I help you?");
        map.put("Hi","Hello, can I help you?");
        map.put("hello","Hello, can I help you?");
        map.put("Hello","Hello, can I help you?");
        map.put("Could you help me?","Of course, " + function);
        map.put("What services can you provide?", function);
        map.put("I'm not feeling well recently.","What's wrong with you?");
        map.put("I have a headache recently.","You may have a cold.");
        map.put("I have a stomachache recently.","You may have gastritis");
        map.put("My heart hasn't been feeling well lately.","You should go to the hospital at once!");
        map.put("I've taken the medicine, but I'm still not feeling well.","I suggest you consult a doctor in the hospital.");
        map.put("I want to know the nearest hospital.",hospital.get(random.nextInt(4)));
        map.put("OK,thanks!","You're welcome!And you can leave by typing \"quit\"");
//        map.put("quit", "I'm glad to serve you. See you next time!");
    }

    public String answer(String question){
        Set<String> keys = map.keySet();
        if (keys.contains(question)) {
            if (question.contains("headache"))
                this.label = 0;
            else if (question.contains("stomachache"))
                this.label = 1;
            else if (question.contains("heart"))
                this.label = 2;
            return map.get(question);
        }
        else if (question.equals("What should I do?")){
            if (this.label == -1)
                return "what's your symptoms?";
            else
                return method.get(this.label);
        }
        else if (question.equals("Do I need surgery?")){
            if (this.label == 2)
                return "Maybe, it's up to doctor.";
            else if (this.label == -1)
                return "what's your symptoms?";
            else
                return "I don't think so.";
        }
        else
            return "sorry, I can't understand your question.";
    }
}
