package br.com.oxxynet.chatoxxy.fixture;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import br.com.oxxynet.chatoxxy.model.Message;
import br.com.oxxynet.chatoxxy.model.User;

public final class MessagesFixtures extends FixturesData {

    private MessagesFixtures() {
        throw new AssertionError();
    }

//    public static Message getImageMessage() {
//        Message message = new Message(getRandomId(), getUser(), null);
//        message.setImage(new Message.Image(getRandomImage()));
//        return message;
//    }
//
//    public static Message getVoiceMessage() {
//        Message message = new Message(getRandomId(), getUser(), null);
//        message.setVoice(new Message.Voice("http://example.com", rnd.nextInt(200) + 30));
//        return message;
//    }
//
//    public static Message getTextMessage() {
//        return getTextMessage(getRandomMessage());
//    }
//
//    public static Message getTextMessageUser(String text,boolean recebida) {
//        return new Message(getRandomId(), getUser(recebida), text);
//    }
//
//    public static Message getTextMessage(String text) {
//        return new Message(getRandomId(), getUser(), text);
//    }
//
//    public static ArrayList<Message> getMessages(Date startDate) {
//        ArrayList<Message> messages = new ArrayList<>();
//        for (int i = 0; i < 10/*days count*/; i++) {
//            int countPerDay = rnd.nextInt(5) + 1;
//
//            for (int j = 0; j < countPerDay; j++) {
//                Message message;
//                if (i % 2 == 0 && j % 3 == 0) {
//                    message = getImageMessage();
//                } else {
//                    message = getTextMessage();
//                }
//
//                Calendar calendar = Calendar.getInstance();
//                if (startDate != null) calendar.setTime(startDate);
//                calendar.add(Calendar.DAY_OF_MONTH, -(i * i + 1));
//
//                message.setCreatedAt(calendar.getTime());
//                messages.add(message);
//            }
//        }
//        return messages;
//    }
//
//    private static User getUser() {
//        boolean even = rnd.nextBoolean();
//        return new User("0" ,
//                 names.get(0) ,
//                 avatars.get(0) ,
//                true);
//    }
//
//    private static User getUser(boolean recebida) {
//        boolean even = rnd.nextBoolean();
//        if (recebida){
//
//            return new User("1" ,
//                    names.get(1) ,
//                    avatars.get(1) ,
//                    true);
//        }
//        return new User("0" ,
//                 names.get(0) ,
//                 avatars.get(0) ,
//                true);
//    }
}
