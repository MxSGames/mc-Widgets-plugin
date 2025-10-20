plugins {
    id("java")
    id("io.github.goooler.shadow") version "8.1.7"
    id("maven-publish")
}

group = "com.monsterxsquad"
version = "1.0.7"

publishing {
    publications {
        create<MavenPublication>("shadow") {
            groupId = project.group.toString()
            artifactId = "Widgets"
            version = project.version.toString()

            artifact(tasks.shadowJar.get().archiveFile) {
                builtBy(tasks.shadowJar)
            }
        }
    }

    repositories {
        mavenLocal()
    }
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")

    maven("https://repo.codemc.org/repository/maven-public/")

    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.10-R0.1-SNAPSHOT")
    implementation("dev.jorel:commandapi-paper-shade:11.0.0")

    compileOnly("me.clip:placeholderapi:2.11.3")

    implementation("org.bstats:bstats-bukkit:3.0.2")

    implementation("org.reflections:reflections:0.10.2")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks.compileJava {
    options.encoding = "UTF-8"
}

tasks.shadowJar {
    relocate("dev.jorel.commandapi", "com.monsterxsquad.widgets.libs.commandapi")

    relocate("org.bstats", "com.monsterxsquad.widgets.libs.bstats")
    relocate("org.reflections", "com.monsterxsquad.widgets.libs.reflections")
}

tasks.jar {
    dependsOn(tasks.shadowJar)
}