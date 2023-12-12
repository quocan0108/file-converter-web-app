package controller;

import model.bean.PDF2WORD;

public class BackgroundWork2 {
    public static void addToQueue(PDF2WORD word) {
        Runnable run = new MyRunnable2(word);
        new Thread(run).start();
    }
}

class MyRunnable2 implements Runnable {
    model.bean.PDF2WORD pdf;

    public MyRunnable2(model.bean.PDF2WORD pdf) {
        this.pdf = pdf;
    }

    public void run() {
        try {
            // Convert here!
            model.bo.PDF2WORD.ConvertToWord(this.pdf);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
