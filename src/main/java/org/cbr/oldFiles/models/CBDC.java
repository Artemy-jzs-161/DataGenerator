package org.cbr.oldFiles.models;

/**
 * @num - номер п.п. ключа.
 * @contName; - каталог с ключом/ имя сессии янтаря (профиль СКАД)
 * @country; - Страна
 * @region; - Город, Область (регион)
 * @company; - Организация (ИД владельца ключа)
 * @commonName; - Общее имя (ИД ФП)
 * @locality; - Населенный пункт (область применения)
 * @subdivision; - Подразделение (ИД Кошелька)
 * @keyUsage; - Флаг разреш. генерации ключа шифрования
 * @eku; - Расширенная область применения ключа
 * @regulationUseCert; - Регламенты использования сертификата
 * @acquiredName; - Приобретенное имя
 * @position; - Должность
 * @inCertWin; - Вкл. серт. для windows
 * @inCertLinux; - Вкл. серт. для linux
 * @Street; - Улица
 * @AltName; - Альтернативное имя Владельца: ДНС
 * @publisherCertificate; - Издатель в сертификате
 * @UCpublisherCertificate; - УЦ, издавший сертификат
 * @idKey; - Идентификатор ключа
 * @SNILS; - СНИЛС
 * @usagePeriodCertLifeTime; - срок действия сертификата (кол-во месяцев)
 * @usagePeriodKeyLifeTime; - срок действия ключа (кол-во мес)
 * @company2; - Организация 2
 * @commonName2; - Общее имя 2
 * @subdivision2; - Подразделение
 * @comment; - примечания к генерации ключа и сертификата
 * @description; - название ключа
 */

public record CBDC() {
    static String num;
    static String contName;
    static String country;
    static String region;
    static String company;
    static String commonName;
    static String locality;
    static String subdivision;
    static String keyUsage;
    static String eku;
    static String regulationUseCert;
    static String acquiredName;
    static String position;
    static String inCertWin;
    static String inCertLinux;
    static String Street;
    static String AltName;
    static String publisherCertificate;
    static String UCpublisherCertificate;
    static String idKey;
    static String SNILS;
    static String usagePeriodCertLifeTime;
    static String usagePeriodKeyLifeTime;
    static String company2;
    static String commonName2;
    static String subdivision2;
    static String comment;
    static String description;
}
