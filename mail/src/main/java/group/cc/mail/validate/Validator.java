package group.cc.mail.validate;

import group.cc.mail.MailException;

public interface Validator<T, R> {

    R validate(T t) throws MailException;
}
