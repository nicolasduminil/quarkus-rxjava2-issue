package fr.simplex_software.workshop;

import io.reactivex.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.jboss.resteasy.annotations.*;

import java.util.*;

@Path("tz2")
@Produces(MediaType.APPLICATION_JSON)
@Stream(Stream.MODE.GENERAL)
public class TimeZoneResource
{
  private static final List<String> locations = new ArrayList<>(List.of(TimeZone.getAvailableIDs()));

  @GET
  public Flowable<String> getTimeZoneStream()
  {
    return Flowable.create(flowableEmitter ->
    {
      locations.forEach(location -> flowableEmitter.onNext(location));
      flowableEmitter.onComplete();
    }, BackpressureStrategy.BUFFER);
  }
}
