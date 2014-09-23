/**
 *
 */
package com.octo.reactive.audit.integration.maven.sample;


/**
 * @author Yacine
 *
 */
public class TestApp {
    public static void main(String[] args) {
        System.out.println("Reactive test application is running!!");

        startThread();

        System.out.println("Reactive test application stopped.");
    }

    private static void startThread() {
        final Thread th = new Thread() {
            /* (non-Javadoc)
             * @see java.lang.Thread#run()
             */
            @Override
            public void run() {
                try {
                    Thread.sleep(200L);
                } catch (final InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };

        th.start();
    }
}
