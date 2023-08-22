package az.company.msingress.scheduler;

import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngressScheduler {

    @Scheduled(fixedDelayString = "PT1M")
    @SchedulerLock(name="sendMessage",lockAtLeastFor = "PT1M",lockAtMostFor = "PT5M")
    public void sendMessage(){
        System.out.println("Sending message");
    }

//    @Scheduled(cron = "10 * * * * *")   //after 10 seconds per every minute
//    @SchedulerLock(name="sendMessage2",lockAtLeastFor = "PT1M",lockAtMostFor = "PT5M")
//    public void sendMessage2(){
//        System.out.println("Sending message222");
//    }
}
