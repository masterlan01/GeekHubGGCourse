byte lang_count = 8
long age = 55
String what = 'the programming language'
String name = "Vladivir"
int os_count = 7

def expiriens = 36.5
BigDecimal item = 1.5

String message ="""Hello, my name is ${name}.
I am ${age} years old.
I've been working as a software developer for ${expiriens} yeas.
"""
String extMessage ="""
I know ${lang_count} ${what}.
I worked with ${os_count} operating system.
"""
def addMessage = """
I like Java.
I learn a \"Groovy\" ${item} and more hour everyday.
I am delighted with \"Groovy\" !
"""
/* Имя этой ( "isShortVersion_да" ) переменной содержит символы кириллицы .*/
boolean isShortVersion_да = false
/* Раскоментировать следующую строку для краткой версии */
//isShortVersion_да = !isShortVersion_да

String comment = !isShortVersion_да? 
$/(Для краткой версии раскоментируйте строку №26)/$ :
$/(Для полной версии закоментируйте строку №26)/$


(isShortVersion_да? message + addMessage : message + extMessage + addMessage) + "\n"+comment



