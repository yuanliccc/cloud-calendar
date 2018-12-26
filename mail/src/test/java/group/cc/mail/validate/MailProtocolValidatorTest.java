package group.cc.mail.validate;

import org.junit.Test;

public class MailProtocolValidatorTest  {

    @Test
    public void testValidator() {
        System.out.println("pop".matches("(pop3)|(smtp)|(imap)"));
    }
}
