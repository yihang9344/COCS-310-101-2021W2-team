package com.a2;

import java.io.IOException;
import java.util.*;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class  dialog{
    private int label = -1;
    private final Map<String, String> map = new HashMap<String,String>();
    private final List<String> method = new ArrayList<String>();
    private final List<String> hospital = new ArrayList<String>();


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
        map.put("I have a headache recently.","You may have a cold.");
        map.put("I have a stomachache recently.","You may have gastritis");
        map.put("My heart hasn't been feeling well lately.","You should go to the hospital at once!");
        map.put("I've taken the medicine, but I'm still not feeling well.","I suggest you consult a doctor in the hospital.");
        map.put("I want to know the nearest hospital.",hospital.get(random.nextInt(4)));
        map.put("OK,thanks!","You're welcome!And you can leave by typing \"quit\"");
        //Second topic healthy
        map.put("I feel I am fat", "Maybe you should ask healthy question.");
        map.put("How to keep fit?","You need more exercise.");
        map.put("Could my cell phone kill me?","It seems unlikely. But if you use your mobile phone a lot, consider getting an earpiece or putting your caller on speaker so you can hold the phone away from your head.");
        map.put("Will vitamin D save my life?","A growing body of evidence strongly suggests that vitamin D in high doses not only helps keep bones strong but also reduces the risk of colon, ovarian, and breast cancers, and diseases such as diabetes and multiple sclerosis.");
        map.put("Is it okay to cleanse your body by fasting from time to time?","As long as you are in good health, a brief liquid fast or cleanse is fine.");
        map.put("Can I trust my tap water?","Sure. Unless you're on a private well, tap water comes from municipal treatment plants that are carefully monitored and better regulated than bottled water.");
        map.put("Is my microwave giving me cancer?","No. Microwaving doesn't alter food in any way that could make you sick. ");
        map.put("How long am I contagious when I have the flu or a cold?","As long as you have symptoms.");
        map.put("Is it true that 48 hours after starting antibiotics I can't infect someone else?","Yes, in most cases, and provided you really had a bacterial infection, like strep throat, and not a viral one—against which antibiotics are useless, says Schaffner.");
        map.put("Is bird flu still a danger?","Yes. As of this writing, influenza A virus subtype H5N1—bird flu—has not made an appearance in the United States.");
        map.put("How often do I really need to have my teeth professionally cleaned?","The answer depends on your habits at home, says periodontist Sally Cram, consumer adviser for the American Dental Association.");
        //5 outputs when users enter something different outsides the two topics
        map.put("Do you feel alone?","Are you bored?");
        map.put("What are you going to do?","I am trying to answer you.");
        map.put("How is your parent?","I do not have parent.");
        map.put("When will the hospital close?","You can check google.");
        map.put("How old are you?","Let's talk about your health.");
    }

    public String answer(String s){
        Set<String> keys = map.keySet();
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        CoreDocument coreDocument = new CoreDocument(s);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreSentence> sentences = coreDocument.sentences();
        List<CoreLabel> coreLabels = coreDocument.tokens();

        for(CoreSentence sentence : sentences){
            String sentiment = sentence.sentiment();
            if(sentiment.contains("Positive")){
                return "I am glad that you have a good day.";
            }
            else if (sentiment.contains("Negative")){
                return "What's Wrong with you?";
            }
        }

        for(CoreLabel coreLabel : coreLabels){
            String ner = coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class);
            String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            if (ner.contains("PERSON")){
                return "Hi, I am robot. How are you?";
            }
            if(pos.contains("UH")){
                return "Hi! How are you?";
            }
        }

        if (keys.contains(s)) {
            if (s.contains("headache"))
                this.label = 0;
            else if (s.contains("stomachache"))
                this.label = 1;
            else if (s.contains("heart"))
                this.label = 2;
            return map.get(s);
        }
        else if (s.equals("What should I do?")){
            if (this.label == -1)
                return "what's your symptoms?";
            else
                return method.get(this.label);
        }
        else if (s.equals("Do I need surgery?")){
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
