package eightysix250kaffe.rest.resources.open;

import com.twilio.sdk.examples.TwiMLResponseExample;
import com.twilio.sdk.verbs.Sms;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;
import eightysix250kaffe.rest.api.Participant;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Slf4j
@Path("/open/kaffe/sms")
@Service
public class SmsReceptionResource {

    @POST
    @Produces(MediaType.APPLICATION_XML)
    public String testReceive(Object request) {
        log.info("POST testReceive: " + request);
        log.info("request class: " + request.getClass());

        TwiMLResponse twiml = new TwiMLResponse();
        try {
            twiml.append(new Sms("Hei på deg :) " + new DateTime() + ", request: " + request));
        }
        catch (TwiMLException e) {
            log.error("Error building twiML: " + e.getMessage(), e);
        }

        log.info("Twiml response: " + twiml.toXML());

        return twiml.toXML();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String get(Object request) {
        log.info("GET test: " + request);
        log.info("request class: " + request.getClass());

        TwiMLResponse twiml = new TwiMLResponse();
        try {
            twiml.append(new Sms("Hei på deg :) " + new DateTime() + ", request: " + request));
        }
        catch (TwiMLException e) {
            log.error("Error building twiML: " + e.getMessage(), e);
        }

        log.info("Twiml response: " + twiml.toXML());

        return twiml.toXML();
    }


}
