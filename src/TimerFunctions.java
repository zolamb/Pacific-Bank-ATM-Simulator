import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TimerFunctions {

    //CREDIT TO:  http://stackoverflow.com/questions/7269294/how-to-stop-a-runnable-scheduled-for-repeated-execution-after-a-certain-number-o
    //USER:  http://stackoverflow.com/users/654801/sbridges
    public void beginDelayedTimer(Runnable task, int maxRunCount, long period, TimeUnit unit, ScheduledExecutorService executor) {
        new FixedExecutionRunnable(task, maxRunCount).runNTimes(executor, period, unit);
    }

    class FixedExecutionRunnable implements Runnable {
        private final AtomicInteger runCount = new AtomicInteger();
        private final Runnable delegate;
        private volatile ScheduledFuture<?> self;
        private final int maxRunCount;

        public FixedExecutionRunnable(Runnable delegate, int maxRunCount) {
            this.delegate = delegate;
            this.maxRunCount = maxRunCount;
        }

        @Override
        public void run() {
            delegate.run();
            if(runCount.incrementAndGet() == maxRunCount) {
                boolean interrupted = false;
                try {
                    while(self == null) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            interrupted = true;
                        }
                    }
                    self.cancel(false);
                } finally {
                    if(interrupted) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void runNTimes(ScheduledExecutorService executor, long period, TimeUnit unit) {
            self = executor.scheduleAtFixedRate(this, 0, period, unit);
        }
    }
}

