package test.AutomationExerciseBDD.Utils;
import java.util.concurrent.atomic.AtomicLong;

public class ExecutionTimer {

public static AtomicLong timer=new AtomicLong();
  public static AtomicLong startTime=new AtomicLong();
  public static AtomicLong endTime=new AtomicLong();
 
  //Synchronizing this for multi-threading
 
  public static synchronized void startTimer() {
  startTime.set(System.currentTimeMillis());
  }
 
  public static synchronized void endTimer() {
  endTime.set(System.currentTimeMillis());
  timer.set(timer.get()+endTime.get()-startTime.get());
  }
 
  public static synchronized long returnTime() {
  return timer.get();
  }

}