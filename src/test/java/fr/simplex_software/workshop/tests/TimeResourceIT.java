package fr.simplex_software.workshop.tests;

import fr.simplex_software.workshop.client.*;
import io.quarkus.test.junit.*;
import io.reactivex.*;
import jakarta.inject.*;
import org.eclipse.microprofile.config.inject.*;
import org.eclipse.microprofile.rest.client.inject.*;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.*;

@QuarkusTest
public class TimeResourceIT
{
  @Inject
  @RestClient
  TimeZoneResourceClient timeZoneResourceClient;
  @Inject
  @ConfigProperty(name = "base_uri/mp-rest/url")
  String baseURI;

  @Test
  public void testTimeZoneStreamResource() throws InterruptedException
  {
    assertThat(timeZoneResourceClient).isNotNull();
    CountDownLatch latch = new CountDownLatch(1);
    Flowable<TimeZone> tzs = timeZoneResourceClient.getTimeZoneStream();
    assertThat(tzs).isNotNull();
    tzs.subscribe(
      timeZone -> System.out.println(timeZone),
      throwable -> System.err.println(throwable),
      () -> latch.countDown()
    );
    latch.await();
    assertThat(latch.getCount()).isEqualTo(0L);
  }
}
