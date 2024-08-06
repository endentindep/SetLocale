package ru.crystals;

import java.util.Optional;

public class SetLocale {

    private final static String RU_LOCALE = "ru_RU";
    private final static String EN_LOCALE = "en_US";

    private static final String LOCALE_KEY = "locale"; // пример значения получаемого по ключу: ru_RU
    private static final String COUNTRY_KEY = "country"; // пример значения получаемого по ключу: RU

    private static final LocaleHelper localeHelper = new LocaleHelper() {};

    public static void main(String[] args) {
        setLocale();
    }

    private static void setLocale() {
            localeHelper.getProperty(LOCALE_KEY).ifPresentOrElse(
                    SetLocale::applyLocaleOrDefault,
                    SetLocale::setLocaleByCountry
            );
    }

    private static void setLocaleByCountry() {
        String countryCode = localeHelper.getProperty(COUNTRY_KEY).get();
        String defaultLocale = Country.getByCode(countryCode, Country.RU).getLocale();
        applyLocaleOrDefault(defaultLocale);
    }

    private static void applyLocaleOrDefault(String locale) {
        if (RU_LOCALE.equals(locale) || EN_LOCALE.equals(locale)) {
            localeHelper.setLocaleByString(locale);
        } else {
            localeHelper.setLocaleByString(EN_LOCALE);
        }
    }
}