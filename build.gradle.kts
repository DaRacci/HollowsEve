version = 2021

dependencies {

    compileOnly(rootProject.libs.racciCore)
    compileOnly(rootProject.libs.citizensAPI)
    compileOnly(rootProject.libs.ecoEnchants)
    compileOnly(files("../API/GoldenCrates.jar"))
    compileOnly(files("../API/NexEngine.jar"))

}