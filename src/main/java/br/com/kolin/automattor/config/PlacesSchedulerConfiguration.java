package br.com.kolin.automattor.config;

import br.com.kolin.automattor.job.AutoWiringSpringBeanJobFactory;
import br.com.kolin.automattor.job.PlacesJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import java.time.*;
import java.util.Date;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class PlacesSchedulerConfiguration {

    private final ApplicationContext applicationContext;

    @Bean
    public JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(PlacesJob.class);
        jobDetailFactory.setName("Quartz_Google_Place_Job");
        jobDetailFactory.setDescription("Invoke GooglePlacesJob service...");
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean trigger(@Qualifier("jobDetail") JobDetail job,
                                            @Value("${org.quartz.trigger.start.year}") Integer year,
                                            @Value("${org.quartz.trigger.start.month}") String month,
                                            @Value("${org.quartz.trigger.start.day}") Integer day,
                                            @Value("${org.quartz.trigger.start.hour}") Integer hour,
                                            @Value("${org.quartz.trigger.start.minute}") Integer minute,
                                            @Value("${org.quartz.trigger.frequency}") Integer frequency) {

        LocalDateTime jobStartTime = LocalDateTime.of(year, Month.valueOf(month), day, hour, minute);

        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setStartTime(Date.from(jobStartTime.atZone(ZoneId.systemDefault()).toInstant()));
        trigger.setStartDelay(0L);
        trigger.setJobDetail(job);
        trigger.setRepeatInterval(frequency);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        trigger.setName("Quartz_Google_Place_Trigger");

        return trigger;
    }

    @Bean
    public SchedulerFactoryBean scheduler(SimpleTriggerFactoryBean triggerFactory, JobDetail job) {

        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setSchedulerName("Quartz_Google_Place_Scheduler");
        schedulerFactory.setJobFactory(springBeanJobFactory());
        schedulerFactory.setJobDetails(job);
        schedulerFactory.setTriggers(triggerFactory.getObject());

        return schedulerFactory;
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {

        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);

        return jobFactory;
    }

}
