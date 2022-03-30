package oslomet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ChatBot {
    String name;
    String whatDoYouLikeToDoInYourSpareTime;
    String whatDayIsItToday;
    String letsEat;
    String letsActionNice;
    String letsActionBad;
    String letsActionDontExist;
    String oshi;
    String bye;
    int suggestionNice;
    String suggestion;
    String suggestion_ing;
    String module;
    String wantToSomethingOrSomething;
    String suggestion2;
    String suggestion_ing2;
    int niceSuggestion;
    boolean session;

    // General set get methods
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setWhatDoYouLikeToDoInYourSpareTime(String whatDoYouLikeToDoInYourSpareTime){
        this.whatDoYouLikeToDoInYourSpareTime = whatDoYouLikeToDoInYourSpareTime;
    }
    public String getWhatDoYouLikeToDoInYourSpareTime(){
        return whatDoYouLikeToDoInYourSpareTime;
    }

    public void setWhatDayIsItToday(String whatDayIsItToday){
        this.whatDayIsItToday = whatDayIsItToday;
    }
    public String getWhatDayIsItToday(){
        return whatDayIsItToday;
    }

    public void setLetsEat(String letsEat){
        this.letsEat = letsEat;
    }
    public String getLetsEat(){
        return letsEat;
    }

    public void setOshi(String oshi){
        this.oshi = oshi;
    }
    public String getOshi(){
        return oshi;
    }

    public void setBye(String bye){
        this.bye = bye;
    }
    public String getBye(){
        return bye;
    }

    public void setLetsActionNice(String acton){
        this.letsActionNice = acton;
    }
    public String getLetsActionNice(){
        return letsActionNice;
    }

    public void setLetsActionBad(String acton){
        this.letsActionBad = acton;
    }
    public String getLetsActionBad(){
        return letsActionBad;
    }

    public void setLetsActionDontExist(String action){
        this.letsActionDontExist = action;
    }
    public String getLetsActionDontExist(){
        return letsActionDontExist;
    }

    public String getSuggestion_ing(){
        return suggestion_ing;
    }

    public void setSuggestion(String innString){
        this.suggestion = innString;
        this.suggestion_ing = innString;
    }
    public String getSuggestion(){
        return suggestion;
    }

    public void setSuggestionNice(int innint){
        this.suggestionNice = innint;
    }
    public int getSuggestionNice(){
        return suggestionNice;
    }

    public void setSuggestion2(String innString){
        this.suggestion2 = innString;
    }
    public String getSuggestion2(){
        return suggestion2;
    }

    public void setSuggestion_ing2(String innString){
        this.suggestion_ing2 = innString;
    }
    public String getSuggestion_ing2(){
        return suggestion_ing2;
    }

    public void setNiceSuggestion(int innInt){
        this.niceSuggestion = innInt;
    }
    public int getNiceSuggestion(){
        return niceSuggestion;
    }

    public void setWantToSomethingOrSomething(String wantToSomethingOrSomething) {
        this.wantToSomethingOrSomething = wantToSomethingOrSomething;
    }
    public String getWantToSomethingOrSomething(){
        return wantToSomethingOrSomething;
    }

    public void setSession(boolean session){
        this.session = session;
    }
    public boolean getSession(){
        return session;
    }

    public String getModule(){
        return module;
    }

    // Updates the bots with new responses
    public void update(){
        if(getModule() == "nice"){
            this.setNiceResponses();
        }
        if(getModule() == "moody"){
            this.setMoodyResponses();
        }
        if(getModule() == "workaholic"){
            this.setWorkaholicResponses();
        }
        if(getModule() == "lazy"){
            this.setLazyResponses();
        }
    }

    // Updates bots response based on the users input
    public void letsSuggestionExist(String innString){
        // Removes the sentence to only leave the suggestion
        String inputSuggestion;
        if(innString.startsWith("lets go")){
            inputSuggestion = innString.replace("lets go ", "");
        }
        else {
            inputSuggestion = innString.replace("lets ", "");
        }

        // Making list of recognizable suggestions and sorting them in nice and bad morally
        List<String> niceSuggestions = new ArrayList<String>();
        niceSuggestions.add("dance");
        niceSuggestions.add("game");
        niceSuggestions.add("sing");
        niceSuggestions.add("sleep");
        List<String> badSuggestions = new ArrayList<String>();
        badSuggestions.add("fight");
        badSuggestions.add("steal");
        badSuggestions.add("molest");
        badSuggestions.add("bully");

        // Updates the suggestion, suggestioning and the bots responses
        if(niceSuggestions.contains(inputSuggestion)){// Updates the sentences based on the suggestion being morally good
            // Updates variables
            setSuggestionNice(1);// Updates the determining variable for whether a nice or bad moral response is given
            setSuggestion(inputSuggestion);// Updates the suggestion variabel for sentence building

            // Formating for ing
            char lastChar = inputSuggestion.charAt(inputSuggestion.length() -1);// Gets the last character of the suggestion
            if(lastChar == 'e'){// If the last character is e we need to remove it
                String suggestioning = inputSuggestion.substring(0, inputSuggestion.length() - 1);// Removes the last character which is e
                suggestioning = suggestioning+"ing";// Adds ing to the end of the word
                this.suggestion_ing = suggestioning;// Sets the word as suggestioning
            }
            else{// There is no e at the end so there is no need to remove it
                String suggestioning = inputSuggestion+"ing";// Puts ing at the end of the word
                this.suggestion_ing = suggestioning;// Sets the suggestioning as suggestioning
            }
        }
        else if(badSuggestions.contains(inputSuggestion)){// Updates the sentences based on the suggestion being morally bad
            // Updates variables
            setSuggestionNice(-1);// Updates the determining variable for whether a nice or bad moral response is given
            setSuggestion(inputSuggestion);// Sets the suggestion as suggestion for troubleshooting

            // Formating for ing
            char lastChar = inputSuggestion.charAt(inputSuggestion.length() -1);// Gets the last character of the suggestion
            if(lastChar == 'e'){// If the last character is e we need to remove it
                String suggestioning = inputSuggestion.substring(0, inputSuggestion.length() - 1);// Removes the last character which is e
                suggestioning = suggestioning+"ing";// Adds ing to the end of the word
                this.suggestion_ing = suggestioning;// Sets the word as suggestioning
            }
            else{// There is no e at the end so there is no need to remove it
                String suggestioning = inputSuggestion+"ing";// Puts ing at the end of the word
                this.suggestion_ing = suggestioning;// Sets the suggestioning as suggestioning
            }
        }
        else{// Word doesn't exist in lists
            setSuggestionNice(0);// Updates the determining variable for whether a nice or bad moral response is given
        }
        update();// Updates the bots with new responses based on the user input
    }

    public void wantToSomtingOrSomethingExists(String a, String b){
        // Removes the sentence to only leave the suggestion
        String aSuggestion;
        String bSuggestion;
        if(a.startsWith("i want to")){
            aSuggestion = a.replace("I want to ", "");
        }
        else {
            aSuggestion = a.replace("want to ", "");
        }
        if(b.startsWith("i want to")){
            bSuggestion = b.replace("want to ", "");
        }
        else {
            bSuggestion = b.replace("want to ", "");
        }

        // Making list of recognizable suggestions and sorting them in nice and bad morally
        List<String> niceSuggestions = new ArrayList<String>();
        niceSuggestions.add("dance");
        niceSuggestions.add("game");
        niceSuggestions.add("sing");
        niceSuggestions.add("sleep");
        List<String> badSuggestions = new ArrayList<String>();
        badSuggestions.add("fight");
        badSuggestions.add("steal");
        badSuggestions.add("molest");
        badSuggestions.add("bully");

        if(niceSuggestions.contains(aSuggestion) || niceSuggestions.contains(bSuggestion)){
            // Updates variables
            setSuggestionNice(1);// Updates the determining variable for whether a nice or bad moral response is given
            setSuggestion(aSuggestion);// Updates the suggestion variabel for sentence building
            setSuggestion2(bSuggestion);
            if(niceSuggestions.contains(aSuggestion)){
                setNiceSuggestion(1);
            }
            else{
                setNiceSuggestion(2);
            }

            // Formating for ing
            char lastChar = aSuggestion.charAt(aSuggestion.length() -1);// Gets the last character of the suggestion
            char lastChar2 = bSuggestion.charAt(bSuggestion.length() -1);// Gets the last character of the suggestion
            if(lastChar == 'e'){// If the last character is e we need to remove it
                if(niceSuggestions.contains(aSuggestion)) {
                    String suggestioning = aSuggestion.substring(0, aSuggestion.length() - 1);// Removes the last character which is e
                    suggestioning = suggestioning + "ing";// Adds ing to the end of the word
                    this.suggestion_ing = suggestioning;// Sets the word as suggestioning
                }
                else{
                    String suggestioning = aSuggestion.substring(0, aSuggestion.length() - 1);// Removes the last character which is e
                    suggestioning = suggestioning + "ing";// Adds ing to the end of the word
                    this.suggestion_ing = suggestioning;// Sets the word as suggestioning
                }
            }
            else{// There is no e at the end so there is no need to remove it
                if(niceSuggestions.contains(aSuggestion)){
                    String suggestioning = aSuggestion+"ing";// Puts ing at the end of the word
                    this.suggestion_ing = suggestioning;// Sets the suggestioning as suggestioning
                }
                else{
                    String suggestioning = aSuggestion+"ing";// Puts ing at the end of the word
                    this.suggestion_ing = suggestioning;// Sets the suggestioning as suggestioning
                }
            }
            if(lastChar2 == 'e'){// If the last character is e we need to remove it
                if(niceSuggestions.contains(bSuggestion)) {
                    String suggestioning = bSuggestion.substring(0, bSuggestion.length() - 1);// Removes the last character which is e
                    suggestioning = suggestioning + "ing";// Adds ing to the end of the word
                    this.suggestion_ing2 = suggestioning;// Sets the word as suggestioning
                }
                else{
                    String suggestioning = bSuggestion.substring(0, bSuggestion.length() - 1);// Removes the last character which is e
                    suggestioning = suggestioning + "ing";// Adds ing to the end of the word
                    this.suggestion_ing2 = suggestioning;// Sets the word as suggestioning
                }
            }
            else{// There is no e at the end so there is no need to remove it
                if(niceSuggestions.contains(bSuggestion)){
                    String suggestioning = bSuggestion+"ing";// Puts ing at the end of the word
                    this.suggestion_ing2 = suggestioning;// Sets the suggestioning as suggestioning
                }
                else{
                    String suggestioning = bSuggestion+"ing";// Puts ing at the end of the word
                    this.suggestion_ing2 = suggestioning;// Sets the suggestioning as suggestioning
                }
            }
        }
        else if(badSuggestions.contains(aSuggestion) || badSuggestions.contains(bSuggestion)){
            // Updates variables
            setSuggestionNice(-1);// Updates the determining variable for whether a nice or bad moral response is given
            setSuggestion(aSuggestion);// Updates the suggestion variabel for sentence building
            setSuggestion2(bSuggestion);
            if(badSuggestions.contains(aSuggestion)){
                setNiceSuggestion(-1);
            }
            else{
                setNiceSuggestion(-2);
            }

            // Formating for ing
            char lastChar = aSuggestion.charAt(aSuggestion.length() -1);// Gets the last character of the suggestion
            char lastChar2 = bSuggestion.charAt(bSuggestion.length() -1);// Gets the last character of the suggestion
            if(lastChar == 'e'){// If the last character is e we need to remove it
                if(badSuggestions.contains(aSuggestion)) {
                    String suggestioning = aSuggestion.substring(0, aSuggestion.length() - 1);// Removes the last character which is e
                    suggestioning = suggestioning + "ing";// Adds ing to the end of the word
                    this.suggestion_ing = suggestioning;// Sets the word as suggestioning
                }
                else{
                    String suggestioning = aSuggestion.substring(0, aSuggestion.length() - 1);// Removes the last character which is e
                    suggestioning = suggestioning + "ing";// Adds ing to the end of the word
                    this.suggestion_ing = suggestioning;// Sets the word as suggestioning
                }
            }
            else{// There is no e at the end so there is no need to remove it
                if(badSuggestions.contains(aSuggestion)){
                    String suggestioning = aSuggestion+"ing";// Puts ing at the end of the word
                    this.suggestion_ing = suggestioning;// Sets the suggestioning as suggestioning
                }
                else{
                    String suggestioning = aSuggestion+"ing";// Puts ing at the end of the word
                    this.suggestion_ing = suggestioning;// Sets the suggestioning as suggestioning
                }
            }
            if(lastChar2 == 'e'){// If the last character is e we need to remove it
                if(badSuggestions.contains(bSuggestion)) {
                    String suggestioning = bSuggestion.substring(0, bSuggestion.length() - 1);// Removes the last character which is e
                    suggestioning = suggestioning + "ing";// Adds ing to the end of the word
                    this.suggestion_ing2 = suggestioning;// Sets the word as suggestioning
                }
                else{
                    String suggestioning = bSuggestion.substring(0, bSuggestion.length() - 1);// Removes the last character which is e
                    suggestioning = suggestioning + "ing";// Adds ing to the end of the word
                    this.suggestion_ing2 = suggestioning;// Sets the word as suggestioning
                }
            }
            else{// There is no e at the end so there is no need to remove it
                if(badSuggestions.contains(bSuggestion)){
                    String suggestioning = bSuggestion+"ing";// Puts ing at the end of the word
                    this.suggestion_ing2 = suggestioning;// Sets the suggestioning as suggestioning
                }
                else{
                    String suggestioning = bSuggestion+"ing";// Puts ing at the end of the word
                    this.suggestion_ing2 = suggestioning;// Sets the suggestioning as suggestioning
                }
            }
        }
        else{// The words doesn't exist in the lists
            setSuggestionNice(0);// Updates the determining variable for whether a nice or bad moral response is given
            setNiceSuggestion(0);// Tells which suggestion is nice
        }
        update();// Updates the bots with new responses based on the user input
    }

    public String getResponse(String response){
        // RegEX
        String noPunct = response.replaceAll("\\p{Punct}", "");
        String toLowerCase = noPunct.toLowerCase();
        //System.out.println(toLowerCase);

        // System.out.println for bots responses to the user's input
        if(toLowerCase.equals("what do you like to do in your spare time") || toLowerCase.equals("what do you like to do")){
            return getWhatDoYouLikeToDoInYourSpareTime();
        }
        else if(toLowerCase.equals("what day is it today") || toLowerCase.equals("what day is it")){
            return getWhatDayIsItToday();
        }
        else if(toLowerCase.equals("lets eat")){
            return getLetsEat();
        }
        else if(toLowerCase.equals("oshi")){
            return getOshi();
        }
        else if(toLowerCase.equals("bye") || toLowerCase.equals("good bye") || toLowerCase.equals("bye bye") || toLowerCase.equals("farewell")){
            return getBye();
        }
        else if(toLowerCase.equals("options")){
            String toReturn = "What do you like to do in your spare time?\n"+
                    "What day is it today?\n"+
                    "Let's eat.\n"+
                    "Let's go 'something'\n"+
                    "Bye";
            return toReturn;
        }
        else if(toLowerCase.startsWith("lets ") && !toLowerCase.equals("lets eat")){
            this.letsSuggestionExist(toLowerCase);
            if(this.getSuggestionNice() == 1){
                return getLetsActionNice();
            }
            else if(this.getSuggestionNice() == -1){
                return getLetsActionBad();
            }
            else{
                return getLetsActionDontExist();
            }
        }
        else{
            return "You can type options for conversation options or bye to end the conversation.";
        }
    }

    // Set nice response
    public void setNiceResponses(){
        if(getName() == null){// Response needs names for formatting
            System.out.println("Name needs to be assigned first");
            throw new NullPointerException();
        }
        else{
            List<String> niceSuggestions = new ArrayList<String>();
            niceSuggestions.add("dance");
            niceSuggestions.add("game");
            niceSuggestions.add("sing");
            niceSuggestions.add("sleep");
            int randomNum = ThreadLocalRandom.current().nextInt(0, 3 + 1);
            Date today = Calendar.getInstance().getTime();// Gets todays time for response
            this.module = "nice";// Sets bots module for response updating
            this.setWhatDoYouLikeToDoInYourSpareTime("I like to do a variety of charity work such as picking up trash or taking of abandoned animals at the animal shelter!");;
            this.setWhatDayIsItToday("The time? It is exactly "+today);
            this.setLetsEat("Oh, I brought a healthy and delicious lunchbox with me that we can share!");;
            this.setOshi("Rikka!");;
            this.setBye("Bah BYE! Let's meet again!");;
            if(suggestionNice != 0) {// For when there is a suggestion from user
                this.setLetsActionNice("That's a great idea! Let's go " + getSuggestion_ing() + "!");;
                this.setLetsActionBad("That's a bad idea... I'll have to punish you if you go " + getSuggestion_ing() + ".");;
                if(niceSuggestion == 1){// Nice suggestion is suggestion
                    this.setWantToSomethingOrSomething(""+getSuggestion_ing()+" sounds nice!");;
                }
                else if(niceSuggestion == 2){// Nice suggestion is suggestion2
                    this.setWantToSomethingOrSomething(""+getSuggestion_ing2()+" sounds nice!");;
                }
                else if(niceSuggestion == -1){// Bad suggestion is suggestion
                    this.setWantToSomethingOrSomething(""+getSuggestion_ing()+" sounds bad... How about we instead "+niceSuggestions.get(randomNum)+"?");;
                }
                else{// Bad suggestion is suggestion 2
                    this.setWantToSomethingOrSomething(""+getSuggestion_ing2()+" sounds bad... How about we instead "+niceSuggestions.get(randomNum)+"?");;
                }
            }
            else{
                this.setLetsActionDontExist("Sorry, dunno what that is.");;
                this.setWantToSomethingOrSomething("Sorry, dunno what those are.");;
            }
        }
    }

    // Set moody response
    public void setMoodyResponses(){
        if(getName() == null){// Response needs names for formatting
            System.out.println("Name needs to be assigned first");
            throw new NullPointerException();
        }
        else {
            Date today = Calendar.getInstance().getTime();// Gets todays time for response
            this.module = "moody";// Sets bots module for response updating
            this.setWhatDoYouLikeToDoInYourSpareTime("None of your business...");
            this.setWhatDayIsItToday("You've got a phone, right? Why don't you check yourself...");
            this.setLetsEat("Why would I eat with you?");
            this.setOshi("...Tokoyami Towa");
            this.setBye("...What? Just leave.");
            if(suggestionNice != 0){// For when there is a suggestion from user
                this.setLetsActionNice("Why would I " + getSuggestion() + " with you?");
                this.setLetsActionBad("Why don't you go " + getSuggestion_ing() + " by yourself?");
                if(niceSuggestion == 1 || niceSuggestion == 2 || niceSuggestion == -1 || niceSuggestion == -2){// Nice suggestion is suggestion
                    this.setWantToSomethingOrSomething("Oh wow, "+getSuggestion_ing()+" sounds nice! Or maybe "+getSuggestion2()+" is better? How about you go and spend the day doing those away from me.");
                }
            }
            else{
                this.setLetsActionDontExist("I don't know what that is and even if I did, I wouldn't do it with you so leave.");
                this.setWantToSomethingOrSomething("I don't know what that is and even if I did, I wouldn't do it with you so leave.");
            }
        }
    }

    // Set workaholic response
    public void setWorkaholicResponses() {
        if (getName() == null) {
            System.out.println("Name needs to be assigned first");
            throw new NullPointerException();
        } else {
            Date today = Calendar.getInstance().getTime();// Gets todays time for response
            this.module = "workaholic";// Sets bots module for response updating
            this.setWhatDoYouLikeToDoInYourSpareTime("Spare time? There's no spare time, just work. Do more work!");
            this.setWhatDayIsItToday("It is exactly " + today + " and we are 1 minute and 3 seconds behind schedule so hurry up!");
            this.setLetsEat("We don't provide lunch breaks. Just order something and eat while working.");;
            this.setOshi("Hoshimachi Suisei and Elite Miko. It's just business.");;
            this.setBye("Bye? There's much work left to do, you're not leaving. Hmm? Wait, GET YOUR BEHIND BACK HERE!!");
            if (suggestionNice != 0) {// For when there is a suggestion from user
                this.setLetsActionNice("Sure, we can " + getSuggestion() + ", but only after we're done working.");
                this.setLetsActionBad("If you have time to think about going " + getSuggestion_ing() + " then I need to assign you more work!");
                if(niceSuggestion == 1){// Nice suggestion is suggestion
                    this.setWantToSomethingOrSomething("We can go "+getSuggestion_ing()+" after work.");
                }
                else if(niceSuggestion == 2){// Nice suggestion is suggestion2
                    this.setWantToSomethingOrSomething(""+getSuggestion2()+" can come later. For now we work.");
                }
                else if(niceSuggestion == -1){// Bad suggestion is suggestion
                    this.setWantToSomethingOrSomething(""+getSuggestion()+"? How about you focus on work instead?");
                }
                else{// Bad suggestion is suggestion 2
                    this.setWantToSomethingOrSomething(""+getSuggestion2()+"? How about you focus on work instead?");
                }
            }
            else{
                this.setLetsActionDontExist("Don't know what that is. Explain in under 20 seconds- Actually I've got to take a call. We'll come back to this later.");
                this.setWantToSomethingOrSomething("Don't know what those are. Explain in under 20 seconds- Actually I've got to take a call. We'll come back to this later.");
            }
        }
    }

    // Set lazy response
    public void setLazyResponses(){
        if(getName() == null){// Response needs names for formatting
            System.out.println("Name needs to be assigned first");
            throw new NullPointerException();
        }
        else {
            Date today = Calendar.getInstance().getTime();// Gets todays time for response
            this.module = "lazy";// Sets bots module for response updating
            this.setWhatDoYouLikeToDoInYourSpareTime("Me? I like sleeping, napping, stargazing, cloud gazing, ceiling gazing... zzZ");
            this.setWhatDayIsItToday("The day? Let me just get up and check. Here WE GO! ...zzZ");
            this.setLetsEat("Wanna order something? Don't feel like going out or making nothin'.");
            this.setOshi("I've got a lot. There's Inui Toko, Kiryu Coco... zZZ");;
            this.setBye("zZZ");;
            if (suggestionNice != 0) {// For when there is a suggestion from user
                this.setLetsActionNice("Sure, " + getSuggestion_ing() + " sounds nice.");
                this.setLetsActionBad("To be honest, I'd rather sleep than " + getSuggestion_ing() + ".");
                if(niceSuggestion == 1){// Nice suggestion is suggestion
                    this.setWantToSomethingOrSomething("Sure, we can go "+getSuggestion_ing()+", but only if you can get me out of bed.");
                }
                else if(niceSuggestion == 2){// Nice suggestion is suggestion2
                    this.setWantToSomethingOrSomething(""+getSuggestion2()+" sounds nice, but I'd rather- zZZ.");
                }
                else if(niceSuggestion == -1){// Bad suggestion is suggestion
                    this.setWantToSomethingOrSomething("Why "+getSuggestion()+" when you can sleep?");
                }
                else{// Bad suggestion is suggestion 2
                    this.setWantToSomethingOrSomething("Why "+getSuggestion2()+" when you can stay in bed?");
                }
            }
            else{
                this.setLetsActionDontExist("Don't know what that is and I'll fall asleep before you finish explaining- zZZ.");
                this.setWantToSomethingOrSomething("Don't know what those are and I'll fall asleep before you finish explaining- zZZ.");
            }
        }
    }
}
