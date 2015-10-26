def asus     = [cpu:"i3" ,ram:2 ,inch:14,hdd:1.0]
def acer     = [cpu:"A4" ,ram:2 ,inch:14,hdd:1.0]
def hp       = [cpu:"i5" ,ram:4 ,inch:15,hdd:1.5]
def dell     = [cpu:"A6" ,ram:4 ,inch:15,hdd:1.5]
def lenovo   = [cpu:"i7" ,ram:8 ,inch:17,hdd:2.0]
def toshiba  = [cpu:"A8" ,ram:8 ,inch:17,hdd:2.0]
def fujitsu  = [cpu:"A10",ram:16,inch:18,hdd:2.0]

def shop = [asus:asus,acer:acer,hp:hp,dell:dell,lenovo:lenovo,toshiba:toshiba,fujitsu:fujitsu]

StringBuffer jsonStr = new StringBuffer("\n{")
shop.each { vendor, notebook ->
    jsonStr.append("\"$vendor\": \n  {")
    notebook.each { k , val -> (val.class in Number ) ?  jsonStr.append("\"$k\":  $val ,") : jsonStr.append("\"$k\": \"$val\",")}
    if(notebook.size() > 0) jsonStr[jsonStr.length()-1 .. jsonStr.length()-1]="" 
    jsonStr.append("},\n")
}
if (shop.size() > 0) jsonStr[jsonStr.length()-2 .. jsonStr.length()-1]="" 
jsonStr.append("\n}")
println "The contents of the store:"+jsonStr

println "The \"GREEN\" notebooks - \t"+shop.findAll{it.value.cpu.contains("A") && it.value.hdd==shop.collect {it.value.hdd}.min()}
def intelCPU = shop.findAll{it.value.cpu.contains("i")}
println "Simply the best - \t\t"+intelCPU.find{it.value.ram==(intelCPU.collect {it.value.ram}.max())}
