import sun.jvmstat.monitor.MonitoredVmUtil.mainClass

plugins {
    id("java")
    id ("application")
}
application {
    mainClass.set("com.sergiomorell.tema4gradle.Main")
}

group = "com.sergiomorell.tema4gradle"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(platform("dev.langchain4j:langchain4j-bom:1.10.0"))
    implementation("dev.langchain4j:langchain4j-open-ai")
}

tasks.test {
    useJUnitPlatform()
}

System.getProperty("os.name")


val vers = tasks.register <Exec>("ollamaVersion") {
    commandLine("ollama","--version")
}

val ps = tasks.register <Exec>("ollamaPs") {
    commandLine("ollama","ps")
}
tasks.register ("llmInfo") {
    dependsOn(vers,ps)
    doLast{
        println("Demo finalizada");
    }
}
