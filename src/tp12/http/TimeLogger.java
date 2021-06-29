package tp12.http;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class TimeLogger {
    static Instant timeBase=Instant.now();
    static boolean show=true;
    
    static void show(boolean show) {
        TimeLogger.show=show;
    }
    
    static void log(String message,Object ... pargs) {
        if (!show) return;
        Duration when=Duration.between(timeBase,Instant.now());
        @SuppressWarnings("unused")
        long h=when.getSeconds()/3600; 
        long m=(when.getSeconds()%3600)/60;
        long s=when.getSeconds()%60;
        long u=when.getNano()/1000;
        String lt=String.format("%02d:%02d.%06d",m,s,u);
        String format="%s : Thread %-12s (%03d): "+message+"\n";
        Object[] targs=new Object[] {lt,Thread.currentThread().getName(),Thread.currentThread().getId()};
        Object[] allargs=Arrays.copyOf(targs, targs.length + pargs.length);
        System.arraycopy(pargs, 0, allargs, targs.length, pargs.length);
        
        System.out.printf(format,allargs);
    }
}
