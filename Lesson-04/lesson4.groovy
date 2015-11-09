/**
 * Created by master on 07.11.15.
 */

class lesson4 {

    static void main(args) {

        def fabric08 = new Manufactory()

        for (i in 1..10){
            println "PC-$i\t- ${fabric08.makePC("./Lesson-04/continuity.job")}"
        }

        println "PC-(bad)\t- ${fabric08.makePC("./Lesson-04/continuityBad.job")}"
    }
}

class Manufactory {

    Random rand = new Random();
    
    Map stores = [
        caseStore:["Deepcool", "DELUX", "Fractal", "Design", "GAMEMAX", "LogicPower", "ThermalTake", "Zalman"],
        powersupplyStore:["AeroCool","CHIEFTEC","CoolerMaster", "CORSAIR", "Deepcool", "GEMBIRD"],
        motherboardStore:["ASRock","ASUS","Biostar","GIGABYTE","INTEL","MSI"],
        cpuStore: ["Core i3", "Core i5", "Core i7", "AMD FX", "AMD Athlon", "AMD Sempron", "Pentium", "Celeron"],
        coolerStore:["Arctic","CoolerMaster","Deepcool","Noctua","SCYTHE","Thermalright","TITAN","Xigmatek" ],
        ramStore:["CORSAIR","G.Skill","GEIL","GOODRAM","Hynix","Kingston","Samsung","Silicon Power","Team","Transcend",],
        videoStore:["ASUS","EVGA","ZOTAC","Sapphire","PowerColor","PALIT","MSI","Inno3D","HIS","GIGABYTE",],
        hddStore:["TOSHIBA","Samsung","Seagate","Western Digital","Hitachi",]
    ]

    Map operationsPool = [
            caseSelect : { store, HashMap <String, String> computer ->
                computer << [case: store[rand.nextInt(store.size())]]
            },
            powersupplyInstall : { store, HashMap <String, String> computer ->
                if(computer.containsKey("case"))
                    computer << [powersupply: store[rand.nextInt(store.size())]]
            },
            motherboardInstall : { store, HashMap <String, String> computer ->
                if(computer.containsKey("case"))
                    computer << [motherboard: store[rand.nextInt(store.size())]]
            },
            cpuInstall : { store, HashMap <String, String> computer ->
                if(computer.containsKey("motherboard"))
                    computer << [cpu: store[rand.nextInt(store.size())]]
            },
            coolerInstall : { store, HashMap <String, String> computer ->
                if(computer.containsKey("cpu"))
                    computer << [cooler: store[rand.nextInt(store.size())]]
            },
            ramInstall : { store, HashMap <String, String> computer ->
                if(computer.containsKey("motherboard"))
                    computer << [ram: store[rand.nextInt(store.size())]]
            },
            videoInstall : { store, HashMap <String, String> computer ->
                if(computer.containsKey("motherboard"))
                    computer << [video: store[rand.nextInt(store.size())]]
            },
            hddInstall : { store, HashMap <String, String> computer ->
                if(computer.containsKey("motherboard"))
                    computer << [hdd: store[rand.nextInt(store.size())]]
            },
    ]

    HashMap makePC(String fileName){
        HashMap<String, String> ibmPC = new HashMap<String, String>();
        File file = new File(fileName)
        file.eachLine { line ->
            if (line.trim().length() > 0) {

                String storeName = "${line.split()[1].toLowerCase()}Store"

                if (stores.containsKey(storeName)) {
                    def storeItem = stores.get(storeName)

                    String operationName = line.split()[1].toLowerCase() + line.split()[0].toUpperCase()[0] + line.split()[0].toLowerCase().substring(1)

                    if (operationsPool.containsKey(operationName))
                        (operationsPool.get(operationName)).call(storeItem, ibmPC)
                    else
                        println "Operation \"${line.split()[0]}\" not exist !"
                }
                else {
                    println "Store \"${storeName}\" not exist !"
                }

            }
        }
        return ibmPC
    }
}