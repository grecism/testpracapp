package com.main.thread;

/**
 * @author lulu.zou
 * @version 2018/4/20
 * @since 2018/4/20
 */
public class ThreadTest {
    public static void main(String[] args) {
        System.out.println("aa");
        /*你看或不看，demand就在那里，不多不少；
        你想或不想，bug就在那里，不增不减；
        你查或不查，excepiton就在那里，不静不动；
        你改或不改，error就在那里，不离不弃；
        解决它或者处理它，油满面，泪千行。*/

        try {
            boolean youSee = false,youThink = false,youFind = false,youUpdate = false,solveIt = false,debugIt = false;
            if(!youSee || youSee){
                System.out.println("你看或不看，demand就在那里，不多不少");
            }
            if(!youThink || youThink){
                System.out.println("你想或不想，bug就在那里，不增不减");
            }
            if(!youFind || youFind){
                System.out.println("你查或不查，excepiton就在那里，不静不动");
            }
            if(!youUpdate || youUpdate){
                System.out.println("你改或不改，error就在那里，不离不弃");
            }
            while(!solveIt || !debugIt){
                Thread.sleep(10000);
                System.out.println("油满面，泪千行~");
                throw new InterruptedException();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("已崩溃~");
        }
    }
}
