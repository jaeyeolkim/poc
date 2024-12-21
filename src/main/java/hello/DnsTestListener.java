package hello;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class DnsTestListener implements ApplicationListener<ApplicationStartedEvent> {
    private static final String dnsAddress = "myhost";
    private final DnsTestRunner dnsTestRunner = new DnsTestRunner();
    private boolean isRunning = true;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        Executors.newSingleThreadExecutor().execute(dnsTestRunner);
    }

    @PreDestroy
    public void destroy() {
        isRunning = false;
    }

    public class DnsTestRunner implements Runnable {
        String prevIp = "";
        @Override
        public void run() {
            while(isRunning) {
                log.info("--------------------------------------------------------");
                try {
                    String ip = InetAddress.getByName(dnsAddress).getHostAddress();
                    if (!ip.equals(prevIp)) {
                        log.info("Changed IP: {}", ip);
                    }
                    prevIp = ip;
                } catch (UnknownHostException e) {
                    log.error("DNS \"{}\" is UnknownHost", dnsAddress);
                }

                try {
                    Thread.sleep(2_000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}