package az.company.msingress.scheduler;

import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheScheduler {

    @Scheduled(fixedDelayString = "PT1M")
    @SchedulerLock(name="clearCache",lockAtLeastFor = "PT1M",lockAtMostFor = "PT5M")
    @CacheEvict(value = "cards",allEntries = true)
    public void clearCache(){
        System.out.println("Cache Cleared");
    }//scheduler for query cache
}
