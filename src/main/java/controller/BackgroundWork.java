package controller;

import model.bean.WORD2PDF;

public class BackgroundWork {
    public static void addToQueue(WORD2PDF pdf) {
        Runnable r = new MyRunnable(pdf);
        new Thread(r).start();
    }
}

class MyRunnable implements Runnable {
    model.bean.WORD2PDF word;

    public MyRunnable(model.bean.WORD2PDF word) {
        this.word = word;
    }

    public void run() {
        try {
            // Convert here!
            model.bo.WORD2PDF.ConvertToPDF(this.word);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}