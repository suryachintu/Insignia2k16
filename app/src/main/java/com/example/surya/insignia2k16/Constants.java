package com.example.surya.insignia2k16;

import com.example.surya.insignia2k16.events_main.EventsModel;

import java.util.ArrayList;

/**
 * Created by surya on 22-06-2016.
 */
public class Constants {
    public static final String TIMESTAMP = "timestamp";
//    public static String USERNAME = "USERNAME";
    public static boolean FLAG = false;
    public static ArrayList<EventsModel> eventsModels = new ArrayList<>();

    public static  ArrayList<String> users = new ArrayList<>();

    public static Integer[] requiredMembers = {2,4,1,1,2,1,1,4,2,1,3,1};

    public static String MUN_URL = "http://nitdmun.insigniathefest.com/delegate.html";

    public static String[] mEvents_names = {"HEX A HEX","Les Quizzerables",
                                                "TUSSLE OF TONGUES",
                                                  "NITD MUN","WORDSMITH’S WONDERLAND","GHOST WRITING",
                                                    "FOR BETTER, FOR VERSE","TREASURE HUNT","RAGPICKER'S PURSUIT",
                                                        "BLIND FOLD","MURAL PAINTING"
                                                            ,"COMES AROUND GOES AROUND"};
    public static Integer[] mEvents_posters = {R.drawable.hexahex,R.drawable.lesquizzerables,R.drawable.deadschest,R.drawable.mun,
            R.drawable.wordsmith,R.drawable.deadschest,R.drawable.for_bettorverse,R.drawable.deadschest,R.drawable.ragpicker,R.drawable.deadschest,R.drawable.muralpainting,R.drawable.deadschest};

    public static String[] mEvent_cordinators = {"Sravan,Shehnaz Sheik,Ali","Sanyogitha,Soumesh","Aditi Yadav,Amulya,Aryan",
                                                    "Avtansh Gupta,Swapnil Vijay,Julia","Diksha Sharma,Tanushree","Swathi Sharma",
                                                      "Sanjana Sinha,Nita","Tejesh","Nitika,Sangeeta","Aditya,Yoga","Sravan,Ali","Sanjana Sinha,Swati Sharma"};

    public static Integer[] mEvents_description = {
                                    R.string.HEX_A_HEX,R.string.Les_Quizzerables,R.string.TUSSLE_OF_TONGUES,
                                    R.string.NITD_MUN,R.string.WORDSMITHS_WONDERLAND,R.string.GHOST_WRITING,R.string.FOR_BETTER_FOR_VERSE,R.string.TREASURE_HUNT,
                                    R.string.RAGEPICKERS_PURSUIT ,R.string.BLIND_FOLD,R.string.MURRAL_PAINTING,
                                    R.string.COMES_AROUND_GOES_AROUND
                                };


    public static Integer[] mEvents_rules = {
                                    R.string.HEX_A_HEX_RULE,R.string.Les_Quizzerables_RULE,R.string.TUSSLE_OF_TONGUES_RULE,
                                    R.string.NITD_MUN_RULE,R.string.WORDSMITHS_WONDERLAND_RULE,R.string.GHOST_WRITING_RULE,R.string.FOR_BETTER_FOR_VERSE_RULE,
                                    R.string.TREASURE_HUNT_RULE,R.string.RAGEPICKERS_PURSUIT_RULE,R.string.BLIND_FOLD_RULE,R.string.MURRAL_PAINTING_RULE,R.string.COMES_AROUND_GOES_AROUND_RULE
                                };

    public static final boolean[] FLAGARRAY ={false,false,false,false,false,false,false,false,false,false,false,false} ;
    //tree roots
    public static String event_name = "dummy";
    public static final String MESSAGES = "messages";
    public static final String CHATROOMS = "CHATROOMS";
    public static final String INBOX = "INBOX";
    public static final String[] insignia_heads = {"Surya Parsa","Aditya Chamarathi","Yoga","Tejesh"};

    public static String[] mEvent_time = {"5th November 2016","5th November 2016","5th November 2016","5th November 2016","5th November 2016","5th November 2016","5th November 2016","5th November 2016",
                                                    "5th November 2016","5th November 2016","5th November 2016","5th November 2016",};


    public static String[] mVenues ={"NIT DELHI","NIT DELHI","NIT DELHI","NIT DELHI","NIT DELHI","NIT DELHI","NIT DELHI","NIT DELHI","NIT DELHI","NIT DELHI","NIT DELHI","NIT DELHI"};
    public static String[] mEvents_Informal = {"FACE PAINTING","MINUTE TO WIN","TUG OF WAR","TYPE-A-THON","LETTERS TO JULIET WALL OF FEVER","GULLY CRICKET","BELLY BALLOON","UFF MIRCHI","RING TOSS"};
    public static Integer[] mEvents_Informal_description = {R.string.FACE_PAINTING,R.string.MINUTE_TO_WIN,R.string.TUG_OF_WAR,
                                    R.string.TYPE_A_THON,R.string.LETTERS_TO_JULIET,R.string.GULLY_CRICKET,R.string.BELLY_BALLOON,R.string.UFF_MIRCHI,R.string.RING_TOSS};
}
