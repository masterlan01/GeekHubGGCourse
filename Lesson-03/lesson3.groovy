/**
 * Created by masterlan on 29.10.15.
 */

import groovy.transform.ToString
class lesson3 {
    static void main(args){

        List Invoices = [];

        Invoices << new ElectricPowerInvoice(   number:"17",
                date: Date.parse('yyyy-MM-dd','2015-10-25'),
                from: "Энергосети",
                kilowatt: 12.83,
                tariff: 4.52,
                total: 58.00)

        Invoices << new WaterSupplyInvoice(   number:"45",
                date: Date.parse('yyyy-MM-dd','2015-10-27'),
                from: "ЧеркассыВодоканал",
                volume: 12.59,
                tariff: 2.45,
                total: 30.85)

        Invoices << new GasInvoice(   number:"45",
                date: Date.parse('yyyy-MM-dd','2015-10-27'),
                from: "ЧеркассыГаз",
                humans: 2,
                tariff: 8.85,
                total: 17.70)

        Invoices << new HeatingInvoice(   number:"45",
                date: Date.parse('yyyy-MM-dd','2015-10-27'),
                from:"ТЕЦ",
                livingspace: 92.43,
                tariff: 342.00,
                total: 445.00)

        Invoices << new HotWaterSupplyInvoice(   number:"45",
                date: Date.parse('yyyy-MM-dd','2015-10-27'),
                from: "ЧеркассыВодоканал",
                volume: 2.33,
                tariff: 342.00,
                total: 45.00)

        Invoices << new PhoneInvoice(   number:"45",
                date: Date.parse('yyyy-MM-dd','2015-10-27'),
                from: "УкрТелеком",
                call_duration: 3.59,
                tariff: 2.45,
                total: 8.80)

        Invoices << new OtherServicesInvoice(   number:"45",
                date: Date.parse('yyyy-MM-dd','2015-10-27'),
                from: "Приднепровский СУБ",
                total: 35.00)

        Invoices << new CabelTVInvoice(   number:"45",
                date: Date.parse('yyyy-MM-dd','2015-10-27'),
                from: "Кабель-Воля",
                total: 165.00)

        Invoices << new INetInvoice(   number:"45",
                date: Date.parse('yyyy-MM-dd','2015-10-27'),
                from: "McLaut ISP",
                total: 75.00)


        Invoices.each { println "$it\n\tэта платёжка за " + it.title.toUpperCase()+" от \"" + it.from + "\" " + ((it.validate()? ", она корректена":" и она не корректна !" )+"\n")}
    }
}

interface Invoice {
    boolean validate()
}

@ToString(includeNames = true, includeFields = true)
abstract class CommunalInvoice implements Invoice{
    String number
    Date date
    String from
    private String to = "Логунов В.С."
    BigDecimal total
    boolean validate(){
        true
    }

}

@ToString(includeNames = true, includeFields = true, includeSuper=true)
class ElectricPowerInvoice extends CommunalInvoice{
    BigDecimal kilowatt
    BigDecimal tariff
    String getTitle(){"Электроснабжение"}

    boolean validate(){
        total == (kilowatt.multiply(tariff)).setScale(2, BigDecimal.ROUND_HALF_UP)
    }

}

@ToString(includeNames = true, includeFields = true, includeSuper=true)
class WaterSupplyInvoice extends CommunalInvoice{
    BigDecimal volume
    BigDecimal tariff
    String getTitle(){"Водоснабжение"}

    boolean validate(){
        total == (volume.multiply(tariff)).setScale(2, BigDecimal.ROUND_HALF_UP)
    }
}


@ToString(includeNames = true, includeFields = true, includeSuper=true)
class GasInvoice extends CommunalInvoice{
    Integer humans
    BigDecimal tariff
    String getTitle(){"Газ"}

    boolean validate(){
        total == (humans.multiply(tariff)).setScale(2, BigDecimal.ROUND_HALF_UP)
    }
}

@ToString(includeNames = true, includeFields = true, includeSuper=true)
class HeatingInvoice extends CommunalInvoice{
    BigDecimal livingspace
    BigDecimal tariff
    String getTitle(){"Отопление"}

    boolean validate(){
        total == (livingspace.multiply(tariff)).setScale(2, BigDecimal.ROUND_HALF_UP)
    }
}

@ToString(includeNames = true, includeFields = true, includeSuper=true)
class HotWaterSupplyInvoice extends CommunalInvoice{
    BigDecimal volume
    BigDecimal tariff
    String getTitle(){"Снабжение горячей водой"}

    boolean validate(){
        total == (volume.multiply(tariff)).setScale(2, BigDecimal.ROUND_HALF_UP)
    }

}

@ToString(includeNames = true, includeFields = true, includeSuper=true)
class PhoneInvoice extends CommunalInvoice{
    BigDecimal call_duration
    BigDecimal tariff
    String getTitle(){"Стационарный телефон"}

    boolean validate(){
        total == (call_duration.multiply(tariff)).setScale(2, BigDecimal.ROUND_HALF_UP)
    }

}

@ToString(includeNames = true, includeFields = true, includeSuper=true)
class OtherServicesInvoice extends CommunalInvoice{
    String getTitle(){"Услуги СУБ"}
}

@ToString(includeNames = true, includeFields = true, includeSuper=true)
class CabelTVInvoice extends CommunalInvoice{
    String getTitle(){"Кабельное телевидение"}
}

@ToString(includeNames = true, includeFields = true, includeSuper=true)
class INetInvoice extends CommunalInvoice{
    String getTitle(){"Услуги интернет-провайдера"}
}
